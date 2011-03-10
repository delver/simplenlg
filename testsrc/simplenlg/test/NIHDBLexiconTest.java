/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is "Simplenlg".
 *
 * The Initial Developer of the Original Code is Ehud Reiter, Albert Gatt and Dave Westwater.
 * Portions created by Ehud Reiter, Albert Gatt and Dave Westwater are Copyright (C) 2010-11 The University of Aberdeen. All Rights Reserved.
 *
 * Contributor(s): Ehud Reiter, Albert Gatt, Dave Wewstwater, Roman Kutlak, Margaret Mitchell.
 */

package simplenlg.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simplenlg.features.LexicalFeature;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.Lexicon;
import simplenlg.lexicon.NIHDBLexicon;

// TODO: Auto-generated Javadoc
/**
 * Tests for NIHDBLexicon
 * 
 * @author Ehud Reiter
 */
public class NIHDBLexiconTest extends TestCase {

	// lexicon object -- an instance of Lexicon
	NIHDBLexicon lexicon = null;

	// DB location
	static String DB_FILENAME = "E:\\NIHDB\\lexAccess2009";

	@Override
	@Before
	/*
	 * * Sets up the accessor and runs it -- takes ca. 26 sec
	 */
	public void setUp() {
		this.lexicon = new NIHDBLexicon(DB_FILENAME);
	}

	/* close the lexicon
	 */
	@Override
	@After
	public void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		if (lexicon!=null)
			lexicon.close();
	}
	
	@Test
	public void testBasics() {
		SharedLexiconTests.doBasicTests(lexicon);
	}
	
	@Test
	public void testNIHSpecifics() {
		// test getWord.  There is only one "UK", it is an acronym for "United Kingdom"
		WordElement UK = lexicon.getWord("UK");
		Assert.assertEquals("United Kingdom", UK.getFeatureAsString(LexicalFeature.ACRONYM_OF));

		// test keepStandardInflection flag
		boolean keepInflectionsFlag = lexicon.isKeepStandardInflections();
		
		lexicon.setKeepStandardInflections(true);
		WordElement dog = lexicon.getWord("dog", LexicalCategory.NOUN);
		Assert.assertEquals("dogs", dog.getFeatureAsString(LexicalFeature.PLURAL));

		lexicon.setKeepStandardInflections(false);
		WordElement cat = lexicon.getWord("cat", LexicalCategory.NOUN);
		Assert.assertEquals(null, cat.getFeatureAsString(LexicalFeature.PLURAL));

		// restore flag to original state
		lexicon.setKeepStandardInflections(keepInflectionsFlag);	
	}

}
