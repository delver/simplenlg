/*
 * 
 * Copyright (C) 2010, University of Aberdeen
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package simplenlg.test;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simplenlg.features.LexicalFeature;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.Lexicon;
import simplenlg.lexicon.MultipleLexicon;
import simplenlg.lexicon.NIHDBLexicon;
import simplenlg.lexicon.XMLLexicon;

/**
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
 * @author D. Westwater, Data2Text Ltd
 *
 */
public class MultipleLexiconTest {

	// NIH, XML lexicon location
	static String DB_FILENAME = "E:\\NIHDB\\lexAccess2009";
	static String XML_FILENAME = "E:\\NIHDB\\default-lexicon.xml";
	
	// multi lexicon
	MultipleLexicon lexicon;


	@Before
	public void setUp() throws Exception {
		this.lexicon = new MultipleLexicon(new XMLLexicon(XML_FILENAME), new NIHDBLexicon(DB_FILENAME));
	}

	@After
	public void tearDown() throws Exception {
		lexicon.close();
	}

	@Test
	public void testBasics() {
		SharedLexiconTests.doBasicTests(lexicon);
	}
	
	@Test
	public void testMultipleSpecifics() {
		// try to get word which is only in NIH lexicon
		WordElement UK = lexicon.getWord("UK");
		Assert.assertEquals("United Kingdom", UK.getFeatureAsString(LexicalFeature.ACRONYM_OF));

		// test alwaysSearchAll flag
		boolean alwaysSearchAll = lexicon.isAlwaysSearchAll();
		
		// tree as noun exists in both, but as verb only in NIH
		lexicon.setAlwaysSearchAll(true);
		Assert.assertEquals(3, lexicon.getWords("tree").size()); // 3 = once in XML plus twice in NIH

		lexicon.setAlwaysSearchAll(false);
		Assert.assertEquals(1, lexicon.getWords("tree").size());

		// restore flag to original state
		lexicon.setAlwaysSearchAll(alwaysSearchAll);	
	}


}
