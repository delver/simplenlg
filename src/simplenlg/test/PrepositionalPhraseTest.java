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
import simplenlg.framework.CoordinatedPhraseElement;

// TODO: Auto-generated Javadoc
/**
 * This class groups together some tests for prepositional phrases and
 * coordinate prepositional phrases.
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
public class PrepositionalPhraseTest extends SimpleNLG4Test {

	/**
	 * Instantiates a new pP test.
	 * 
	 * @param name
	 *            the name
	 */
	public PrepositionalPhraseTest(String name) {
		super(name);
	}

	/**
	 * Basic test for the pre-set PP fixtures.
	 */
	@Test
	public void testBasic() {
		Assert.assertEquals("in the room", this.realiser //$NON-NLS-1$
				.realise(this.inTheRoom).getRealisation());
		Assert.assertEquals("behind the curtain", this.realiser //$NON-NLS-1$
				.realise(this.behindTheCurtain).getRealisation());
		Assert.assertEquals("on the rock", this.realiser //$NON-NLS-1$
				.realise(this.onTheRock).getRealisation());
	}

	/**
	 * Test for coordinate NP complements of PPs.
	 */
	@Test
	public void testComplementation() {
		this.inTheRoom.clearComplements();
		this.inTheRoom.addComplement(new CoordinatedPhraseElement(
				this.phraseFactory.createNounPhrase("the", "room"), //$NON-NLS-1$ //$NON-NLS-2$
				this.phraseFactory.createNounPhrase("a", "car"))); //$NON-NLS-1$//$NON-NLS-2$
		Assert.assertEquals("in the room and a car", this.realiser //$NON-NLS-1$
				.realise(this.inTheRoom).getRealisation());
	}

	/**
	 * Test for PP coordination.
	 */
	public void testCoordination() {
		// simple coordination

		CoordinatedPhraseElement coord1 = new CoordinatedPhraseElement(
				this.inTheRoom, this.behindTheCurtain);
		Assert.assertEquals("in the room and behind the curtain", this.realiser //$NON-NLS-1$
				.realise(coord1).getRealisation());

		// change the conjunction
		coord1.setFeature(Feature.CONJUNCTION, "or"); //$NON-NLS-1$
		Assert.assertEquals("in the room or behind the curtain", this.realiser //$NON-NLS-1$
				.realise(coord1).getRealisation());

		// new coordinate
		CoordinatedPhraseElement coord2 = new CoordinatedPhraseElement(
				this.onTheRock, this.underTheTable);
		coord2.setFeature(Feature.CONJUNCTION, "or"); //$NON-NLS-1$
		Assert.assertEquals("on the rock or under the table", this.realiser //$NON-NLS-1$
				.realise(coord2).getRealisation());

		// coordinate two coordinates
		CoordinatedPhraseElement coord3 = new CoordinatedPhraseElement(coord1,
				coord2);

		String text = this.realiser.realise(coord3).getRealisation();
		Assert
				.assertEquals(
						"in the room or behind the curtain and on the rock or under the table", //$NON-NLS-1$
						text);
	}
}
