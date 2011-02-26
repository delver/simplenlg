package simplenlg.aggregation;

import java.util.List;

import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.NLGModule;
import simplenlg.framework.PhraseCategory;
import simplenlg.framework.PhraseElement;
import simplenlg.syntax.english.SyntaxProcessor;

public class NewAggregator extends NLGModule {
	private SyntaxProcessor _syntax;
	private NLGFactory _factory;

	public NewAggregator() {

	}

	public void initialise() {
		this._syntax = new SyntaxProcessor();
		this._factory = new NLGFactory();
	}

	@Override
	public List<NLGElement> realise(List<NLGElement> elements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NLGElement realise(NLGElement element) {
		// TODO Auto-generated method stub
		return null;
	}

	public NLGElement realise(NLGElement phrase1, NLGElement phrase2) {
		NLGElement result = null;

		if (phrase1 instanceof PhraseElement
				&& phrase2 instanceof PhraseElement
				&& phrase1.getCategory() == PhraseCategory.CLAUSE
				&& phrase2.getCategory() == PhraseCategory.CLAUSE) {

			List<FunctionalSet> funcSets = AggregationHelper
					.collectFunctionalPairs(this._syntax.realise(phrase1),this._syntax.realise(phrase2));

			applyForwardConjunctionReduction(funcSets);
			applyBackwardConjunctionReduction(funcSets);
			result = this._factory.createdCoordinatedPhrase(phrase1, phrase2);
		}

		return result;
	}

	// private void applyGapping(List<FunctionalSet> funcPairs) {
	//
	// }

	private void applyForwardConjunctionReduction(List<FunctionalSet> funcSets) {

		for (FunctionalSet pair : funcSets) {
			if (pair.getPeriphery() == Periphery.LEFT && pair.formIdentical()) {
				pair.elideRightMost();
			}
		}

	}

	private void applyBackwardConjunctionReduction(List<FunctionalSet> funcSets) {
		for (FunctionalSet pair : funcSets) {
			if (pair.getPeriphery() == Periphery.RIGHT && pair.formIdentical()) {
				pair.elideLeftMost();
			}
		}
	}

}
