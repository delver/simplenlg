/*
 * Pattern.java - An enumeration detailing the different morphology patterns that can 
 * be applied to words.
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

package simplenlg.features;

/**
 * <p>
 * An enumeration representing the different types of morphology patterns used
 * by the basic morphology processor included with SimpleNLG. This enumeration
 * is a way of informing the morphology processor which set of rules should be
 * used when inflecting the word.
 * </p>
 * <p>
 * The pattern is recorded in the {@code Feature.PATTERN} feature and applies to
 * adjectives, nouns and verbs.
 * </p>
 * <p>
 * It should be noted that the morphology processor will use user-defined
 * inflections or those found in a lexicon first before applying the supplied
 * rules.
 * </p>
 * 
 * <hr>
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

public enum Pattern {

	/**
	 * The morphology processor has simple rules for pluralising Greek and Latin
	 * nouns. The full list can be found in the explanation of the morphology
	 * processor. An example would be turning <em>focus</em> into <em>foci</em>.
	 * The Greco-Latin rules are generally more complex than the basic rules.
	 */
	GRECO_LATIN_REGULAR,

	/**
	 * A word having an irregular pattern essentially means that none of the
	 * supplied rules can be used to correctly inflect the word. The inflection
	 * should be defined by the user or appear in the lexicon. <em>sheep</em> is
	 * an example of an irregular noun.
	 */
	IRREGULAR,

	/**
	 * Regular patterns represent the default rules when dealing with
	 * inflections. A full list can be found in the explanation of the
	 * morphology processor. An example would be adding <em>-s</em> to the end
	 * of regular nouns to pluralise them.
	 */
	REGULAR, 
	
	/**
	 * Regular double patterns apply to verbs where the last consonant is duplicated
	 * before applying the new suffix. For example, the verb <em>tag</em> has a 
	 * regular double pattern as its inflected forms include <em>tagged</em> and 
	 * <em>tagging</em>.
	 */
	REGULAR_DOUBLE;
}
