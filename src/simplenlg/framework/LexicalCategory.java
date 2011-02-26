/*
 * LexicalCategory - an enumeration of the different types of lexical components.
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

/**
 * <p>
 * This enumeration defines the different lexical components. The categories
 * define the well understood role each word takes in language. For example,
 * <em>dog</em> is a noun, <em>chase</em> is a verb, <em>the</em> is a
 * determiner, and so on.
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
 * @author A. Gatt and D. Westwater, University of Aberdeen.
 * @version 4.0
 * 
 */
public enum LexicalCategory implements ElementCategory {

	/** A default value, indicating an unspecified category. */
	ANY,

	/** The element represents a symbol. */
	SYMBOL,

	/** A noun element. */
	NOUN,

	/** An adjective element. */
	ADJECTIVE,

	/** An adverb element. */
	ADVERB,

	/** A verb element. */
	VERB,

	/** A determiner element often referred to as a specifier. */
	DETERMINER,

	/** A pronoun element. */
	PRONOUN,

	/** A conjunction element. */
	CONJUNCTION,

	/** A preposition element. */
	PREPOSITION,

	/** A complementiser element. */
	COMPLEMENTISER,

	/** A modal element. */
	MODAL,

	/** An auxiliary verb element. */
	AUXILIARY;

	/**
	 * <p>
	 * Checks to see if the given object is equal to this lexical category.
	 * This is done by checking the enumeration if the object is of the type
	 * <code>LexicalCategory</code> or by converting the object and this
	 * category to strings and comparing the strings.
	 * </p>
	 * <p>
	 * For example, <code>LexicalCategory.NOUN</code> will match another
	 * <code>LexicalCategory.NOUN</code> but will also match the string
	 * <em>"noun"</em> as well.
	 */
	public boolean equalTo(Object checkObject) {
		boolean match = false;

		if (checkObject != null) {
			if (checkObject instanceof DocumentCategory) {
				match = this.equals(checkObject);
			} else {
				match = this.toString()
						.equalsIgnoreCase(checkObject.toString());
			}
		}
		return match;
	}
}
