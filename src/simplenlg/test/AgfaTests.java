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
