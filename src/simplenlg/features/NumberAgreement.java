/*
 * NumberAgreement.java - An enumeration representing the number agreement of an 
 * element. That is whether the element is singular, plural or either.
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
 * An enumeration representing the different types of number agreement. The
 * number agreement is recorded in the {@code Feature.NUMBER} feature and
 * applies to nouns and verbs, and their associated phrases.
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
 * @author A. Gatt and D. Westwater, University of Aberdeen.
 * @version 4.0
 * 
 */
public enum NumberAgreement {
	
	/**
	 * This represents words that have the same form regardless of whether they 
	 * are singular or plural. For example, <em>sheep</em>, <em>fish</em>.
	 */
	BOTH,

	/**
	 * This represents verbs and nouns that are written in the plural. For
	 * example, <em>dogs</em> as opposed to <em>dog</em>, and
	 * <em>John and Simon <b>kiss</b> Mary</em>.
	 */
	PLURAL,

	/**
	 * This represents verbs and nouns that are written in the singular. For
	 * example, <em>dog</em> as opposed to <em>dogs</em>, and
	 * <em>John <b>kisses</b> Mary</em>.
	 */
	SINGULAR;
}
