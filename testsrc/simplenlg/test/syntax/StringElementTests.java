package simplenlg.test.syntax;

import junit.framework.Assert;

import org.junit.Test;

import simplenlg.framework.LexicalCategory;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;

/**
 * Tests for string elements as parts of larger phrases
 * 
 * @author bertugatt
 * 
 */
public class StringElementTests extends SimpleNLG4Test {

	public StringElementTests(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Test that string elements can be used as heads of NP
	 */
	@Test
	public void testStringElementAsHead() {
		NPPhraseSpec np = this.phraseFactory.createNounPhrase();
		np.setHead(phraseFactory.createStringElement("dogs and cats"));
		np.setSpecifier(phraseFactory.createWord("the",
				LexicalCategory.DETERMINER));
		Assert.assertEquals("the dogs and cats", this.realiser.realise(np)
				.getRealisation());
	}

	/**
	 * Sentences whose VP is a canned string
	 */
	@Test
	public void testStringElementAsVP() {
		SPhraseSpec s = this.phraseFactory.createClause();
		s.setVerbPhrase(this.phraseFactory.createStringElement("eats and drinks"));
		s.setSubject(this.phraseFactory.createStringElement("the big fat man"));
		Assert.assertEquals("the big fat man eats and drinks", this.realiser
				.realise(s).getRealisation());
	}

}
