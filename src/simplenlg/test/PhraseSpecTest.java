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

import org.junit.Test;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.InflectedWordElement;
import simplenlg.framework.NLGElement;
import simplenlg.framework.PhraseElement;
import simplenlg.framework.StringElement;
import simplenlg.framework.WordElement;
import simplenlg.phrasespec.SPhraseSpec;

/**
 * test suite for simple XXXPhraseSpec classes
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

 * @author ereiter
 * 
 */

public class PhraseSpecTest extends SimpleNLG4Test {

	public PhraseSpecTest(String name) {
		super(name);
	}

	/**
	 * Test SPhraseSpec
	 */
	@Test
	public void testSPhraseSpec() {
		
		// simple test of methods
		SPhraseSpec c1 = (SPhraseSpec) phraseFactory.createClause();
		c1.setVerb("give");
		c1.setSubject("John");
		c1.setObject("an apple");
		c1.setIndirectObject("Mary");
		c1.setTense(Tense.PAST);
		c1.setNegated(true);
		
		// check getXXX methods
		Assert.assertEquals("give",  getBaseForm(c1.getVerb()));
		Assert.assertEquals("John", getBaseForm(c1.getSubject()));
		Assert.assertEquals("an apple", getBaseForm(c1.getObject()));
		Assert.assertEquals("Mary", getBaseForm(c1.getIndirectObject()));
		
		Assert.assertEquals("John did not give Mary an apple", this.realiser //$NON-NLS-1$
				.realise(c1).getRealisation());
		

		
		// test modifier placement
		SPhraseSpec c2 = (SPhraseSpec) phraseFactory.createClause();
		c2.setVerb("see");
		c2.setSubject("the man");
		c2.setObject("me");
		c2.addModifier("fortunately");
		c2.addModifier("quickly");
		c2.addModifier("in the park");
		// try setting tense directly as a feature
		c2.setFeature(Feature.TENSE, Tense.PAST);
		Assert.assertEquals("fortunately the man quickly saw me in the park", this.realiser //$NON-NLS-1$
				.realise(c2).getRealisation());
	}

	// get string for head of constituent
	private String getBaseForm(NLGElement constituent) {
		if (constituent == null)
			return null;
		else if (constituent instanceof StringElement)
			return constituent.getRealisation();
		else if (constituent instanceof WordElement)
			return ((WordElement)constituent).getBaseForm();
		else if (constituent instanceof InflectedWordElement)
			return getBaseForm(((InflectedWordElement)constituent).getBaseWord());
		else if (constituent instanceof PhraseElement)
			return getBaseForm(((PhraseElement)constituent).getHead());
		else
			return null;
	}
}
