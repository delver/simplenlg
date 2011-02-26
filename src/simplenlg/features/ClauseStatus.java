/*
 * ClauseStatus.java - An enumeration representing the status of a clause, i.e. matrix 
 * (main) or subordinate.
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
 *  You should have received a copy of the GNU Lesser General Public License and the 
 *  GNU Lesser General Public License along with this program.  If not, see 
 *  <http://www.gnu.org/licenses/>.
 */

package simplenlg.features;

/**
 * <p>
 * This is an enumeration of the two different types of clauses used in the
 * SimplNLG package. Clauses can be either matrix or subordinate. Matrix clauses
 * are not contained within any other clause and frequently span an entire
 * sentence, whereas a subordinate clauses is contained within another clause.
 * </p>
 * 
 * <p>
 * As an example, take the phrase, <em><b>whoever said it</b> is wrong</em>.
 * This phrase has two clauses, one being the main clause and the other being a
 * subordinate clause. The section in <b>bold</b> type highlights the
 * subordinate clause. It is entirely contained within another clause. The
 * matrix clause is of the form <em>he is wrong</em> or to be more general
 * <em>X is wrong</em>. <em>X</em> can be replaced with a single subject or, as
 * is the case here, by a subordinate clause.
 * </p>
 * 
 * <p>
 * The clause status is recorded under the {@code Feature.CLAUSE_STATUS} feature
 * and applies only to clauses.
 * <hr>
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
 * @author A. Gatt and D. Westwater, University of Aberdeen.
 * @version 4.0
 * 
 */

public enum ClauseStatus {

	/**
	 * This enumeration represents a matrix clause. A matrix clause is not
	 * subordinate to any other clause and therefore sits at the top-level of
	 * the clause hierarchy, typically spanning the whole sentence.
	 */
	MATRIX,

	/**
	 * The subordinate clauses are contained within a higher clause.
	 */
	SUBORDINATE;
}
