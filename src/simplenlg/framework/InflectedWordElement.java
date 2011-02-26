/*
 * InflectedWordElement.java - a representation of a word that needs to be inflected
 * by the morphology.
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
package simplenlg.framework;

import java.util.List;

import simplenlg.features.InternalFeature;
import simplenlg.features.LexicalFeature;

/**
 * <p>
 * This class defines the <code>NLGElement</code> that is used to represent an
 * word that requires inflection by the morphology. It has convenience methods
 * for retrieving the base form of the word (for example, <em>kiss</em>,
 * <em>eat</em>) and for setting and retrieving the base word. The base word is
 * a <code>WordElement</code> constructed by the lexicon.
 * </p>
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
public class InflectedWordElement extends NLGElement {

	/**
	 * Constructs a new inflected word using the giving word as the base form.
	 * Constructing the word also requires a lexical category (such as noun,
	 * verb).
	 * 
	 * @param word
	 *            the base form for this inflected word.
	 * @param category
	 *            the lexical category for the word.
	 */
	public InflectedWordElement(String word, LexicalCategory category) {
		super();
		setFeature(LexicalFeature.BASE_FORM, word);
		setCategory(category);
	}
	
	/**
	 * Constructs a new inflected word from a WordElement
	 * 
	 * @param word
	 *            underlying wordelement
	 */
	public InflectedWordElement(WordElement word) {
		super();
		setFeature(InternalFeature.BASE_WORD, word);
		setFeature(LexicalFeature.BASE_FORM, word.getBaseForm());
		setCategory(word.getCategory());
	}

	/**
	 * This method returns null as the inflected word has no child components.
	 */
	@Override
	public List<NLGElement> getChildren() {
		return null;
	}

	@Override
	public String toString() {
		return "InflectedWordElement[" + getBaseForm() + ':' //$NON-NLS-1$
				+ getCategory().toString() + ']';
	}

	@Override
	public String printTree(String indent) {
		StringBuffer print = new StringBuffer();
		print.append("InflectedWordElement: base=").append(getBaseForm()) //$NON-NLS-1$
				.append(", category=").append(getCategory().toString()).append( //$NON-NLS-1$
						", ").append(super.toString()).append('\n'); //$NON-NLS-1$
		return print.toString();
	}

	/**
	 * Retrieves the base form for this element. The base form is the originally
	 * supplied word.
	 * 
	 * @return a <code>String</code> forming the base form of the element.
	 */
	public String getBaseForm() {
		return getFeatureAsString(LexicalFeature.BASE_FORM);
	}

	/**
	 * Sets the base word for this element.
	 * 
	 * @param word
	 *            the <code>WordElement</code> representing the base word as
	 *            read from the lexicon.
	 */
	public void setBaseWord(WordElement word) {
		setFeature(InternalFeature.BASE_WORD, word);
	}

	/**
	 * Retrieves the base word for this element.
	 * 
	 * @return the <code>WordElement</code> representing the base word as read
	 *         from the lexicon.
	 */
	public WordElement getBaseWord() {
		NLGElement baseWord = this.getFeatureAsElement(InternalFeature.BASE_WORD);
		return baseWord instanceof WordElement ? (WordElement) baseWord : null;
	}
}
