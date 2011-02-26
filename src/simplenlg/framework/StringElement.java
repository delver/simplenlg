/*
 * StringElement.java - the element for representing canned text.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import simplenlg.features.Feature;

/**
 * <p>
 * This class defines an element for representing canned text within the
 * SimpleNLG library. Once assigned a value, the string element should not be
 * changed by any other processors.
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
 * 
 */
public class StringElement extends NLGElement {

	/**
	 * Constructs a new string element representing some canned text.
	 * 
	 * @param value
	 *            the text for this string element.
	 */
	public StringElement(String value) {
		setCategory(PhraseCategory.CANNED_TEXT);
		setFeature(Feature.ELIDED, false);
		setRealisation(value);
	}

	/**
	 * The string element contains no children so this method will always return
	 * an empty list.
	 */
	@Override
	public List<NLGElement> getChildren() {
		return new ArrayList<NLGElement>();
	}

	@Override
	public String toString() {
		return getRealisation();
	}

	@Override
	public String printTree(String indent) {
		StringBuffer print = new StringBuffer();
		print
				.append("StringElement: content=\"").append(getRealisation()).append('\"'); //$NON-NLS-1$
		Map<String, Object> features = this.getAllFeatures();

		if (features != null) {
			print.append(", features=").append(features.toString()); //$NON-NLS-1$
		}
		print.append('\n');
		return print.toString();
	}
}
