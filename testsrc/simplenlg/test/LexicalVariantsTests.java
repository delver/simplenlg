package simplenlg.test;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simplenlg.features.Feature;
import simplenlg.features.LexicalFeature;
import simplenlg.features.NumberAgreement;
import simplenlg.features.Tense;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.NIHDBLexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

/**
 * Tests on the use of spelling and inflectional variants, using the
 * NIHDBLexicon.
 * 
 * @author bertugatt
 * 
 */
public class LexicalVariantsTests extends TestCase {

	// lexicon object -- an instance of Lexicon
	NIHDBLexicon lexicon = null;

	// factory for phrases
	NLGFactory factory;

	// realiser
	Realiser realiser;

	// DB location -- change this to point to the lex access data dir
	static String DB_FILENAME = "A:\\corpora\\LEX\\lexAccess2011\\data\\HSqlDb\\lexAccess2011";

	@Override
	@Before
	/*
	 * * Sets up the accessor and runs it -- takes ca. 26 sec
	 */
	public void setUp() {
		this.lexicon = new NIHDBLexicon(DB_FILENAME);
		this.factory = new NLGFactory(lexicon);
		this.realiser = new Realiser(this.lexicon);
	}

	/**
	 * Close the lexicon
	 */
	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();

		if (lexicon != null)
			lexicon.close();
	}

	/**
	 * check that spelling variants are properly set
	 */
	@Test
	public void testSpellingVariants() {
		WordElement asd = lexicon.getWord("Adams-Stokes disease");
		List<String> spellVars = asd
				.getFeatureAsStringList(LexicalFeature.SPELL_VARS);
		Assert.assertTrue(spellVars.contains("Adams Stokes disease"));
		Assert.assertTrue(spellVars.contains("Adam-Stokes disease"));
		Assert.assertEquals(2, spellVars.size());
		Assert.assertEquals(asd.getBaseForm(), asd
				.getFeatureAsString(LexicalFeature.DEFAULT_SPELL));

		// default spell variant is baseform
		Assert.assertEquals("Adams-Stokes disease", asd
				.getDefaultSpellingVariant());

		// default spell variant changes
		asd.setDefaultSpellingVariant("Adams Stokes disease");
		Assert.assertEquals("Adams Stokes disease", asd
				.getDefaultSpellingVariant());
	}

	/**
	 * Check that spelling variants of nouns are kept in the realisation, once
	 * set.
	 */
	public void testNounSpellingVariant() {
		WordElement word = lexicon.getWord("formalization");
		List<String> spellVars = word.getSpellingVariants();
		Assert.assertTrue(spellVars.contains("formalisation"));
		Assert.assertEquals("reg", word.getDefaultInflectionalVariant());

		NPPhraseSpec np = factory
				.createNounPhrase(lexicon.getWord("the"), word);
		np.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
		Assert.assertEquals("the formalizations", this.realiser.realise(np)
				.getRealisation());

		// now set the default spelling variant to be different
		word.setDefaultSpellingVariant("formalisation");
		NPPhraseSpec np2 = factory.createNounPhrase(lexicon.getWord("the"),
				word);
		np2.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
		Assert.assertEquals("the formalisations", this.realiser.realise(np2)
				.getRealisation());
	}

	public void testNounSpellingVariant2() {
		WordElement word = lexicon.getWord("heart-failure",
				LexicalCategory.NOUN);
		List<String> spellVars = word.getSpellingVariants();
		Assert.assertTrue(spellVars.contains("heart failure"));

		// force the infl to be reg (it's actually uncount -- NIH doesn't list
		// reg as an option for this one)
		word.setDefaultInflectionalVariant("reg");

		NPPhraseSpec np1 = this.factory.createNounPhrase("two", word);
		np1.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
		SPhraseSpec s1 = this.factory.createClause("he", "have", np1);
		s1.setFeature(Feature.TENSE, Tense.PAST);
		Assert.assertEquals("he had two heart-failures", this.realiser.realise(
				s1).getRealisation());

		// change the default spelling variant
		word.setDefaultSpellingVariant("heart failure");
		Assert.assertEquals("he had two heart failures", this.realiser.realise(s1)
				.getRealisation());

		// change to plural -- should pluralise the alternative spelling variant		
		s1.setFeature(Feature.TENSE, Tense.FUTURE);
		Assert.assertEquals("he will have two heart failures", this.realiser
				.realise(s1).getRealisation());
	}

	public void testInflectionalVariant() {
		WordElement word = lexicon.getWord("heart-failure",
				LexicalCategory.NOUN);
		
		//default infl for this word is uncount, so no pluralisation
		NPPhraseSpec np1 = this.factory.createNounPhrase("two", word);
		np1.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
		Assert.assertEquals("two heart-failure", this.realiser.realise(np1).getRealisation());
		
		// force the infl to be reg (it's actually uncount -- NIH doesn't list
		// reg as an option for this one)
		word.setDefaultInflectionalVariant("reg");
		Assert.assertEquals("two heart-failures", this.realiser.realise(np1).getRealisation());
		
		//
	}
}
