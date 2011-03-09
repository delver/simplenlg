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
package simplenlg.orthography.english;

import java.util.ArrayList;
import java.util.List;

import simplenlg.features.DiscourseFunction;
import simplenlg.features.InternalFeature;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.DocumentCategory;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.ElementCategory;
import simplenlg.framework.ListElement;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGModule;
import simplenlg.framework.StringElement;

/**
 * <p>
 * This processing module deals with punctuation when applied to
 * <code>DocumentElement</code>s. The punctuation currently handled by this
 * processor includes the following (as of version 4.0):
 * <ul>
 * <li>Capitalisation of the first letter in sentences.</li>
 * <li>Termination of sentences with a period if not interrogative.</li>
 * <li>Termination of sentences with a question mark if they are interrogative.</li>
 * <li>Replacement of multiple conjunctions with a comma. For example,
 * <em>John and Peter and Simon</em> becomes <em>John, Peter and Simon</em>.</li>
 * </ul>
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
 * 
 * @author D. Westwater, University of Aberdeen.
 * @version 4.0
 * 
 */
public class OrthographyProcessor extends NLGModule {

	@Override
	public void initialise() {
		// No initialisation.
	}

	@Override
	public NLGElement realise(NLGElement element) {
		NLGElement realisedElement = null;

		if (element != null) {
			ElementCategory category = element.getCategory();
			if (category instanceof DocumentCategory
					&& element instanceof DocumentElement) {
				List<NLGElement> components = ((DocumentElement) element)
						.getComponents();
				switch ((DocumentCategory) category) {
				case SENTENCE:
					realisedElement = realiseSentence(components, element);
					break;

				case LIST_ITEM:
					if (components != null && components.size() > 0) {
						realisedElement = new ListElement(realise(components));
					}
					break;

				default:
					((DocumentElement) element)
							.setComponents(realise(components));
					realisedElement = element;
				}
			} else if (element instanceof ListElement) {
				StringBuffer buffer = new StringBuffer();
				realiseList(buffer, element.getChildren());
				realisedElement = new StringElement(buffer.toString());
			} else if (element instanceof CoordinatedPhraseElement) {
				realisedElement = realiseCoordinatedPhrase(element
						.getChildren());
			} else {
				realisedElement = element;
			}
		}
		return realisedElement;
	}

	/**
	 * Performs the realisation on a sentence. This includes adding the
	 * terminator and capitalising the first letter.
	 * 
	 * @param components
	 *            the <code>List</code> of <code>NLGElement</code>s representing
	 *            the components that make up the sentence.
	 * @param element
	 *            the <code>NLGElement</code> representing the sentence.
	 * @return the realised element as an <code>NLGElement</code>.
	 */
	private NLGElement realiseSentence(List<NLGElement> components,
			NLGElement element) {

		NLGElement realisedElement = null;
		if (components != null && components.size() > 0) {
			StringBuffer realisation = new StringBuffer();
			realiseList(realisation, components);

			capitaliseFirstLetter(realisation);
			terminateSentence(realisation, element.getFeatureAsBoolean(
					InternalFeature.INTERROGATIVE).booleanValue());

			((DocumentElement) element).clearComponents();
			realisation.append(' ');
			element.setRealisation(realisation.toString());
			realisedElement = element;
		}
		return realisedElement;
	}

	/**
	 * Adds the sentence terminator to the sentence. This is a period ('.') for
	 * normal sentences or a question mark ('?') for interrogatives.
	 * 
	 * @param realisation
	 *            the <code>StringBuffer<code> containing the current 
	 * realisation of the sentence.
	 * @param interrogative
	 *            a <code>boolean</code> flag showing <code>true</code> if the
	 *            sentence is an interrogative, <code>false</code> otherwise.
	 */
	private void terminateSentence(StringBuffer realisation,
			boolean interrogative) {
		char character = realisation.charAt(realisation.length() - 2);
		if (character != '.' && character != '?') {
			if (interrogative) {
				realisation.append('?');
			} else {
				realisation.append('.');
			}
		}
	}

	/**
	 * Capitalises the first character of a sentence if it is a lower case
	 * letter.
	 * 
	 * @param realisation
	 *            the <code>StringBuffer<code> containing the current 
	 * realisation of the sentence.
	 */
	private void capitaliseFirstLetter(StringBuffer realisation) {
		char character = realisation.charAt(0);
		if (character >= 'a' && character <= 'z') {
			character = (char) ('A' + (character - 'a'));
			realisation.setCharAt(0, character);
		}
	}

	@Override
	public List<NLGElement> realise(List<NLGElement> elements) {
		List<NLGElement> realisedList = new ArrayList<NLGElement>();

		if (elements != null && elements.size() > 0) {
			for (NLGElement eachElement : elements) {
				if (eachElement instanceof DocumentElement) {
					realisedList.add(realise(eachElement));
				} else {
					realisedList.add(eachElement);
				}
			}
		}
		return realisedList;
	}

	/**
	 * Realises a list of elements appending the result to the on-going
	 * realisation.
	 * 
	 * @param realisation
	 *            the <code>StringBuffer<code> containing the current 
	 * 			  realisation of the sentence.
	 * @param components
	 *            the <code>List</code> of <code>NLGElement</code>s representing
	 *            the components that make up the sentence.
	 */
	private void realiseList(StringBuffer realisation,
			List<NLGElement> components) {

		NLGElement realisedChild = null;

		for (NLGElement thisElement : components) {
			realisedChild = realise(thisElement);
			realisation.append(realisedChild.getRealisation()).append(' ');
		}
		if (realisation.length() > 0) {
			realisation.setLength(realisation.length() - 1);
		}
	}

	/**
	 * Realises coordinated phrases. Where there are more than two coordinates,
	 * then a comma replaces the conjunction word between all the coordinates
	 * save the last two. For example, <em>John and Peter and Simon</em> becomes
	 * <em>John, Peter and Simon</em>.
	 * 
	 * @param components
	 *            the <code>List</code> of <code>NLGElement</code>s representing
	 *            the components that make up the sentence.
	 * @return the realised element as an <code>NLGElement</code>.
	 */
	private NLGElement realiseCoordinatedPhrase(List<NLGElement> components) {
		StringBuffer realisation = new StringBuffer();
		NLGElement realisedChild = null;

		int length = components.size();

		for (int index = 0; index < length; index++) {
			realisedChild = components.get(index);
			if (index < length - 2
					&& DiscourseFunction.CONJUNCTION.equals(realisedChild
							.getFeature(InternalFeature.DISCOURSE_FUNCTION))) {

				realisation.append(", "); //$NON-NLS-1$
			} else {
				realisedChild = realise(realisedChild);
				realisation.append(realisedChild.getRealisation()).append(' ');
			}
		}
		realisation.setLength(realisation.length() - 1);
		return new StringElement(realisation.toString().replace(" ,", ",")); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
