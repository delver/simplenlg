/*
 * PhraseHelper - a helper class for the syntax processor that 
 * realises phrases.
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
package simplenlg.syntax.english;

import java.util.List;

import simplenlg.features.DiscourseFunction;
import simplenlg.features.Feature;
import simplenlg.features.InternalFeature;
import simplenlg.features.LexicalFeature;
import simplenlg.framework.InflectedWordElement;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.ListElement;
import simplenlg.framework.NLGElement;
import simplenlg.framework.PhraseCategory;
import simplenlg.framework.PhraseElement;

/**
 * <p>
 * This class contains static methods to help the syntax processor realise
 * phrases.
 * </p>
 * 
 * <hr>
 * 
 * <p>
 * Copyright (C) 2010, University of Aberdeen
 * </p>
 * 
 * <p>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
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
 * You should have received a copy of the GNU Lesser General Public License in
 * the zip file. If not, see <a
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
 * @author E. Reiter and D. Westwater, University of Aberdeen.
 * @version 4.0
 */
abstract class PhraseHelper {

	/**
	 * The main method for realising phrases.
	 * 
	 * @param parent
	 *            the <code>SyntaxProcessor</code> that called this method.
	 * @param phrase
	 *            the <code>PhraseElement</code> to be realised.
	 * @return the realised <code>NLGElement</code>.
	 */
	static NLGElement realise(SyntaxProcessor parent, PhraseElement phrase) {
		ListElement realisedElement = null;

		if (phrase != null) {
			realisedElement = new ListElement();

			realiseList(parent, realisedElement, phrase.getPreModifiers(),
					DiscourseFunction.PRE_MODIFIER);

			realiseHead(parent, phrase, realisedElement);
			realiseComplements(parent, phrase, realisedElement);

			PhraseHelper.realiseList(parent, realisedElement, phrase
					.getPostModifiers(), DiscourseFunction.POST_MODIFIER);
		}
		return realisedElement;
	}

	/**
	 * Realises the complements of the phrase adding <em>and</em> where
	 * appropriate.
	 * 
	 * @param parent
	 *            the parent <code>SyntaxProcessor</code> that will do the
	 *            realisation of the complementiser.
	 * @param phrase
	 *            the <code>PhraseElement</code> representing this noun phrase.
	 * @param realisedElement
	 *            the current realisation of the noun phrase.
	 */
	private static void realiseComplements(SyntaxProcessor parent,
			PhraseElement phrase, ListElement realisedElement) {

		boolean firstProcessed = false;
		NLGElement currentElement = null;

		for (NLGElement complement : phrase
				.getFeatureAsElementList(InternalFeature.COMPLEMENTS)) {
			currentElement = parent.realise(complement);
			if (currentElement != null) {
				currentElement.setFeature(InternalFeature.DISCOURSE_FUNCTION,
						DiscourseFunction.COMPLEMENT);
				if (firstProcessed) {
					realisedElement.addComponent(new InflectedWordElement(
							"and", LexicalCategory.CONJUNCTION)); //$NON-NLS-1$
				} else {
					firstProcessed = true;
				}
				realisedElement.addComponent(currentElement);
			}
		}
	}

	/**
	 * Realises the head element of the phrase.
	 * 
	 * @param parent
	 *            the parent <code>SyntaxProcessor</code> that will do the
	 *            realisation of the complementiser.
	 * @param phrase
	 *            the <code>PhraseElement</code> representing this noun phrase.
	 * @param realisedElement
	 *            the current realisation of the noun phrase.
	 */
	private static void realiseHead(SyntaxProcessor parent,
			PhraseElement phrase, ListElement realisedElement) {

		NLGElement head = phrase.getHead();
		if (head != null) {
			if (phrase.hasFeature(Feature.IS_COMPARATIVE)) {
				head.setFeature(Feature.IS_COMPARATIVE, phrase.getFeature(Feature.IS_COMPARATIVE));
			}
			else if (phrase.hasFeature(Feature.IS_SUPERLATIVE)) {
				head.setFeature(Feature.IS_SUPERLATIVE, phrase.getFeature(Feature.IS_SUPERLATIVE));
			}
			head = parent.realise(head);
			head.setFeature(InternalFeature.DISCOURSE_FUNCTION, DiscourseFunction.HEAD);
			realisedElement.addComponent(head);
		}
	}

	/**
	 * Iterates through a <code>List</code> of <code>NLGElement</code>s
	 * realisation each element and adding it to the on-going realisation of
	 * this clause.
	 * 
	 * @param parent
	 *            the parent <code>SyntaxProcessor</code> that will do the
	 *            realisation of the complementiser.
	 * @param realisedElement
	 *            the current realisation of the clause.
	 * @param elementList
	 *            the <code>List</code> of <code>NLGElement</code>s to be
	 *            realised.
	 * @param function
	 *            the <code>DiscourseFunction</code> each element in the list is
	 *            to take. If this is <code>null</code> then the function is not
	 *            set and any existing discourse function is kept.
	 */
	static void realiseList(SyntaxProcessor parent,
			ListElement realisedElement, List<NLGElement> elementList,
			DiscourseFunction function) {

		NLGElement currentElement = null;
		for (NLGElement eachElement : elementList) {
			currentElement = parent.realise(eachElement);
			if (currentElement != null) {
				currentElement.setFeature(InternalFeature.DISCOURSE_FUNCTION, function);
				realisedElement.addComponent(currentElement);
			}
		}
	}

	/**
	 * Determines if the given phrase has an expletive as a subject.
	 * 
	 * @param phrase
	 *            the <code>PhraseElement</code> to be examined.
	 * @return <code>true</code> if the phrase has an expletive subject.
	 */
	public static boolean isExpletiveSubject(PhraseElement phrase) {
		List<NLGElement> subjects = phrase
				.getFeatureAsElementList(InternalFeature.SUBJECTS);
		boolean expletive = false;

		if (subjects.size() == 1) {
			NLGElement subjectNP = subjects.get(0);

			if (subjectNP.isA(PhraseCategory.NOUN_PHRASE)) {
				expletive = subjectNP.getFeatureAsBoolean(
						LexicalFeature.EXPLETIVE_SUBJECT).booleanValue();
			} else if (subjectNP.isA(PhraseCategory.CANNED_TEXT)) {
				expletive = "there".equalsIgnoreCase(subjectNP.getRealisation()); //$NON-NLS-1$
			}
		}
		return expletive;
	}
}
