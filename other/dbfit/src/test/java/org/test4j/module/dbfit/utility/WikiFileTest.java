package org.test4j.module.dbfit.utility;

import org.test4j.module.dbfit.utility.WikiFile;
import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

@Test(groups = "test4j")
public class WikiFileTest extends Test4J {

	@Test
	public void testFindWikiFile() throws Exception {
		WikiFile wiki = WikiFile.findWikiFile(null, "dbfit/jar/file/test.wiki");
		want.object(wiki).propertyEq("wikiUrl", "dbfit/jar/file/test.wiki").propertyEq("wikiName", "test");
	}

	@Test
	public void testReadWikiContent() throws Exception {
		WikiFile wiki = WikiFile.findWikiFile(null, "dbfit/jar/file/test.wiki");
		String content = wiki.readWikiContent();
		want.string(content).notNull().contains("|connect|");
	}

}
