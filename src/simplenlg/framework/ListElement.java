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
import java.util.Map;

import simplenlg.features.InternalFeature;

/**
 * <p>
 * <code>ListElement</code> is used to define elements that can be grouped
 * together and treated in a similar manner. The list element itself adds no
 * additional meaning to the realisation. For example, the syntax processor
 * takes a phrase element and produces a list element containing inflected word
 * elements. Phrase elements only have meaning within the syntax processing
 * while the morphology processor (the next in the sequence) needs to work with
 * inflected words. Using the list element helps to keep the inflected word
 * elements together.
 * </p>
 * 
 * <p>
 * There is no sorting within the list element and components are added in the
 * order they are given.
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
public class ListElement extends NLGElement {

	/**
	 * Creates a new list element with no components.
	 */
	public ListElement() {
		// Do nothing
	}

	/**
	 * Creates a new list element containing the given components.
	 * 
	 * @param components
	 *            the initial components for this list element.
	 */
	public ListElement(List<NLGElement> components) {
		this();
		this.addComponents(components);
	}

	@Override
	public List<NLGElement> getChildren() {
		return getFeatureAsElementList(InternalFeature.COMPONENTS);
	}

	/**
	 * Creates a new list element containing the given component.
	 * 
	 * @param newComponent
	 *            the initial component for this list element.
	 */
	public ListElement(NLGElement newComponent) {
		this();
		this.addComponent(newComponent);
	}

	/**
	 * Adds the given component to the list element.
	 * 
	 * @param newComponent
	 *            the <code>NLGElement</code> component to be added.
	 */
	public void addComponent(NLGElement newComponent) {
		List<NLGElement> components = getFeatureAsElementList(InternalFeature.COMPONENTS);
		if (components == null) {
			components = new ArrayList<NLGElement>();
		}
		setFeature(InternalFeature.COMPONENTS, components);
		components.add(newComponent);
	}

	/**
	 * Adds the given components to the list element.
	 * 
	 * @param newComponents
	 *            a <code>List</code> of <code>NLGElement</code>s to be added.
	 */
	public void addComponents(List<NLGElement> newComponents) {
		List<NLGElement> components = getFeatureAsElementList(InternalFeature.COMPONENTS);
		if (components == null) {
			components = new ArrayList<NLGElement>();
		}
		setFeature(InternalFeature.COMPONENTS, components);
		components.addAll(newComponents);
	}

	/**
	 * Replaces the current components in the list element with the given list.
	 * 
	 * @param newComponents
	 *            a <code>List</code> of <code>NLGElement</code>s to be used as
	 *            the components.
	 */
	public void setComponents(List<NLGElement> newComponents) {
		setFeature(InternalFeature.COMPONENTS, newComponents);
	}

	@Override
	public String toString() {
		return getChildren().toString();
	}

	@Override
	public String printTree(String indent) {
		String thisIndent = indent == null ? " |-" : indent + " |-"; //$NON-NLS-1$ //$NON-NLS-2$
		String childIndent = indent == null ? " | " : indent + " | "; //$NON-NLS-1$ //$NON-NLS-2$
		String lastIndent = indent == null ? " \\-" : indent + " \\-"; //$NON-NLS-1$ //$NON-NLS-2$
		String lastChildIndent = indent == null ? "   " : indent + "   "; //$NON-NLS-1$ //$NON-NLS-2$
		StringBuffer print = new StringBuffer();
		print.append("ListElement: features={"); //$NON-NLS-1$

		Map<String, Object> features = getAllFeatures();
		for (String eachFeature : features.keySet()) {
			print.append(eachFeature).append('=').append(
					features.get(eachFeature).toString()).append(' ');
		}
		print.append("}\n"); //$NON-NLS-1$

		List<NLGElement> children = getChildren();
		int length = children.size() - 1;
		int index = 0;

		for (index = 0; index < length; index++) {
			print.append(thisIndent).append(
					children.get(index).printTree(childIndent));
		}
		if (length >= 0) {
			print.append(lastIndent).append(
					children.get(length).printTree(lastChildIndent));
		}
		return print.toString();
	}

	/**
	 * Retrieves the number of components in this list element.
	 * @return the number of components.
	 */
	public int size() {
		return getChildren().size();
	}

	/**
	 * Retrieves the first component in the list.
	 * @return the <code>NLGElement</code> at the top of the list.
	 */
	public NLGElement getFirst() {
		List<NLGElement> children = getChildren();
		return children == null ? null : children.get(0);
	}
}
