package simplenlg.test.syntax;

import junit.framework.Assert;
import simplenlg.features.Feature;
import simplenlg.phrasespec.SPhraseSpec;

/**
 * Tests for elision of phrases and words
 */
public class ElisionTests extends SimpleNLG4Test {

	public ElisionTests(String name) {
		super(name);
	}
	
	/**
	 * Test elision of phrases in various places in the sentence
	 */
//	public void testPhraseElision() {
//		SPhraseSpec s1 = this.phraseFactory.createClause();
//		s1.setSubject(this.np4); //the rock
//		this.kiss.setComplement(this.np5);//kiss the curtain
//		s1.setVerbPhrase(this.kiss);
//		
//		Assert.assertEquals("the rock kisses the curtain", this.realiser.realise(s1).getRealisation());
//		
//		//elide subject np
//		this.np4.setFeature(Feature.ELIDED, true);
//		Assert.assertEquals("kisses the curtain", this.realiser.realise(s1).getRealisation());
//		
//		//elide vp
//		this.np4.setFeature(Feature.ELIDED, false);
//		this.kiss.setFeature(Feature.ELIDED, true);
//		Assert.assertEquals("the rock", this.realiser.realise(s1).getRealisation());
//		
//		//elide complement only
//		this.kiss.setFeature(Feature.ELIDED, false);
//		this.np5.setFeature(Feature.ELIDED, true);
//		Assert.assertEquals("the rock kisses", this.realiser.realise(s1).getRealisation());
//	}
	
	/**
	 * Test for elision of specific words rather than phrases
	 */
	public void testWordElision() {
		this.realiser.setDebugMode(true);
		SPhraseSpec s1 = this.phraseFactory.createClause();
		s1.setSubject(this.np4); //the rock
		this.kiss.setComplement(this.np5);//kiss the curtain
		s1.setVerbPhrase(this.kiss);
		
		this.kiss.getHead().setFeature(Feature.ELIDED, true);
		Assert.assertEquals("the rock kisses", this.realiser.realise(s1).getRealisation());
	}

}
