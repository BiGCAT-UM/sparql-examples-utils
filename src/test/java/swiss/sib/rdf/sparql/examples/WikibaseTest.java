package swiss.sib.rdf.sparql.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import swiss.sib.rdf.sparql.examples.Wikibase.NamedTemplate;

public class WikibaseTest {

	@Test
	public void parseHtml() {
		Document document = Jsoup.parse(testHtml);
		
		
		Element selectFirst = document.selectFirst(NamedTemplate.SPARQL2.cssClass);
		String comment = Wikibase.makeThePreviousSiblingNodesTheLabel(selectFirst, NamedTemplate.SPARQL2);
		assertNotNull(comment);
		assertEquals(comment, "SPARQL 5");
		
		Element selectSecond = document.select(NamedTemplate.SPARQL2.cssClass).get(1);
		comment = Wikibase.makeThePreviousSiblingNodesTheLabel(selectSecond, NamedTemplate.SPARQL2);
		assertNotNull(comment);
		assertEquals(comment, "Sparql no tiltle");
	}
	
	
	private final String testHtml = """
		<div id="bodyContent" class="vector-body">
		<div id="siteSub" class="noprint">From Wikidata</div>
		<div id="contentSub"><div id="mw-content-subtitle"><div class="subpages">&lt; <bdi dir="ltr"><a href="/wiki/Special:MyLanguage/User:Wladek92" title="Special:MyLanguage/User:Wladek92">User:Wladek92</a></bdi> | <bdi dir="ltr"><a href="/wiki/Special:MyLanguage/User:Wladek92/test" title="Special:MyLanguage/User:Wladek92/test">test</a></bdi></div></div></div>
		<div id="contentSub2"></div>
		
		<div id="jump-to-nav"></div>
		<a class="mw-jump-link" href="#mw-head">Jump to navigation</a>
		<a class="mw-jump-link" href="#searchInput">Jump to search</a>
		<div id="mw-content-text" class="mw-body-content"><div class="mw-pt-translate-header noprint nomobile" dir="ltr" lang="en"><a href="/w/index.php?title=Special:Translate&amp;group=page-User%3AWladek92%2Ftest%2Fpage1&amp;action=page&amp;filter=&amp;action_source=translate_page" title="Special:Translate">Translate this page</a></div><div class="mw-content-ltr mw-parser-output" lang="en" dir="ltr"><div class="mw-pt-languages noprint navigation-not-searchable" lang="en" dir="ltr"><div class="mw-pt-languages-label">Other languages:</div><ul class="mw-pt-languages-list"><li><span class="mw-pt-languages-ui mw-pt-languages-selected mw-pt-progress mw-pt-progress--complete" lang="en" dir="ltr">English</span></li>
<li><a href="/wiki/User:Wladek92/test/page1/fr" class="mw-pt-progress mw-pt-progress--med" title="User:Wladek92/test/page1/fr (25% translated)" lang="fr" dir="ltr">français</a></li>
<li><a href="/wiki/User:Wladek92/test/page1/mk" class="mw-pt-progress mw-pt-progress--high" title="Корисник:Wladek92/проба/страница1 (75% translated)" lang="mk" dir="ltr">македонски</a></li></ul></div>
<div style="font-size: 1.3125em; width: 100%; display: table; border-bottom: 1px solid #cccccc; margin: 1em -16px 1em -16px; padding: 0 16px">
    <div style="width: auto; display: inline-block; float: left; border: 1px solid #cccccc; background-color: #f4f4f4; position: relative; top: 1px; padding: 0.5em 1em;"><a href="/wiki/Special:MyLanguage/Wikidata:Tools/OpenRefine/Editing/Schema_alignment" title="Special:MyLanguage/Wikidata:Tools/OpenRefine/Editing/Schema alignment">Schema alignment</a></div>
    <div style="width: auto; display: inline-block; float: left; border: 1px solid #cccccc; background-color: #f4f4f4; border-left: 0; position: relative; top: 1px; padding: 0.5em 1em;"><a href="/w/index.php?title=Wikidata:Special:MyLanguage/Tools/OpenRefine/Editing/Advanced_schemas&amp;action=edit&amp;redlink=1" class="new" title="Wikidata:Special:MyLanguage/Tools/OpenRefine/Editing/Advanced schemas (page does not exist)">Advanced schemas</a></div>
<div style="width: auto; display: inline-block; float: left; border: 1px solid #cccccc; background-color: #f4f4f4; border-left: 0; position: relative; top: 1px; padding: 0.5em 1em;"><a href="/w/index.php?title=Wikidata:Special:MyLanguage/Tools/OpenRefine/Editing/New_items&amp;action=edit&amp;redlink=1" class="new" title="Wikidata:Special:MyLanguage/Tools/OpenRefine/Editing/New items (page does not exist)">New items</a></div>
    <div style="width: auto; display: inline-block; float: left; border: 1px solid #cccccc; background-color: #f4f4f4; border-left: 0; position: relative; top: 1px; padding: 0.5em 1em;"><a href="/w/index.php?title=Wikidata:Special:MyLanguage/Tools/OpenRefine/Editing/Quality_assurance&amp;action=edit&amp;redlink=1" class="new" title="Wikidata:Special:MyLanguage/Tools/OpenRefine/Editing/Quality assurance (page does not exist)">Quality assurance</a></div>
    <div style="width: auto; display: inline-block; float: left; border: 1px solid #cccccc; background-color: #f4f4f4; border-left: 0; position: relative; top: 1px; padding: 0.5em 1em;"><a href="/w/index.php?title=Wikidata:Special:MyLanguage/Tools/OpenRefine/Editing/Uploading&amp;action=edit&amp;redlink=1" class="new" title="Wikidata:Special:MyLanguage/Tools/OpenRefine/Editing/Uploading (page does not exist)">Uploading</a></div>
</div>
<div class="mw-heading mw-heading3"><h3 id="Glaciers_map">Glaciers map</h3><span class="mw-editsection"><span class="mw-editsection-bracket">[</span><a href="/w/index.php?title=User:Wladek92/test/page1&amp;action=edit&amp;section=1" title="Edit section: Glaciers map"><span>edit</span></a><span class="mw-editsection-bracket">]</span></span></div>
<div class="mw-heading mw-heading4"><h4 id="SPARQL_5">SPARQL 5</h4><span class="mw-editsection"><span class="mw-editsection-bracket">[</span><a href="/w/index.php?title=User:Wladek92/test/page1&amp;action=edit&amp;section=2" title="Edit section: SPARQL 5"><span>edit</span></a><span class="mw-editsection-bracket">]</span></span></div>
<div style="font-size:smaller">
<ul><li>Items used: <a class="external text" href="https://www.wikidata.org/wiki/Special:EntityPage/Q35666">glacier (Q35666)</a>&nbsp; <span typeof="mw:File"><a href="https://reasonator.toolforge.org/?q=Q35666&amp;lang=en" title="View with Reasonator"><img alt="View with Reasonator" src="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/12px-Wikidata-Reasonator_small_logo.svg.png" decoding="async" width="12" height="12" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/18px-Wikidata-Reasonator_small_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/24px-Wikidata-Reasonator_small_logo.svg.png 2x" data-file-width="500" data-file-height="500"></a></span>&nbsp;<span typeof="mw:File"><a href="https://sqid.toolforge.org/#/view?id=Q35666&amp;lang=en" title="View with SQID"><img alt="View with SQID" src="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/25px-SQID_logo.svg.png" decoding="async" width="25" height="7" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/38px-SQID_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/50px-SQID_logo.svg.png 2x" data-file-width="179" data-file-height="48"></a></span></li></ul>
<ul><li>Properties used: <a class="external text" href="https://www.wikidata.org/wiki/Special:EntityPage/P31">instance of (P31)</a>&nbsp; <span typeof="mw:File"><a href="https://reasonator.toolforge.org/?q=P31&amp;lang=en" title="View with Reasonator"><img alt="View with Reasonator" src="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/12px-Wikidata-Reasonator_small_logo.svg.png" decoding="async" width="12" height="12" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/18px-Wikidata-Reasonator_small_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/24px-Wikidata-Reasonator_small_logo.svg.png 2x" data-file-width="500" data-file-height="500"></a></span>&nbsp;<span typeof="mw:File"><a href="https://sqid.toolforge.org/#/view?id=P31&amp;lang=en" title="View with SQID"><img alt="View with SQID" src="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/25px-SQID_logo.svg.png" decoding="async" width="25" height="7" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/38px-SQID_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/50px-SQID_logo.svg.png 2x" data-file-width="179" data-file-height="48"></a></span>, <a class="external text" href="https://www.wikidata.org/wiki/Special:EntityPage/P279">subclass of (P279)</a>&nbsp; <span typeof="mw:File"><a href="https://reasonator.toolforge.org/?q=P279&amp;lang=en" title="View with Reasonator"><img alt="View with Reasonator" src="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/12px-Wikidata-Reasonator_small_logo.svg.png" decoding="async" width="12" height="12" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/18px-Wikidata-Reasonator_small_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/24px-Wikidata-Reasonator_small_logo.svg.png 2x" data-file-width="500" data-file-height="500"></a></span>&nbsp;<span typeof="mw:File"><a href="https://sqid.toolforge.org/#/view?id=P279&amp;lang=en" title="View with SQID"><img alt="View with SQID" src="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/25px-SQID_logo.svg.png" decoding="async" width="25" height="7" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/38px-SQID_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/50px-SQID_logo.svg.png 2x" data-file-width="179" data-file-height="48"></a></span>, <a class="external text" href="https://www.wikidata.org/wiki/Special:EntityPage/P625">coordinate location (P625)</a>&nbsp; <span typeof="mw:File"><a href="https://reasonator.toolforge.org/?q=P625&amp;lang=en" title="View with Reasonator"><img alt="View with Reasonator" src="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/12px-Wikidata-Reasonator_small_logo.svg.png" decoding="async" width="12" height="12" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/18px-Wikidata-Reasonator_small_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/24px-Wikidata-Reasonator_small_logo.svg.png 2x" data-file-width="500" data-file-height="500"></a></span>&nbsp;<span typeof="mw:File"><a href="https://sqid.toolforge.org/#/view?id=P625&amp;lang=en" title="View with SQID"><img alt="View with SQID" src="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/25px-SQID_logo.svg.png" decoding="async" width="25" height="7" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/38px-SQID_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/50px-SQID_logo.svg.png 2x" data-file-width="179" data-file-height="48"></a></span>, <a class="external text" href="https://www.wikidata.org/wiki/Special:EntityPage/P2046">area (P2046)</a>&nbsp; <span typeof="mw:File"><a href="https://reasonator.toolforge.org/?q=P2046&amp;lang=en" title="View with Reasonator"><img alt="View with Reasonator" src="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/12px-Wikidata-Reasonator_small_logo.svg.png" decoding="async" width="12" height="12" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/18px-Wikidata-Reasonator_small_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/24px-Wikidata-Reasonator_small_logo.svg.png 2x" data-file-width="500" data-file-height="500"></a></span>&nbsp;<span typeof="mw:File"><a href="https://sqid.toolforge.org/#/view?id=P2046&amp;lang=en" title="View with SQID"><img alt="View with SQID" src="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/25px-SQID_logo.svg.png" decoding="async" width="25" height="7" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/38px-SQID_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/50px-SQID_logo.svg.png 2x" data-file-width="179" data-file-height="48"></a></span></li></ul>
<ul><li>Features used: <a class="external text" href="https://www.wikidata.org/wiki/Special:EntityPage/Q24515275">map (Q24515275)</a>&nbsp; <span typeof="mw:File"><a href="https://reasonator.toolforge.org/?q=Q24515275&amp;lang=en" title="View with Reasonator"><img alt="View with Reasonator" src="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/12px-Wikidata-Reasonator_small_logo.svg.png" decoding="async" width="12" height="12" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/18px-Wikidata-Reasonator_small_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Wikidata-Reasonator_small_logo.svg/24px-Wikidata-Reasonator_small_logo.svg.png 2x" data-file-width="500" data-file-height="500"></a></span>&nbsp;<span typeof="mw:File"><a href="https://sqid.toolforge.org/#/view?id=Q24515275&amp;lang=en" title="View with SQID"><img alt="View with SQID" src="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/25px-SQID_logo.svg.png" decoding="async" width="25" height="7" class="mw-file-element" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/38px-SQID_logo.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/e/ed/SQID_logo.svg/50px-SQID_logo.svg.png 2x" data-file-width="179" data-file-height="48"></a></span></li></ul></div>
<div class="mw-highlight mw-highlight-lang-sparql mw-content-ltr" dir="ltr"><pre><span></span><span class="c"># by Jura1, 2021-05-21</span>
<span class="c"># defaultView:Map{"hide":["?coor","?area_scale_km"], "layer": "?area_scale_km"}</span>
<span class="k">SELECT</span> <span class="nv">?item</span> <span class="nv">?itemLabel</span> <span class="nv">?itemDescription</span> <span class="nv">?coor</span> <span class="nv">?area_sqkm</span> <span class="nv">?area_scale_km</span>
<span class="p">{</span>
  <span class="nv">?item</span> <span class="nn">wdt</span><span class="p">:</span><span class="nt">P31</span><span class="o">/</span><span class="nn">wdt</span><span class="p">:</span><span class="nt">P279</span><span class="o">*</span> <span class="nn">wd</span><span class="p">:</span><span class="nt">Q35666</span> <span class="p">.</span>
  <span class="k">OPTIONAL</span> <span class="p">{</span> <span class="nv">?item</span> <span class="nn">wdt</span><span class="p">:</span><span class="nt">P625</span> <span class="nv">?coor</span> <span class="p">}</span>
  <span class="k">OPTIONAL</span> <span class="p">{</span> <span class="nv">?item</span> <span class="nn">p</span><span class="p">:</span><span class="nt">P2046</span> <span class="p">[</span> <span class="k">a</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">BestRank</span><span class="p">;</span> <span class="nn">psn</span><span class="p">:</span><span class="nt">P2046</span><span class="o">/</span><span class="nn">wikibase</span><span class="p">:</span><span class="nt">quantityAmount</span> <span class="nv">?area_sqm</span> <span class="p">]</span> <span class="p">.</span>
    <span class="k">BIND</span><span class="p">(</span> <span class="nf">ROUND</span><span class="p">(</span><span class="nv">?area_sqm</span><span class="o">/</span><span class="mi">10000</span><span class="p">)</span><span class="o">/</span><span class="mi">100</span> <span class="k">as</span> <span class="nv">?area_sqkm</span><span class="p">)</span>
    <span class="k">BIND</span><span class="p">(</span> <span class="nf">strlen</span><span class="p">(</span><span class="nf">str</span><span class="p">(</span><span class="nf">ROUND</span><span class="p">(</span><span class="nv">?area_sqm</span><span class="o">/</span><span class="mi">1000000</span><span class="p">)))</span> <span class="k">as</span> <span class="nv">?area_scale_km</span><span class="p">)</span>
  <span class="p">}</span>
  <span class="k">SERVICE</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">label</span> <span class="p">{</span> <span class="nn">bd</span><span class="p">:</span><span class="nt">serviceParam</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">language</span> <span class="s">"[AUTO_LANGUAGE],en"</span><span class="p">.</span> <span class="p">}</span>
<span class="p">}</span>
</pre></div>
<p><a class="external text" href="https://query.wikidata.org/#%23%20by%20Jura1%2C%202021-05-21%0A%23%20defaultView%3AMap%7B%22hide%22%3A%5B%22%3Fcoor%22%2C%22%3Farea_scale_km%22%5D%2C%20%22layer%22%3A%20%22%3Farea_scale_km%22%7D%0ASELECT%20%3Fitem%20%3FitemLabel%20%3FitemDescription%20%3Fcoor%20%3Farea_sqkm%20%3Farea_scale_km%0A%7B%0A%20%20%3Fitem%20wdt%3AP31%2Fwdt%3AP279%2A%20wd%3AQ35666%20.%0A%20%20OPTIONAL%20%7B%20%3Fitem%20wdt%3AP625%20%3Fcoor%20%7D%0A%20%20OPTIONAL%20%7B%20%3Fitem%20p%3AP2046%20%5B%20a%20wikibase%3ABestRank%3B%20psn%3AP2046%2Fwikibase%3AquantityAmount%20%3Farea_sqm%20%5D%20.%0A%20%20%20%20BIND%28%20ROUND%28%3Farea_sqm%2F10000%29%2F100%20as%20%3Farea_sqkm%29%0A%20%20%20%20BIND%28%20strlen%28str%28ROUND%28%3Farea_sqm%2F1000000%29%29%29%20as%20%3Farea_scale_km%29%0A%20%20%7D%0A%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%20%22%5BAUTO_LANGUAGE%5D%2Cen%22.%20%7D%0A%7D">Try it!</a>
</p>
<ul><li>Sparql no tiltle</li></ul>
<style data-mw-deduplicate="TemplateStyles:r1502159272">.mw-parser-output .wdt-sparql-container-collapsible{border:1px solid #BBBBBB}.mw-parser-output .wdt-sparql-container-collapsible pre{margin-top:2px}</style><div class="wdt-sparql-container"><div><div class="mw-highlight mw-highlight-lang-sparql mw-content-ltr" dir="ltr"><pre><span></span><span class="c"># by Jura1, 2021-05-21</span>
<span class="c"># defaultView:Map{"hide":["?coor","?area_scale_km"], "layer": "?area_scale_km"}</span>
<span class="k">SELECT</span> <span class="nv">?item</span> <span class="nv">?itemLabel</span> <span class="nv">?itemDescription</span> <span class="nv">?coor</span> <span class="nv">?area_sqkm</span> <span class="nv">?area_scale_km</span>
<span class="p">{</span>
  <span class="nv">?item</span> <span class="nn">wdt</span><span class="p">:</span><span class="nt">P31</span><span class="o">/</span><span class="nn">wdt</span><span class="p">:</span><span class="nt">P279</span><span class="o">*</span> <span class="nn">wd</span><span class="p">:</span><span class="nt">Q35666</span> <span class="p">.</span>
  <span class="k">OPTIONAL</span> <span class="p">{</span> <span class="nv">?item</span> <span class="nn">wdt</span><span class="p">:</span><span class="nt">P625</span> <span class="nv">?coor</span> <span class="p">}</span>
  <span class="k">OPTIONAL</span> <span class="p">{</span> <span class="nv">?item</span> <span class="nn">p</span><span class="p">:</span><span class="nt">P2046</span> <span class="p">[</span> <span class="k">a</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">BestRank</span><span class="p">;</span> <span class="nn">psn</span><span class="p">:</span><span class="nt">P2046</span><span class="o">/</span><span class="nn">wikibase</span><span class="p">:</span><span class="nt">quantityAmount</span> <span class="nv">?area_sqm</span> <span class="p">]</span> <span class="p">.</span>
    <span class="k">BIND</span><span class="p">(</span> <span class="nf">ROUND</span><span class="p">(</span><span class="nv">?area_sqm</span><span class="o">/</span><span class="mi">10000</span><span class="p">)</span><span class="o">/</span><span class="mi">100</span> <span class="k">as</span> <span class="nv">?area_sqkm</span><span class="p">)</span>
    <span class="k">BIND</span><span class="p">(</span> <span class="nf">strlen</span><span class="p">(</span><span class="nf">str</span><span class="p">(</span><span class="nf">ROUND</span><span class="p">(</span><span class="nv">?area_sqm</span><span class="o">/</span><span class="mi">1000000</span><span class="p">)))</span> <span class="k">as</span> <span class="nv">?area_scale_km</span><span class="p">)</span>
  <span class="p">}</span>
  <span class="k">SERVICE</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">label</span> <span class="p">{</span> <span class="nn">bd</span><span class="p">:</span><span class="nt">serviceParam</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">language</span> <span class="s">"[AUTO_LANGUAGE],en"</span><span class="p">.</span> <span class="p">}</span>
<span class="p">}</span>
</pre></div></div><a class="external text" href="https://query.wikidata.org/#%23%20by%20Jura1%2C%202021-05-21%0A%23%20defaultView%3AMap%7B%22hide%22%3A%5B%22%3Fcoor%22%2C%22%3Farea_scale_km%22%5D%2C%20%22layer%22%3A%20%22%3Farea_scale_km%22%7D%0ASELECT%20%3Fitem%20%3FitemLabel%20%3FitemDescription%20%3Fcoor%20%3Farea_sqkm%20%3Farea_scale_km%0A%7B%0A%20%20%3Fitem%20wdt%3AP31%2Fwdt%3AP279%2A%20wd%3AQ35666%20.%0A%20%20OPTIONAL%20%7B%20%3Fitem%20wdt%3AP625%20%3Fcoor%20%7D%0A%20%20OPTIONAL%20%7B%20%3Fitem%20p%3AP2046%20%5B%20a%20wikibase%3ABestRank%3B%20psn%3AP2046%2Fwikibase%3AquantityAmount%20%3Farea_sqm%20%5D%20.%0A%20%20%20%20BIND%28%20ROUND%28%3Farea_sqm%2F10000%29%2F100%20as%20%3Farea_sqkm%29%0A%20%20%20%20BIND%28%20strlen%28str%28ROUND%28%3Farea_sqm%2F1000000%29%29%29%20as%20%3Farea_scale_km%29%0A%20%20%7D%0A%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%20%22%5BAUTO_LANGUAGE%5D%2Cen%22.%20%7D%0A%7D"><span dir="ltr" lang="en">Try it!</span></a>  </div>
<ul><li>sparql  + title</li></ul>
<link rel="mw-deduplicated-inline-style" href="mw-data:TemplateStyles:r1502159272"><div class="wdt-sparql-container"><div><div class="mw-highlight mw-highlight-lang-sparql mw-content-ltr" dir="ltr"><pre><span></span><span class="c"># by Jura1, 2021-05-21</span>
<span class="c">#title:Glaciers map</span>
<span class="c"># defaultView:Map{"hide":["?coor","?area_scale_km"], "layer": "?area_scale_km"}</span>
<span class="k">SELECT</span> <span class="nv">?item</span> <span class="nv">?itemLabel</span> <span class="nv">?itemDescription</span> <span class="nv">?coor</span> <span class="nv">?area_sqkm</span> <span class="nv">?area_scale_km</span>
<span class="p">{</span>
  <span class="nv">?item</span> <span class="nn">wdt</span><span class="p">:</span><span class="nt">P31</span><span class="o">/</span><span class="nn">wdt</span><span class="p">:</span><span class="nt">P279</span><span class="o">*</span> <span class="nn">wd</span><span class="p">:</span><span class="nt">Q35666</span> <span class="p">.</span>
  <span class="k">OPTIONAL</span> <span class="p">{</span> <span class="nv">?item</span> <span class="nn">wdt</span><span class="p">:</span><span class="nt">P625</span> <span class="nv">?coor</span> <span class="p">}</span>
  <span class="k">OPTIONAL</span> <span class="p">{</span> <span class="nv">?item</span> <span class="nn">p</span><span class="p">:</span><span class="nt">P2046</span> <span class="p">[</span> <span class="k">a</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">BestRank</span><span class="p">;</span> <span class="nn">psn</span><span class="p">:</span><span class="nt">P2046</span><span class="o">/</span><span class="nn">wikibase</span><span class="p">:</span><span class="nt">quantityAmount</span> <span class="nv">?area_sqm</span> <span class="p">]</span> <span class="p">.</span>
    <span class="k">BIND</span><span class="p">(</span> <span class="nf">ROUND</span><span class="p">(</span><span class="nv">?area_sqm</span><span class="o">/</span><span class="mi">10000</span><span class="p">)</span><span class="o">/</span><span class="mi">100</span> <span class="k">as</span> <span class="nv">?area_sqkm</span><span class="p">)</span>
    <span class="k">BIND</span><span class="p">(</span> <span class="nf">strlen</span><span class="p">(</span><span class="nf">str</span><span class="p">(</span><span class="nf">ROUND</span><span class="p">(</span><span class="nv">?area_sqm</span><span class="o">/</span><span class="mi">1000000</span><span class="p">)))</span> <span class="k">as</span> <span class="nv">?area_scale_km</span><span class="p">)</span>
  <span class="p">}</span>
  <span class="k">SERVICE</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">label</span> <span class="p">{</span> <span class="nn">bd</span><span class="p">:</span><span class="nt">serviceParam</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">language</span> <span class="s">"[AUTO_LANGUAGE],en"</span><span class="p">.</span> <span class="p">}</span>
<span class="p">}</span>
</pre></div></div><a class="external text" href="https://query.wikidata.org/#%23%20by%20Jura1%2C%202021-05-21%0A%23title%3AGlaciers%20map%0A%23%20defaultView%3AMap%7B%22hide%22%3A%5B%22%3Fcoor%22%2C%22%3Farea_scale_km%22%5D%2C%20%22layer%22%3A%20%22%3Farea_scale_km%22%7D%0ASELECT%20%3Fitem%20%3FitemLabel%20%3FitemDescription%20%3Fcoor%20%3Farea_sqkm%20%3Farea_scale_km%0A%7B%0A%20%20%3Fitem%20wdt%3AP31%2Fwdt%3AP279%2A%20wd%3AQ35666%20.%0A%20%20OPTIONAL%20%7B%20%3Fitem%20wdt%3AP625%20%3Fcoor%20%7D%0A%20%20OPTIONAL%20%7B%20%3Fitem%20p%3AP2046%20%5B%20a%20wikibase%3ABestRank%3B%20psn%3AP2046%2Fwikibase%3AquantityAmount%20%3Farea_sqm%20%5D%20.%0A%20%20%20%20BIND%28%20ROUND%28%3Farea_sqm%2F10000%29%2F100%20as%20%3Farea_sqkm%29%0A%20%20%20%20BIND%28%20strlen%28str%28ROUND%28%3Farea_sqm%2F1000000%29%29%29%20as%20%3Farea_scale_km%29%0A%20%20%7D%0A%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%20%22%5BAUTO_LANGUAGE%5D%2Cen%22.%20%7D%0A%7D">Glaciers map</a>  </div>
<ul><li>sparql title translated</li></ul>
<link rel="mw-deduplicated-inline-style" href="mw-data:TemplateStyles:r1502159272"><div class="wdt-sparql-container"><div><div class="mw-highlight mw-highlight-lang-sparql mw-content-ltr" dir="ltr"><pre><span></span><span class="c"># by Jura1, 2021-05-21</span>
<span class="c">#title:Glaciers map</span>
<span class="c"># defaultView:Map{"hide":["?coor","?area_scale_km"], "layer": "?area_scale_km"}</span>
<span class="k">SELECT</span> <span class="nv">?item</span> <span class="nv">?itemLabel</span> <span class="nv">?itemDescription</span> <span class="nv">?coor</span> <span class="nv">?area_sqkm</span> <span class="nv">?area_scale_km</span>
<span class="p">{</span>
  <span class="nv">?item</span> <span class="nn">wdt</span><span class="p">:</span><span class="nt">P31</span><span class="o">/</span><span class="nn">wdt</span><span class="p">:</span><span class="nt">P279</span><span class="o">*</span> <span class="nn">wd</span><span class="p">:</span><span class="nt">Q35666</span> <span class="p">.</span>
  <span class="k">OPTIONAL</span> <span class="p">{</span> <span class="nv">?item</span> <span class="nn">wdt</span><span class="p">:</span><span class="nt">P625</span> <span class="nv">?coor</span> <span class="p">}</span>
  <span class="k">OPTIONAL</span> <span class="p">{</span> <span class="nv">?item</span> <span class="nn">p</span><span class="p">:</span><span class="nt">P2046</span> <span class="p">[</span> <span class="k">a</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">BestRank</span><span class="p">;</span> <span class="nn">psn</span><span class="p">:</span><span class="nt">P2046</span><span class="o">/</span><span class="nn">wikibase</span><span class="p">:</span><span class="nt">quantityAmount</span> <span class="nv">?area_sqm</span> <span class="p">]</span> <span class="p">.</span>
    <span class="k">BIND</span><span class="p">(</span> <span class="nf">ROUND</span><span class="p">(</span><span class="nv">?area_sqm</span><span class="o">/</span><span class="mi">10000</span><span class="p">)</span><span class="o">/</span><span class="mi">100</span> <span class="k">as</span> <span class="nv">?area_sqkm</span><span class="p">)</span>
    <span class="k">BIND</span><span class="p">(</span> <span class="nf">strlen</span><span class="p">(</span><span class="nf">str</span><span class="p">(</span><span class="nf">ROUND</span><span class="p">(</span><span class="nv">?area_sqm</span><span class="o">/</span><span class="mi">1000000</span><span class="p">)))</span> <span class="k">as</span> <span class="nv">?area_scale_km</span><span class="p">)</span>
  <span class="p">}</span>
  <span class="k">SERVICE</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">label</span> <span class="p">{</span> <span class="nn">bd</span><span class="p">:</span><span class="nt">serviceParam</span> <span class="nn">wikibase</span><span class="p">:</span><span class="nt">language</span> <span class="s">"[AUTO_LANGUAGE],en"</span><span class="p">.</span> <span class="p">}</span>
<span class="p">}</span>
</pre></div></div><a class="external text" href="https://query.wikidata.org/#%23%20by%20Jura1%2C%202021-05-21%0A%23title%3AGlaciers%20map%0A%23%20defaultView%3AMap%7B%22hide%22%3A%5B%22%3Fcoor%22%2C%22%3Farea_scale_km%22%5D%2C%20%22layer%22%3A%20%22%3Farea_scale_km%22%7D%0ASELECT%20%3Fitem%20%3FitemLabel%20%3FitemDescription%20%3Fcoor%20%3Farea_sqkm%20%3Farea_scale_km%0A%7B%0A%20%20%3Fitem%20wdt%3AP31%2Fwdt%3AP279%2A%20wd%3AQ35666%20.%0A%20%20OPTIONAL%20%7B%20%3Fitem%20wdt%3AP625%20%3Fcoor%20%7D%0A%20%20OPTIONAL%20%7B%20%3Fitem%20p%3AP2046%20%5B%20a%20wikibase%3ABestRank%3B%20psn%3AP2046%2Fwikibase%3AquantityAmount%20%3Farea_sqm%20%5D%20.%0A%20%20%20%20BIND%28%20ROUND%28%3Farea_sqm%2F10000%29%2F100%20as%20%3Farea_sqkm%29%0A%20%20%20%20BIND%28%20strlen%28str%28ROUND%28%3Farea_sqm%2F1000000%29%29%29%20as%20%3Farea_scale_km%29%0A%20%20%7D%0A%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%20%22%5BAUTO_LANGUAGE%5D%2Cen%22.%20%7D%0A%7D">Glaciers map</a>  </div>
<!-- 
NewPP limit report
Parsed by mw‐web.eqiad.main‐7475d6bc97‐8m42m
Cached time: 20241004130553
Cache expiry: 2592000
Reduced expiry: false
Complications: []
CPU time usage: 0.102 seconds
Real time usage: 0.170 seconds
Preprocessor visited node count: 1114/1000000
Post‐expand include size: 27108/2097152 bytes
Template argument size: 14996/2097152 bytes
Highest expansion depth: 9/100
Expensive parser function count: 4/500
Unstrip recursion depth: 0/20
Unstrip post‐expand size: 13774/5000000 bytes
Lua time usage: 0.030/10.000 seconds
Lua memory usage: 1291101/52428800 bytes
Number of Wikibase entities loaded: 0/500
-->
<!--
Transclusion expansion time report (%,ms,calls,template)
100.00%   79.789      1 -total
 68.19%   54.405      1 Template:SPARQL5
 61.09%   48.740      3 Template:ItemOrNot
 31.59%   25.204      3 Template:SPARQL
 25.39%   20.261     13 Template:TranslateThis
 10.60%    8.456      8 Template:SPARQLText
  6.30%    5.028      1 Template:Int_try_it
-->

<!-- Saved in parser cache with key wikidatawiki:pcache:idhash:90031361-0!canonical and timestamp 20241004130553 and revision id 2181916911. Rendering was triggered because: page-view
 -->
</div><!--esi <esi:include src="/esitest-fa8a495983347898/content" /> -->
<div class="printfooter" data-nosnippet="">Retrieved from "<a dir="ltr" href="https://www.wikidata.org/w/index.php?title=User:Wladek92/test/page1&amp;oldid=2181916911">https://www.wikidata.org/w/index.php?title=User:Wladek92/test/page1&amp;oldid=2181916911</a>"</div></div>
		<div id="catlinks" class="catlinks catlinks-allhidden" data-mw="interface"></div>
	</div>
			""";
}