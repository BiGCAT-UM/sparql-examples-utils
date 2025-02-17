package swiss.sib.rdf.sparql.examples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.model.vocabulary.SHACL;

import swiss.sib.rdf.sparql.examples.vocabularies.SIB;
import swiss.sib.rdf.sparql.examples.vocabularies.SchemaDotOrg;

/**
 * Convert SPARQL queries in RDF to RQ format.
 * Adding grlc.io metadata.
 */
public class SparqlInRdfToRq {
	private SparqlInRdfToRq() {

	}

	/**
	 * {@see https://grlc.io/}
	 * @param ex the model containing all prefixes and query
	 * @return a rq formatted list of strings that should be concatenated later.
	 */
	public static List<String> asRq(Model ex) {
		List<String> rq = new ArrayList<>();

		streamOf(ex, null, RDF.TYPE, SHACL.SPARQL_EXECUTABLE).map(Statement::getSubject).distinct()
				.peek(s -> rq.add("#+ id: " + s.stringValue())).forEach(s -> {
					streamOf(ex, s, RDFS.COMMENT, null).map(Statement::getObject).map(Value::stringValue)
							.map(o -> "#+ description: " + o.replace("\n", " ").replace("\r", "")).forEach(rq::add);

					String keywords = streamOf(ex, s, SchemaDotOrg.KEYWORDS, null).map(Statement::getObject).map(Value::stringValue)
							.collect(Collectors.joining("\n#+   - "));
					if (!keywords.isEmpty()) {
						rq.add("#+ tags:");
						rq.add("#+   - " + keywords);
					}
					// Pick the first target only
					streamOf(ex, s, SchemaDotOrg.TARGET, null).map(Statement::getObject).map(Value::stringValue)
						.map(o -> "#+ endpoint: " + o).findFirst().ifPresent(rq::add);
					Stream.of(SHACL.ASK, SHACL.SELECT, SHACL.CONSTRUCT, SIB.DESCRIBE)
							.flatMap(qt -> streamOf(ex, s, qt, null)).map(Statement::getObject)
							.map(Value::stringValue).map(q -> {
								ArrayList<String> l = new ArrayList<>();
								l.add(q);
								return l.stream().collect(Collectors.joining("\n"));
							}).forEach(q -> {
								if (q.length() > 600) {
									//We hack in here that for small queries we want GET for cache performance
									rq.add("#+ method: GET");
								}
								rq.add(q);
							});
				});
		return rq;
	}

	/**
     * Add prefixes to a raw SPARQL query string
	 * @param rq
     **/
	public static void addPrefixes(String query, Model ex, List<String> rq) {
		Iterator<Statement> iterator = streamOf(ex, null, SHACL.PREFIX_PROP, null).iterator();
		List<String> prefixes = new ArrayList<>();

		while (iterator.hasNext()) {
			Statement n = iterator.next();
			Resource ns = n.getSubject();
			String nos = n.getObject().stringValue() + ':';

			if (queryContainsPrefix(query, nos)) {
				prefixes.add(streamOf(ex, ns, SHACL.NAMESPACE_PROP, null).map(Statement::getObject)
						.map(Value::stringValue).map(s -> "PREFIX "+nos+" <"+s+'>').collect(Collectors.joining()));
			}
		}
		prefixes.sort(String::compareTo);
		rq.addAll(prefixes);
		rq.add("");
		rq.add(query);
	}

	public static boolean queryContainsPrefix(String query, String prefix) {
		int indexOf = query.indexOf(prefix);
		if (indexOf == 0) {
			return true;
		} else if (indexOf >= 0) {
			//Make sure that the prefix is complete to avoid matching p: when prefix is actually up:
			//so the character should be a tab, space, forward slash, closing bracket or pipe
			while(indexOf >= 0 ) {
				if (isPrefixInUse(query, indexOf))
					return true;
				indexOf = query.indexOf(prefix, indexOf+1);
			}
			return false;
		}
		return false;
	}

	private static boolean isPrefixInUse(String query, int indexOf) {
		char cb = query.charAt(indexOf - 1);
		return cb == '\t' || cb == ' ' || cb == '/' || cb == ')' || cb == '|' || cb == '(' || cb == '=' || cb == '^';
	}

	public static Stream<Statement> streamOf(Model ex, Resource s, IRI p, Value o) {
		return StreamSupport.stream(ex.getStatements(s, p, o).spliterator(), false);
	}
}
