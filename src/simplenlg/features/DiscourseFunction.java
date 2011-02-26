/*
 * DiscourseFunction.java - An enumeration representing the role played in the text by
 * each element.
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
 * An enumeration representing the grammatical function that an element might
 * take. The discourse function is recorded under the {@code
 * Feature.DISCOURSE_FUNCTION} feature and applies to any type of {@code
 * NLGElement}.
 * </p>
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
public enum DiscourseFunction {

	/**
	 * Auxiliaries are the additional verbs added to a verb phrase to alter the
	 * meaning being described. For example, <em>will</em> can be added as an
	 * auxiliary to a verb phrase to represent the future tense of the verb,
	 * <em>John <b>will</b> kiss Mary</em>.
	 */
	AUXILIARY,

	/**
	 * Complements are additional components that are required to complement the
	 * meaning of a sentence. For example,
	 * <em>put the bread <b>on the table</b></em> requires the complement
	 * <em>on the table</em> to make the clause meaningful.
	 */
	COMPLEMENT,

	/**
	 * A conjunction is a word that links items together in a coordinated
	 * phrase. The most common conjunctions are <em>and</em> and <em>but</em>.
	 */
	CONJUNCTION,

	/**
	 * Cue phrases are added to sentence to indicate document structure or flow.
	 * They normally do not add any semantic information to the phrase. For
	 * example,
	 * <em><b>Firstly</b>, let me just say it is an honour to be here.</em>
	 * <em><b>Incidentally</b>, John kissed Mary last night.</em>
	 */
	CUE_PHRASE,

	/**
	 * Front modifiers are modifiers that apply to clauses. They are placed in
	 * the syntactical structure after the cue phrase but before the subject.
	 * For example, <em>However, <b>last night</b> John kissed Mary.</em>
	 */
	FRONT_MODIFIER,

	/**
	 * This represents the main item of the phrase. For verb phrases, the head
	 * will be the main verb. For noun phrases, the head will be the subject
	 * noun. For adjective, adverb and prepositional phrases, the head will be
	 * the adjective, adverb and preposition respectively.
	 */
	HEAD,

	/**
	 * This is the indirect object of a verb phrase or an additional object that
	 * is affected by the action performed. This is typically the recipient of
	 * <em>give</em>. For example, Mary is the indirect object in the phrase
	 * <em>John gives <b>Mary</b> the flower</em>.
	 */
	INDIRECT_OBJECT,

	/**
	 * This is the object of a verb phrase and represents the item that the
	 * action is performed upon. For example, the flower is the object in the
	 * phrase <em>John gives Mary <b>the flower</b></em>.
	 */
	OBJECT,

	/**
	 * Pre-modifiers, typically adjectives and adverbs, appear before the head
	 * of a phrase. They can apply to noun phrases and verb phrases. For
	 * example, <em>the <b>beautiful</b> woman</em>,
	 * <em>the <b>ferocious</b> dog</em>.
	 */
	PRE_MODIFIER,

	/**
	 * Post-modifiers, typically adjectives and adverbs, are added after the
	 * head of the phrase. For example, <em>John walked <b>quickly</b></em>.
	 */
	POST_MODIFIER,

	/**
	 * The specifier, otherwise known as the determiner, is a word that can be
	 * placed before a noun in a noun phrase. Example specifiers include:
	 * <em>the</em>, <em>some</em>, <em>a</em> and <em>an</em> as well as the
	 * personal pronouns such as <em>my</em>, <em>your</em>, <em>their</em>.
	 */
	SPECIFIER,

	/**
	 * This is the subject of a verb phrase and represents the entity performing
	 * the action. For example, John is the subject in the phrase
	 * <em><b>John</b> gives Mary the flower.</em>
	 */
	SUBJECT,

	/**
	 * The verb phrase highlights the part of a clause that forms the verb
	 * phrase. Verb phrases can be formed of a single verb or from a verb with a
	 * particle, such as <em>kiss</em>, <em>talk</em>, <em>bark</em>,
	 * <em>fall down</em>, <em>pick up</em>.
	 */
	VERB_PHRASE;
}
