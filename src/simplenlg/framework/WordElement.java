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
package simplenlg.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import simplenlg.features.LexicalFeature;

/**
 * This is the class for a lexical entry (ie, a word). Words are stored in a
 * {@link simplenlg.lexicon.Lexicon}, and usually the developer retrieves a
 * WordElement via a lookup method in the lexicon
 * 
 * Words always have a base form, and usually have a
 * {@link simplenlg.framework.LexicalCategory}. They may also have a Lexicon ID.
 * 
 * Words also have features (which are retrieved from the Lexicon), these are
 * held in the standard NLGElement feature map
 * 
 * @author E. Reiter, University of Aberdeen.
 * @version 4.0
 */

public class WordElement extends NLGElement {

	// Words have baseForm, category, id, and features
	// features are inherited from NLGElement

	String baseForm; // base form, eg "dog". currently also in NLG Element, but
	// will be removed from there

	String id; // id in lexicon (may be null);

	// LexicalCategory category; // type of word

	/**********************************************************/
	// constructors
	/**********************************************************/

	/**
	 * empty constructor
	 * 
	 */
	public WordElement() {
		super();
		this.baseForm = null;
		setCategory(LexicalCategory.ANY);
		this.id = null;
	}

	/**
	 * create a WordElement with the specified baseForm (no category or ID
	 * specified)
	 * 
	 * @param baseForm
	 *            - base form of WordElement
	 */
	public WordElement(String baseForm) {
		super();
		this.baseForm = baseForm;
		setCategory(LexicalCategory.ANY);
		this.id = null;
	}

	/**
	 * create a WordElement with the specified baseForm and category
	 * 
	 * @param baseForm
	 *            - base form of WordElement
	 * @param category
	 *            - category of WordElement
	 */
	public WordElement(String baseForm, LexicalCategory category) {
		super();
		this.baseForm = baseForm;
		setCategory(category);
		this.id = null;
	}

	/**
	 * create a WordElement with the specified baseForm, category, ID
	 * 
	 * @param baseForm
	 *            - base form of WordElement
	 * @param category
	 *            - category of WordElement
	 * @param id
	 *            - ID of word in lexicon
	 */
	public WordElement(String baseForm, LexicalCategory category, String id) {
		super();
		this.baseForm = baseForm;
		setCategory(category);
		this.id = id;
	}

	/**********************************************************/
	// getters and setters
	/**********************************************************/

	/**
	 * @return the baseForm
	 */
	public String getBaseForm() {
		return this.baseForm;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param baseForm
	 *            the baseForm to set
	 */
	public void setBaseForm(String baseForm) {
		this.baseForm = baseForm;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Convenience method to set the default inflectional variant of a word.
	 * Equivalent to
	 * <code>setFeature(LexicalFeature.DEFAULT_INFL, variant)</code>
	 * 
	 * @param variant
	 *            The variant, which is an inflectional code such as <I>reg</I>
	 *            or <I>irreg</I>
	 */
	public void setDefaultInflectionalVariant(String variant) {
		setFeature(LexicalFeature.DEFAULT_INFL, variant);
	}

	/**
	 * Convenience method, equivalent to
	 * <code>getFeatureAsString(LexicalFeature.DEFAULT_INFL)</code>.
	 * 
	 * @return the default inflectional variant
	 */
	public String getDefaultInflectionalVariant() {
		return getFeatureAsString(LexicalFeature.DEFAULT_INFL);
	}

	/**
	 * Convenience method to get all the inflectional forms of the word.
	 * Equivalent to
	 * <code>getFeatureAsStringList(LexicalFeature.INFLECTIONS)</code>.
	 * 
	 * @return the list of inflectional variants (for example, <I>reg</I>,
	 *         <I>irreg</I> etc)
	 */
	public List<String> getInflectionalVariants() {
		return getFeatureAsStringList(LexicalFeature.INFLECTIONS);
	}

	/**
	 * Convenience method to get all the spelling variants of the word.
	 * Equivalent to
	 * <code>getFeatureAsStringList(LexicalFeature.SPELL_VARS)</code>.
	 * 
	 * @return the list of spelling variants
	 */
	public List<String> getSpellingVariants() {
		return getFeatureAsStringList(LexicalFeature.SPELL_VARS);
	}

	/**
	 * Convenience method to set the default spelling variant of a word.
	 * Equivalent to
	 * <code>setFeature(LexicalFeature.DEFAULT_SPELL, variant)</code>.
	 * 
	 * <P>
	 * By default, the spelling variant used is the base form. If otherwise set,
	 * this forces the realiser to always use the spelling variant specified.
	 * 
	 * @param variant
	 *            The spelling variant
	 */
	public void setDefaultSpellingVariant(String variant) {
		setFeature(LexicalFeature.DEFAULT_SPELL, variant);
	}

	/**
	 * Convenience method, equivalent to
	 * <code>getFeatureAsString(LexicalFeature.DEFAULT_SPELL)</code>. If this
	 * feature is not set, the baseform is returned.
	 * 
	 * @return the default inflectional variant
	 */
	public String getDefaultSpellingVariant() {
		String defSpell = getFeatureAsString(LexicalFeature.DEFAULT_SPELL);
		return defSpell == null ? this.getBaseForm() : defSpell;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	// public void setCategory(LexicalCategory category) {
	// this.category = category;
	// }

	/**********************************************************/
	// other methods
	/**********************************************************/

	@Override
	public String toString() {
		ElementCategory _category = getCategory();
		StringBuffer buffer = new StringBuffer("WordElement["); //$NON-NLS-1$
		buffer.append(getBaseForm()).append(':');
		if (_category != null) {
			buffer.append(_category.toString());
		} else {
			buffer.append("no category"); //$NON-NLS-1$
		}
		buffer.append(']');
		return buffer.toString();
	}

	public String toXML() {
		String xml = String.format("<word>%n"); //$NON-NLS-1$
		if (getBaseForm() != null)
			xml = xml + String.format("  <base>%s</base>%n", getBaseForm()); //$NON-NLS-1$
		if (getCategory() != LexicalCategory.ANY)
			xml = xml + String.format("  <category>%s</category>%n", //$NON-NLS-1$
					getCategory().toString().toLowerCase());
		if (getId() != null)
			xml = xml + String.format("  <id>%s</id>%n", getId()); //$NON-NLS-1$

		SortedSet<String> featureNames = new TreeSet<String>(
				getAllFeatureNames()); // list features in alpha order
		for (String feature : featureNames) {
			Object value = getFeature(feature);
			if (value != null) { // ignore null features
				if (value instanceof Boolean) { // booleans ignored if false,
					// shown as <XX/> if true
					boolean bvalue = ((Boolean) value).booleanValue();
					if (bvalue)
						xml = xml + String.format("  <%s/>%n", feature); //$NON-NLS-1$
				} else { // otherwise include feature and value
					xml = xml + String.format("  <%s>%s</%s>%n", feature, value //$NON-NLS-1$
							.toString(), feature);
				}
			}

		}
		xml = xml + String.format("</word>%n"); //$NON-NLS-1$
		return xml;
	}

	/**
	 * This method returns an empty <code>List</code> as words do not have child
	 * elements.
	 */
	@Override
	public List<NLGElement> getChildren() {
		return new ArrayList<NLGElement>();
	}

	@Override
	public String printTree(String indent) {
		StringBuffer print = new StringBuffer();
		print.append("WordElement: base=").append(getBaseForm()) //$NON-NLS-1$
				.append(", category=").append(getCategory().toString()) //$NON-NLS-1$
				.append(", ").append(super.toString()).append('\n'); //$NON-NLS-1$
		return print.toString();
	}

	/**
	 * Check if this WordElement is equal to an object.
	 * 
	 * @param the
	 *            object
	 * @return <code>true</code> iff the object is a word element with the same
	 *         id and the same baseform and the same features.
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof WordElement) {
			WordElement we = (WordElement) o;

			return (this.baseForm == we.baseForm || this.baseForm
					.equals(we.baseForm))
					&& (this.id == we.id || this.id.equals(we.id))
					&& we.features.equals(this.features);
		}

		return false;
	}
}
