/*
 * DocumentCategory.java - An enumeration of the different document categories.
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
 * This enumerated type defines the different <i>types</i> of components found
 * in the structure of text. This deals exclusively with the structural format
 * of the text and not of the syntax of the language. Therefore, this
 * enumeration deals with documents, sections, paragraphs, sentences and lists.
 * </p>
 * <p>
 * The enumeration implements the <code>ElementCategory</code> interface, thus
 * making it compatible the SimpleNLG framework.
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
 */
public enum DocumentCategory implements ElementCategory {

	/** Definition for a document. */
	DOCUMENT,

	/** Definition for a section within a document. */
	SECTION,

	/** Definition for a paragraph. */
	PARAGRAPH,

	/** Definition for a sentence. */
	SENTENCE,

	/** Definition for creating a list of items. */
	LIST,

	/** Definition for an item in a list. */
	LIST_ITEM;

	/**
	 * <p>
	 * Checks to see if the given object is equal to this document category.
	 * This is done by checking the enumeration if the object is of the type
	 * <code>DocumentCategory</code> or by converting the object and the this
	 * category to strings and comparing the strings.
	 * </p>
	 * <p>
	 * For example, <code>DocumentCategory.LIST</code> will match another
	 * <code>DocumentCategory.LIST</code> but will also match the string
	 * <em>"list"</em> as well.
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

	/**
	 * <p>
	 * This method determines if the supplied elementCategory forms an immediate
	 * sub-part of <code>this</code> category. The allowed sub-parts for each
	 * <code>this</code> type are outlined below:
	 * </p>
	 * 
	 * <ul>
	 * <li><b>DOCUMENT</b>: can contain SECTIONs, PARAGRAPHs, SENTENCEs and
	 * LISTs. It cannot contain other DOCUMENTs or LIST_ITEMs.</li>
	 * <li><b>SECTION</b>: can contain SECTIONs (referred to as subsections),
	 * PARAGRAPHs, SENTENCEs and LISTs. It cannot contain DOCUMENTs or
	 * LIST_ITEMs.</li>
	 * <li><b>PARAGRAPH</b>: can contain SENTENCEs or LISTs. It cannot contain
	 * DOCUMENTs, SECTIONs, other PARAGRAPHs or LIST_ITEMs.</li>
	 * <li><b>SENTENCE</b>: can only contain other forms of
	 * <code>NLGElement</code>s. It cannot contain DOCUMENTs, SECTIONs,
	 * PARAGRAPHs, other SENTENCEs, LISTs or LIST_ITEMs.</li>
	 * <li><b>LIST</b>: can only contain LIST_ITEMs. It cannot contain
	 * DOCUMENTs, SECTIONs, PARAGRAPHs, SENTENCEs or other LISTs.</li>
	 * <li><b>LIST_ITEMs</b>: can contain PARAGRAPHs, SENTENCEs, LISTs or other
	 * forms of <code>NLGElement</code>s. It cannot contain DOCUMENTs, SECTIONs,
	 * or LIST_ITEMs.</li>
	 * </ul>
	 * 
	 * <p>
	 * For structuring text, this effectively becomes the test for relevant
	 * child types affecting the immediate children. For instance, it is
	 * possible for a DOCUMENT to contains LIST_ITEMs but only if the LIST_ITEMs
	 * are children of LISTs.
	 * </p>
	 * 
	 * <p>
	 * A more precise definition of SENTENCE would be that it only contains
	 * PHRASEs. However, this DocumentCategory does not consider these options
	 * as this crosses the boundary between orthographic structure and syntactic
	 * structure.
	 * </p>
	 * 
	 * <p>
	 * In pseudo-BNF this can be written as:
	 * </p>
	 * 
	 * <pre>
	 *    DOCUMENT 		 ::= DOCUMENT_PART*
	 *    DOCUMENT_PART  ::= SECTION | PARAGRAPH
	 *    SECTION 		 ::= DOCUMENT_PART*
	 *    PARAGRAPH 	 ::= PARAPGRAPH_PART*
	 *    PARAGRAPH_PART ::= SENTENCE | LIST
	 *    SENTENCE 	 	 ::= &lt;NLGElement&gt;*
	 *    LIST 			 ::= LIST_ITEM*
	 *    LIST_ITEM 	 ::= PARAGRAPH | PARAGRAPH_PART | &lt;NLGElement&gt;
	 * </pre>
	 * <p>
	 * Ideally the '*' should be replaced with '+' to represent that one or more
	 * of the components must exist rather than 0 or more. However, the
	 * implementation permits creation of the <code>DocumentElement</code>s with
	 * no children or sub-parts added.
	 * </p>
	 * 
	 * @param elementCategory
	 *            the category we are checking against. If this is
	 *            <code>NULL</code> the method will return <code>false</code>.
	 * @return <code>true</code> if the supplied elementCategory is a sub-part
	 *         of <code>this</code> type of category, <code>false</code>
	 *         otherwise.
	 */
	public boolean hasSubPart(ElementCategory elementCategory) {
		boolean subPart = false;
		if (elementCategory != null) {
			if (elementCategory instanceof DocumentCategory) {
				switch (this) {
				case DOCUMENT:
					subPart = !(elementCategory.equals(DOCUMENT) || elementCategory
							.equals(LIST_ITEM));
					break;
					
				case SECTION:
					subPart = elementCategory.equals(PARAGRAPH)
							|| elementCategory.equals(SECTION);
					break;

				case PARAGRAPH:
					subPart = elementCategory.equals(SENTENCE)
							|| elementCategory.equals(LIST);
					break;

				case LIST:
					subPart = elementCategory.equals(LIST_ITEM);
					break;

				default:
					break;
				}
			} else {
				subPart = this.equals(SENTENCE) || this.equals(LIST_ITEM);
			}
		}
		return subPart;
	}
}
