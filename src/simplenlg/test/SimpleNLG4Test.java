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

import junit.framework.TestCase;

import org.junit.Before;

import simplenlg.framework.PhraseElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.lexicon.NIHDBLexicon;
import simplenlg.lexicon.XMLLexicon;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.Realiser;

/**
 * This class is the base class for all JUnit simplenlg.test cases for
 * simplenlg. It sets up a a JUnit fixture, i.e. the basic objects (basic
 * constituents) that all other tests can use.
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

 * @author agatt
 */
public abstract class SimpleNLG4Test extends TestCase {

	/** The realiser. */
	Realiser realiser;

	NLGFactory phraseFactory;
	
	Lexicon lexicon;
	
	/** The pro test2. */
	PhraseElement man, woman, dog, boy, np4, np5, np6, proTest1, proTest2;

	/** The salacious. */
	PhraseElement beautiful, stunning, salacious;

	/** The under the table. */
	PhraseElement onTheRock, behindTheCurtain, inTheRoom, underTheTable;

	/** The say. */
	VPPhraseSpec kick, kiss, walk, talk, getUp, fallDown, give, say;

	/**
	 * Instantiates a new simplenlg test.
	 * 
	 * @param name
	 *            the name
	 */
	public SimpleNLG4Test(String name) {
		super(name);
	}

	/**
	 * Set up the variables we'll need for this simplenlg.test to run (Called
	 * automatically by JUnit)
	 */
	@Override
	@Before
	protected void setUp() {
		//this.lexicon = new NIHDBLexicon("E:\\NIHDB\\lexAccess2009"); // NIH lexicon
		//lexicon = new XMLLexicon("E:\\NIHDB\\default-lexicon.xml");    // default XML lexicon
		lexicon = new XMLLexicon();  // built in lexicon
		this.phraseFactory = new NLGFactory(this.lexicon);
		this.realiser = new Realiser(this.lexicon);
		this.realiser.setFormatter(null);
		
		this.man = this.phraseFactory.createNounPhrase("the", "man"); //$NON-NLS-1$ //$NON-NLS-2$
		this.woman = this.phraseFactory.createNounPhrase("the", "woman");  //$NON-NLS-1$//$NON-NLS-2$
		this.dog = this.phraseFactory.createNounPhrase("the", "dog"); //$NON-NLS-1$ //$NON-NLS-2$
		this.boy = this.phraseFactory.createNounPhrase("the", "boy"); //$NON-NLS-1$ //$NON-NLS-2$

		this.beautiful = this.phraseFactory.createAdjectivePhrase("beautiful"); //$NON-NLS-1$
		this.stunning = this.phraseFactory.createAdjectivePhrase("stunning"); //$NON-NLS-1$
		this.salacious = this.phraseFactory.createAdjectivePhrase("salacious"); //$NON-NLS-1$

		this.onTheRock = this.phraseFactory.createPrepositionPhrase("on"); //$NON-NLS-1$
		this.np4 = this.phraseFactory.createNounPhrase("the", "rock"); //$NON-NLS-1$ //$NON-NLS-2$
		this.onTheRock.addComplement(this.np4);

		this.behindTheCurtain = this.phraseFactory.createPrepositionPhrase("behind"); //$NON-NLS-1$
		this.np5 = this.phraseFactory.createNounPhrase("the", "curtain"); //$NON-NLS-1$ //$NON-NLS-2$
		this.behindTheCurtain.addComplement(this.np5);

		this.inTheRoom = this.phraseFactory.createPrepositionPhrase("in"); //$NON-NLS-1$
		this.np6 = this.phraseFactory.createNounPhrase("the", "room"); //$NON-NLS-1$ //$NON-NLS-2$
		this.inTheRoom.addComplement(this.np6);

		this.underTheTable = this.phraseFactory.createPrepositionPhrase("under"); //$NON-NLS-1$
		this.underTheTable.addComplement(this.phraseFactory.createNounPhrase("the", "table")); //$NON-NLS-1$ //$NON-NLS-2$

		this.proTest1 = this.phraseFactory.createNounPhrase("the", "singer"); //$NON-NLS-1$ //$NON-NLS-2$
		this.proTest2 = this.phraseFactory.createNounPhrase("some", "person"); //$NON-NLS-1$ //$NON-NLS-2$

		this.kick = this.phraseFactory.createVerbPhrase("kick"); //$NON-NLS-1$
		this.kiss = this.phraseFactory.createVerbPhrase("kiss"); //$NON-NLS-1$
		this.walk = this.phraseFactory.createVerbPhrase("walk"); //$NON-NLS-1$
		this.talk = this.phraseFactory.createVerbPhrase("talk"); //$NON-NLS-1$
		this.getUp = this.phraseFactory.createVerbPhrase("get up"); //$NON-NLS-1$
		this.fallDown = this.phraseFactory.createVerbPhrase("fall down"); //$NON-NLS-1$
		this.give = this.phraseFactory.createVerbPhrase("give"); //$NON-NLS-1$
		this.say = this.phraseFactory.createVerbPhrase("say"); //$NON-NLS-1$
	}
}
