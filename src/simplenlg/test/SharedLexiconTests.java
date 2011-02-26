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
import simplenlg.features.LexicalFeature;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.Lexicon;

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
public class SharedLexiconTests {

	public static void doBasicTests(Lexicon lexicon) {
		// test getWords. Should be 2 "can" (of any cat), 1 noun tree, 0 adj
		// trees
		Assert.assertEquals(3, lexicon.getWords("can").size());
		Assert.assertEquals(1, lexicon.getWords("can", LexicalCategory.NOUN)
				.size());
		Assert.assertEquals(0, lexicon.getWords("can",
				LexicalCategory.ADJECTIVE).size());

		// below test removed as standard morph variants no longer recorded in lexicon
		//WordElement early = lexicon.getWord("early", LexicalCategory.ADJECTIVE);
		//Assert.assertEquals("earlier", early.getFeatureAsString(Feature.COMPARATIVE));
		
		// test getWord. Comparative of ADJ "good" is "better", superlative is
		// "best", this is a qualitative and predicative adjective
		WordElement good = lexicon.getWord("good", LexicalCategory.ADJECTIVE);
		Assert.assertEquals("better", good
				.getFeatureAsString(LexicalFeature.COMPARATIVE));
		Assert.assertEquals("best", good
				.getFeatureAsString(LexicalFeature.SUPERLATIVE));
		Assert.assertEquals(true, good.getFeatureAsBoolean(LexicalFeature.QUALITATIVE)
				.booleanValue());
		Assert.assertEquals(true, good.getFeatureAsBoolean(LexicalFeature.PREDICATIVE)
				.booleanValue());
		Assert.assertEquals(false, good.getFeatureAsBoolean(LexicalFeature.COLOUR)
				.booleanValue());
		Assert.assertEquals(false, good
				.getFeatureAsBoolean(LexicalFeature.CLASSIFYING).booleanValue());

		// test getWord. There is only one "woman", and its plural is "women".
		// It is not an acronym, not proper, and countable
		WordElement woman = lexicon.getWord("woman");
		Assert.assertEquals("women", woman.getFeatureAsString(LexicalFeature.PLURAL));
		Assert.assertEquals(null, woman.getFeatureAsString(LexicalFeature.ACRONYM_OF));
		Assert.assertEquals(false, woman.getFeatureAsBoolean(LexicalFeature.PROPER)
				.booleanValue());
		Assert.assertEquals(false, woman.getFeatureAsBoolean(LexicalFeature.NON_COUNT)
				.booleanValue());

		// test getWord. Noun "sand" is non-count
		WordElement sand = lexicon.getWord("sand", LexicalCategory.NOUN);
		Assert.assertEquals(true, sand.getFeatureAsBoolean(LexicalFeature.NON_COUNT)
				.booleanValue());

		// test hasWord
		Assert.assertEquals(true, lexicon.hasWord("tree")); // "tree" exists
		Assert.assertEquals(false, lexicon.hasWord("tree",
				LexicalCategory.ADVERB)); // but not as an adverb

		// test getWordByID; quickly, also check that this is a verb_modifier
		WordElement quickly = lexicon.getWordByID("E0051632");
		Assert.assertEquals("quickly", quickly.getBaseForm());
		Assert.assertEquals(LexicalCategory.ADVERB, quickly.getCategory());
		Assert.assertEquals(true, quickly.getFeatureAsBoolean(
				LexicalFeature.VERB_MODIFIER).booleanValue());
		Assert.assertEquals(false, quickly.getFeatureAsBoolean(
				LexicalFeature.SENTENCE_MODIFIER).booleanValue());
		Assert.assertEquals(false, quickly.getFeatureAsBoolean(
				LexicalFeature.INTENSIFIER).booleanValue());

		// test getWordFromVariant, verb type (tran or intran, not ditran)
		WordElement eat = lexicon.getWordFromVariant("eating");
		Assert.assertEquals("eat", eat.getBaseForm());
		Assert.assertEquals(LexicalCategory.VERB, eat.getCategory());
		Assert.assertEquals(true, eat.getFeatureAsBoolean(LexicalFeature.INTRANSITIVE)
				.booleanValue());
		Assert.assertEquals(true, eat.getFeatureAsBoolean(LexicalFeature.TRANSITIVE)
				.booleanValue());
		Assert.assertEquals(false, eat
				.getFeatureAsBoolean(LexicalFeature.DITRANSITIVE).booleanValue());

		// test BE is handled OK
		Assert.assertEquals("been", lexicon.getWordFromVariant("is",
				LexicalCategory.VERB).getFeatureAsString(
				LexicalFeature.PAST_PARTICIPLE));

		// test modal
		WordElement can = lexicon.getWord("can", LexicalCategory.MODAL);
		Assert.assertEquals("could", can.getFeatureAsString(LexicalFeature.PAST));

		// test non-existent word
		Assert.assertEquals(0, lexicon.getWords("akjmchsgk").size());

		// test lookup word method
		Assert.assertEquals(lexicon.lookupWord("say", LexicalCategory.VERB)
				.getBaseForm(), "say");
		Assert.assertEquals(lexicon.lookupWord("said", LexicalCategory.VERB)
				.getBaseForm(), "say");
		Assert.assertEquals(lexicon
				.lookupWord("E0054448", LexicalCategory.VERB).getBaseForm(),
				"say");
	}

}
