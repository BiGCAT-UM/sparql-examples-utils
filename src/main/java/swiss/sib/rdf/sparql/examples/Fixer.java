package swiss.sib.rdf.sparql.examples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.SHACL;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.query.parser.QueryParser;
import org.eclipse.rdf4j.query.parser.sparql.SPARQLParserFactory;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.sail.SailRepositoryConnection;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.openrdf.query.MalformedQueryException;

import com.bigdata.bop.BOp;
import com.bigdata.rdf.sail.sparql.Bigdata2ASTSPARQLParser;
import com.bigdata.rdf.sail.sparql.BigdataParsedQuery;
import com.bigdata.rdf.sparql.ast.GraphPatternGroup;
import com.bigdata.rdf.sparql.ast.IGroupMemberNode;
import com.bigdata.rdf.sparql.ast.JoinGroupNode;
import com.bigdata.rdf.sparql.ast.NamedSubqueriesNode;
import com.bigdata.rdf.sparql.ast.NamedSubqueryInclude;
import com.bigdata.rdf.sparql.ast.NamedSubqueryRoot;
import com.bigdata.rdf.sparql.ast.QueryRoot;
import com.bigdata.rdf.sparql.ast.QueryType;
import com.bigdata.rdf.sparql.ast.SubqueryRoot;

import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;
import swiss.sib.rdf.sparql.examples.vocabularies.SIB;

@CommandLine.Command(name = "fix", description = "Attempts to fixes example files")
public class Fixer implements Callable<Integer> {

	private static final ValueFactory VF = SimpleValueFactory.getInstance();

	@Spec
	CommandSpec spec;

	private static final String PREFIXES = "PREFIX sh:<http://www.w3.org/ns/shacl#> PREFIX sib:<" + SIB.NAMESPACE + ">";
	@Option(names = { "-i",
			"--input-directory" }, paramLabel = "directory containing example files to test", description = "The root directory where the examples and their prefixes can be found.", required = true)
	private Path inputDirectory;

	@Option(names = { "-h", "--help" }, usageHelp = true, description = "display this help message")
	private boolean usageHelpRequested;

	@Option(names = { "-p", "--project" }, paramLabel = "projects to test", defaultValue = "all")
	private String projects;

	@Option(names = { "--also-run-slow-tests" })
	private boolean alsoRunSlowTests;

	public Integer call() {
		CommandLine commandLine = spec.commandLine();
		if (commandLine.isUsageHelpRequested()) {
			commandLine.usage(System.out);
		} else if (commandLine.isVersionHelpRequested()) {
			commandLine.printVersionHelp(System.out);
		} else {
			findFilesToFix();
		}
		return 0;
	}

	private void findFilesToFix() {
		Model model = Converter.parseExampleFilesIntoModel(projects, inputDirectory);
		SailRepository sr = new SailRepository(new MemoryStore());
		sr.init();
		try (SailRepositoryConnection conn = sr.getConnection()) {
			conn.begin();
			conn.add(model);
			conn.commit();
		}
		Map<String, String> prefixes = new LinkedHashMap<>();
		try (SailRepositoryConnection conn = sr.getConnection()) {
			TupleQuery tq = conn.prepareTupleQuery(PREFIXES + """
						SELECT ?prefix ?namespace {
							[] sh:prefix ?prefix;
							   sh:namespace ?namespace .
						}
					""");
			try (TupleQueryResult tqr = tq.evaluate()) {
				while (tqr.hasNext()) {
					BindingSet tqrb = tqr.next();
					String prefix = tqrb.getValue("prefix").stringValue();
					String namespace = tqrb.getValue("namespace").stringValue();
					prefixes.put(prefix, namespace);
				}
			}
		}
		try (SailRepositoryConnection conn = sr.getConnection()) {
			TupleQuery tq = conn.prepareTupleQuery(PREFIXES + """
						SELECT ?queryIri ?query ?file {
							?queryIri sh:select ?query ;
							    sib:file_name ?file .
						}
					""");
			try (TupleQueryResult tqr = tq.evaluate()) {
				while (tqr.hasNext()) {
					BindingSet tqrb = tqr.next();
					fix((IRI) tqrb.getValue("queryIri"), tqrb.getValue("query"), tqrb.getValue("file"), model, prefixes);
				}
			}
		}
		sr.shutDown();
	}

	private void fix(IRI queryIri, Value query, Value file, Model model, Map<String, String> prefixes2) {
		String queryIriStr = queryIri.stringValue();
		String queryStr = query.stringValue();
		String fileStr = file.stringValue();
		try {
			String fixedPrefixes = Fixer.fixMissingPrefixes(queryStr, prefixes2);
			String fix = Fixer.fixBlazeGraphIncludeWith(fixedPrefixes);
			if (!fix.equals(queryStr)) {
				System.out.println("Fixed " + queryIriStr);
				model.remove(queryIri, SHACL.SELECT, query);
				model.add(queryIri, SHACL.SELECT, VF.createLiteral(fix));
				model.add(queryIri, SIB.BIGDATA_SELECT, query);
			} else if (!fixedPrefixes.equals(queryStr)) {
				System.out.println("Fixed prefixes " + queryIriStr);
				model.remove(queryIri, SHACL.SELECT, query);
				model.add(queryIri, SHACL.SELECT, VF.createLiteral(fixedPrefixes));
			}
		} catch (MalformedQueryException e) {
			System.out.println("Failed to fix " + queryIriStr + " in " + fileStr);
			e.printStackTrace();
		}

		try (FileOutputStream out = new FileOutputStream(queryIriStr)) {
			RDFWriter writer = Rio.createWriter(RDFFormat.RDFXML, out);
			writer.startRDF();
			for (Statement st : model) {
				writer.handleStatement(st);
			}
			writer.endRDF();
		} catch (RDFHandlerException | IOException e) {
			// oh no, do something!
		}
	}

	public static String fixMissingPrefixes(String original, Map<String, String> prefixes2) throws MalformedQueryException {
		boolean fixed = false;
		while(!fixed) {
			try {
				QueryParser parser = new SPARQLParserFactory().getParser();
				parser.parseQuery(original, "http://example.org/");
				fixed = true;
			} catch (org.eclipse.rdf4j.query.MalformedQueryException e) {
				String message = e.getMessage();
				if (message.contains("prefix")) {
					String prefix = message.split("'")[1].split(":")[0];
					String ns = prefixes2.get(prefix);
					original = "PREFIX "+prefix+": <"+ns+">\n" + original;
				}
				break;
			}
		}
		return original;
	}
	
	public static String fixBlazeGraphIncludeWith(String original) throws MalformedQueryException {
		Bigdata2ASTSPARQLParser blzp = new Bigdata2ASTSPARQLParser();
		BigdataParsedQuery pq = blzp.parseQuery(original, "https://example.org/");

		QueryRoot origAst = pq.getASTContainer().getOriginalAST();
		NamedSubqueriesNode nsq = origAst.getNamedSubqueries();
		if (nsq != null) {
			BOp bOp = nsq.get(0);

			origAst.clearProperty("namedSubqueries");

			StringBuilder sb = new StringBuilder(original);
			BOp fixed = replaceIncludes(origAst, bOp, sb);
			return sb.toString();
		}
		return original;
	}

	private static BOp replaceIncludes(BOp astContainer, BOp bOp, StringBuilder blazeGraphIncludeExample) {
		return switch (astContainer) {
		case QueryRoot qr -> {
			var nq = new QueryRoot(qr);
			nq.setGraphPattern((GraphPatternGroup<IGroupMemberNode>) replaceIncludes(nq.getGraphPattern(), bOp,
					blazeGraphIncludeExample));
			yield nq;
		}
		case NamedSubqueryRoot nsqr -> bOp;
		case SubqueryRoot sqb -> sqb;
		case GraphPatternGroup jgn -> {
			var nq = new JoinGroupNode(jgn);
			nq.getChildren().clear();
			for (Object iGroupMemberNode : jgn.getChildren()) {
				if (iGroupMemberNode instanceof IGroupMemberNode mn) {
					nq.addChild((IGroupMemberNode) replaceIncludes(mn, bOp, blazeGraphIncludeExample));
				}
			}
//			nq.setLeftArg(visit(nq.getChildren(), bOp));
//			nq.setRightArg(visit(nq.getRightArg(), bOp));
			yield nq;
		}
		case NamedSubqueryInclude nsq -> {
			Object as = bOp.annotations().get("namedSet");
			if (nsq.annotations().get("namedSet").equals(as)) {
				SubqueryRoot sqr = new SubqueryRoot((QueryType) bOp.annotations().get("queryType"));
				sqr.setGraphPattern((GraphPatternGroup<IGroupMemberNode>) bOp.annotations().get("graphPattern"));
				Matcher m = Pattern.compile("(INCLUDE|include)\s+" + as).matcher(blazeGraphIncludeExample);
				if (m.find()) {
					Pattern origP = Pattern.compile("(?:(?:WITH|with)\\s*\\{([\\s\\S]*?)\\}\s+AS\s+" + as + ")",
							Pattern.MULTILINE);
					Matcher orig = origP.matcher(blazeGraphIncludeExample);
					if (orig.find()) {
						String r = m.replaceAll('{' + orig.group(1) + '}');
						blazeGraphIncludeExample.setLength(0);
						blazeGraphIncludeExample.append(r);
					}
					blazeGraphIncludeExample.delete(orig.start(), orig.end());
				}
				yield sqr;
			}
			yield nsq;
		}
		default -> astContainer;
		};
	}
}
