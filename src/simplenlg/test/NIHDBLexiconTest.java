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
 * <hr>
 * 
 * <p>
 * Copyright (C) 2010, University of Aberdeen
 * </p>
 * 
 * <p>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * </p>
 * 
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * </p>
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License in the zip
 * file. If not, see <a
 * href="http://www.gnu.org/licenses/">www.gnu.org/licenses</a>.
 * </p>
 * 
 * <p>
 * For more details on SimpleNLG visit the project website at <a
 * href="http://www.csd.abdn.ac.uk/research/simplenlg/"
 * >www.csd.abdn.ac.uk/research/simplenlg</a> or email Dr Ehud Reiter at
 * e.reiter@abdn.ac.uk
 * </p>
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
