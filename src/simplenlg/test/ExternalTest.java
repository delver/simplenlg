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

import org.junit.Test;

import simplenlg.features.Feature;
import simplenlg.features.Form;
import simplenlg.features.InternalFeature;
import simplenlg.features.InterrogativeType;
import simplenlg.features.NumberAgreement;
import simplenlg.features.Tense;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.PhraseElement;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

/**
 * Tests from third parties
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
 * @author ereiter
 * 
 */
public class ExternalTest extends SimpleNLG4Test {

	public ExternalTest(String name) {
		super(name);
	}

	/**
	 * Basic tests
	 * 
	 */
	@Test
	public void testForcher() {
		// Bjorn Forcher's tests
		this.phraseFactory.setLexicon(this.lexicon);
		PhraseElement s1 = this.phraseFactory.createClause(null, "associate",
				"Marie");
		s1.setFeature(Feature.PASSIVE, true);
		PhraseElement pp1 = this.phraseFactory.createPrepositionPhrase("with"); //$NON-NLS-1$
		pp1.addComplement("Peter"); //$NON-NLS-1$
		pp1.addComplement("Paul"); //$NON-NLS-1$
		s1.addPostModifier(pp1);

		Assert.assertEquals("Marie is associated with Peter and Paul", //$NON-NLS-1$
				this.realiser.realise(s1).getRealisation());
		SPhraseSpec s2 = this.phraseFactory.createClause();
		s2.setSubject(this.phraseFactory
				.createNounPhrase("Peter")); //$NON-NLS-1$
		s2.setVerb("have"); //$NON-NLS-1$
		s2.setObject("something to do"); //$NON-NLS-1$
		s2.addPostModifier(this.phraseFactory.createPrepositionPhrase(
				"with", "Paul")); //$NON-NLS-1$ //$NON-NLS-2$


		Assert.assertEquals("Peter has something to do with Paul", //$NON-NLS-1$
				this.realiser.realise(s2).getRealisation());
	}

	@Test
	public void testLu() {
		// Xin Lu's test
		this.phraseFactory.setLexicon(this.lexicon);
		PhraseElement s1 = this.phraseFactory.createClause("we", //$NON-NLS-1$
				"consider", //$NON-NLS-1$
				"John"); //$NON-NLS-1$
		s1.addPostModifier("a friend"); //$NON-NLS-1$

		Assert.assertEquals("we consider John a friend", this.realiser //$NON-NLS-1$
				.realise(s1).getRealisation());
	}

	@Test
	public void testDwight() {
		// Rachel Dwight's test
		this.phraseFactory.setLexicon(this.lexicon);

		PhraseElement noun4 = this.phraseFactory
				.createNounPhrase("FGFR3 gene in every cell"); //$NON-NLS-1$

		noun4.setDeterminer("the");

		PhraseElement prep1 = this.phraseFactory.createPrepositionPhrase(
				"of", noun4); //$NON-NLS-1$

		PhraseElement noun1 = this.phraseFactory.createNounPhrase(
				"the", "patient's mother"); //$NON-NLS-1$ //$NON-NLS-2$

		PhraseElement noun2 = this.phraseFactory.createNounPhrase(
				"the", "patient's father"); //$NON-NLS-1$ //$NON-NLS-2$

		PhraseElement noun3 = this.phraseFactory
				.createNounPhrase("changed copy"); //$NON-NLS-1$
		noun3.addPreModifier("one"); //$NON-NLS-1$
		noun3.addComplement(prep1);

		CoordinatedPhraseElement coordNoun1 = new CoordinatedPhraseElement(
				noun1, noun2);
		coordNoun1.setConjunction( "or"); //$NON-NLS-1$

		PhraseElement verbPhrase1 = this.phraseFactory.createVerbPhrase("have"); //$NON-NLS-1$
		verbPhrase1.setFeature(Feature.TENSE,Tense.PRESENT);

		PhraseElement sentence1 = this.phraseFactory.createClause(coordNoun1,
				verbPhrase1, noun3);

		Assert
				.assertEquals(
						"the patient's mother or the patient's father has one changed copy of the FGFR3 gene in every cell", //$NON-NLS-1$
						this.realiser.realise(sentence1).getRealisation());

		// Rachel's second test
		noun3 = this.phraseFactory.createNounPhrase("a", "gene test"); //$NON-NLS-1$ //$NON-NLS-2$
		noun2 = this.phraseFactory.createNounPhrase("an", "LDL test"); //$NON-NLS-1$ //$NON-NLS-2$
		noun1 = this.phraseFactory.createNounPhrase("the", "clinic"); //$NON-NLS-1$ //$NON-NLS-2$
		verbPhrase1 = this.phraseFactory.createVerbPhrase("perform"); //$NON-NLS-1$

		CoordinatedPhraseElement coord1 = new CoordinatedPhraseElement(noun2,
				noun3);
		sentence1 = this.phraseFactory.createClause(noun1, verbPhrase1, coord1);
		sentence1.setFeature(Feature.TENSE,Tense.PAST);

		Assert
				.assertEquals(
						"the clinic performed an LDL test and a gene test", this.realiser //$NON-NLS-1$
								.realise(sentence1).getRealisation());
	}

	@Test
	public void testNovelli() {
		// Nicole Novelli's test
		PhraseElement p = this.phraseFactory.createClause(
				"Mary", "chase", "George"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		PhraseElement pp = this.phraseFactory.createPrepositionPhrase(
				"in", "the park"); //$NON-NLS-1$ //$NON-NLS-2$
		p.addPostModifier(pp);

		Assert.assertEquals("Mary chases George in the park", this.realiser //$NON-NLS-1$
				.realise(p).getRealisation());

		// another question from Nicole
		SPhraseSpec run = this.phraseFactory.createClause(
				"you", "go", "running"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		run.setFeature(Feature.MODAL, "should"); //$NON-NLS-1$
		run.addPreModifier("really"); //$NON-NLS-1$
		SPhraseSpec think = this.phraseFactory.createClause("I", "think"); //$NON-NLS-1$ //$NON-NLS-2$
		think.setObject(run);
		run.setFeature(Feature.SUPRESSED_COMPLEMENTISER, true);

		String text = this.realiser.realise(think).getRealisation();
		Assert.assertEquals("I think you should really go running", text); //$NON-NLS-1$
	}

	@Test
	public void testPiotrek() {
		// Piotrek Smulikowski's test
		this.phraseFactory.setLexicon(this.lexicon);
		PhraseElement sent = this.phraseFactory.createClause(
				"I", "shoot", "the duck"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sent.setFeature(Feature.TENSE,Tense.PAST);

		PhraseElement loc = this.phraseFactory.createPrepositionPhrase(
				"at", "the Shooting Range"); //$NON-NLS-1$ //$NON-NLS-2$
		sent.addPostModifier(loc);
		sent.setFeature(Feature.CUE_PHRASE, "then"); //$NON-NLS-1$

		Assert.assertEquals("then I shot the duck at the Shooting Range", //$NON-NLS-1$
				this.realiser.realise(sent).getRealisation());
	}

	@Test
	public void testPrescott() {
		// Michael Prescott's test
		this.phraseFactory.setLexicon(this.lexicon);
		PhraseElement embedded = this.phraseFactory.createClause(
				"Jill", "prod", "Spot"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		PhraseElement sent = this.phraseFactory.createClause(
				"Jack", "see", embedded); //$NON-NLS-1$ //$NON-NLS-2$
		embedded.setFeature(Feature.SUPRESSED_COMPLEMENTISER, true);
		embedded.setFeature(Feature.FORM, Form.BARE_INFINITIVE);

		Assert.assertEquals("Jack sees Jill prod Spot", this.realiser //$NON-NLS-1$
				.realise(sent).getRealisation());
	}

	@Test
	public void testWissner() {
		// Michael Wissner's text

		setUp();

		PhraseElement p = this.phraseFactory.createClause("a wolf", "eat"); //$NON-NLS-1$ //$NON-NLS-2$
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHAT_OBJECT);
		Assert.assertEquals("what does a wolf eat", this.realiser.realise(p) //$NON-NLS-1$
				.getRealisation());

	}
	
	@Test
	public void testPhan() {
		// Thomas Phan's text

		setUp();

	      PhraseElement subjectElement = phraseFactory.createNounPhrase("I");
	      PhraseElement verbElement = phraseFactory.createVerbPhrase("run");

	      PhraseElement prepPhrase = phraseFactory.createPrepositionPhrase("from");
	      prepPhrase.addComplement("home");

	      verbElement.addComplement(prepPhrase);
	      SPhraseSpec newSentence = phraseFactory.createClause();
	      newSentence.setSubject(subjectElement);
	      newSentence.setVerbPhrase(verbElement);

		Assert.assertEquals("I run from home", this.realiser.realise(newSentence) //$NON-NLS-1$
				.getRealisation());

	}

	@Test
	public void testKerber() {
		// Frederic Kerber's tests
        SPhraseSpec sp =  phraseFactory.createClause("he", "need");
        SPhraseSpec secondSp = phraseFactory.createClause();
        secondSp.setVerb("build");
        secondSp.setObject("a house");
        secondSp.setFeature(Feature.FORM,Form.INFINITIVE);
        sp.setObject("stone");
        sp.addComplement(secondSp);
        Assert.assertEquals("he needs stone to build a house", this.realiser.realise(sp).getRealisation());
       
        SPhraseSpec sp2 =  phraseFactory.createClause("he", "give");
        sp2.setIndirectObject("I");
        sp2.setObject("the book");
        Assert.assertEquals("he gives me the book", this.realiser.realise(sp2).getRealisation());

	}
	
	@Test
	public void testStephenson() {
		// Bruce Stephenson's test
		SPhraseSpec qs2 = this.phraseFactory.createClause();
		qs2 = this.phraseFactory.createClause();
		qs2.setSubject("moles of Gold");
		qs2.setVerb("are");
		qs2.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
		qs2.setFeature(Feature.PASSIVE, false);
		qs2.setFeature(Feature.INTERROGATIVE_TYPE,InterrogativeType.HOW_MANY);
		qs2.setObject("in a 2.50 g sample of pure Gold");
		DocumentElement sentence = this.phraseFactory.createSentence(qs2);
		Assert.assertEquals("How many moles of Gold are in a 2.50 g sample of pure Gold?", this.realiser.realise(sentence).getRealisation());
	}
	
	@Test
	public void testPierre() {
		// John Pierre's test
		SPhraseSpec p = this.phraseFactory.createClause("Mary", "chase", "George");
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHAT_OBJECT);
		Assert.assertEquals("What does Mary chase?", realiser.realiseSentence(p));

		p = this.phraseFactory.createClause("Mary", "chase", "George");
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.YES_NO);
		Assert.assertEquals("Does Mary chase George?", realiser.realiseSentence(p));

		p = this.phraseFactory.createClause("Mary", "chase", "George");
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHERE);
		Assert.assertEquals("Where does Mary chase George?", realiser.realiseSentence(p));

		p = this.phraseFactory.createClause("Mary", "chase", "George");
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHY);
		Assert.assertEquals("Why does Mary chase George?", realiser.realiseSentence(p));

		p = this.phraseFactory.createClause("Mary", "chase", "George");
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.HOW);
		Assert.assertEquals("How does Mary chase George?", realiser.realiseSentence(p));


	}
}
