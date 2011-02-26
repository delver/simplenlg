/*
 * Person.java - An enumeration representing the point of view of the narrative.
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
 * This is an enumeration used to represent the point of view of the narrative.
 * It covers first person, second person and third person. The person is
 * recorded in the {@code Feature.PERSON} feature and applies to clauses,
 * coordinated phrases, noun phrases and verb phrases.
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
 * @author A. Gatt, D. Westwater, University of Aberdeen.
 * @version 4.0
 * 
 */

public enum Person {

	/**
	 * The enumeration to show that the narration is written in the first
	 * person. First person narrative uses the personal pronouns of <em>I</em>
	 * and <em>we</em>.
	 */
	FIRST, 

	/**
	 * The enumeration to show that the narration is written in the second
	 * person. Second person narrative uses the personal pronoun of <em>you</em>.
	 */
	SECOND, 

	/**
	 * The enumeration to show that the narration is written in the third
	 * person. Third person narrative uses the personal pronouns of <em>he</em>, 
	 * <em>her</em> and <em>they</em>.
	 */
	THIRD;
}
