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

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.InflectedWordElement;
import simplenlg.framework.LexicalCategory;
import simplenlg.phrasespec.AdjPhraseSpec;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class AgfaTests extends SimpleNLG4Test {

	public AgfaTests(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void testParticipleModifier() {
		
		String verb = "associate";
		VPPhraseSpec adjP = this.phraseFactory.createVerbPhrase(verb);
		adjP.setFeature(Feature.TENSE, Tense.PAST);
		
		NPPhraseSpec np = this.phraseFactory.createNounPhrase("a", "thrombus");
		np.addPreModifier(adjP);
		String realised = this.realiser.realise(np).getRealisation();
		System.out.println(realised);
		// cch TESTING The following line doesn't work when the lexeme is a
		// verb.
		// morphP.preMod.Add(new AdjPhraseSpec((Lexeme)modifier));

		// It doesn't work for verb "associate" as adjective past participle.
		// Instead of realizing as "associated" it realizes as "ed".
		// Need to use verb phrase.

		// cch TODO : handle general case making phrase type corresponding to
		// lexeme category and usage.
	}
}
