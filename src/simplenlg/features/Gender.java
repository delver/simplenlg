/*
 * Gender.java - The gender enumeration representing the gender of a subject or object
 * of phrase.
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
 * An enumeration representing the gender of the subject of a noun phrase, or
 * the object or subject of a verb phrase. It is most commonly used with
 * personal pronouns. The gender is recorded in the {@code Feature.GENDER}
 * feature and applies to nouns and pronouns.
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

public enum Gender {

	/**
	 * A word or phrase pertaining to a male topic. For example, <em>he</em>,
	 * <em>him</em>, <em>his</em>.
	 */
	MASCULINE,

	/**
	 * A word or phrase pertaining to a female topic. For example, <em>she</em>,
	 * <em>her</em>, <em>hers</em>.
	 */
	FEMININE,

	/**
	 * A word or phrase pertaining to a neutral or gender-less topic. For
	 * example, <em>it</em>, <em>its</em>.
	 */
	NEUTER;
}
