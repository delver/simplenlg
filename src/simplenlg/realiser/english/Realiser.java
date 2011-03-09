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
package simplenlg.realiser.english;

import java.util.List;

import simplenlg.format.english.TextFormatter;
import simplenlg.framework.DocumentCategory;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGModule;
import simplenlg.lexicon.Lexicon;
import simplenlg.morphology.english.MorphologyProcessor;
import simplenlg.orthography.english.OrthographyProcessor;
import simplenlg.syntax.english.SyntaxProcessor;

/**
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
 * @author D. Westwater, Data2Text Ltd
 *
 */
public class Realiser extends NLGModule {

	private MorphologyProcessor morphology;
	private OrthographyProcessor orthography;
	private SyntaxProcessor syntax;
	private NLGModule formatter = null;
	private boolean debug = false;
	
	/**
	 * create a realiser (no lexicon)
	 */
	public Realiser() {
		super();
		initialise();
	}
	
	/** Create a realiser with a lexicon (should match lexicon used for NLGFactory)
	 * @param lexicon
	 */
	public Realiser(Lexicon lexicon) {
		this();
		setLexicon(lexicon);
	}

	@Override
	public void initialise() {
		this.morphology = new MorphologyProcessor();
		this.morphology.initialise();
		this.orthography = new OrthographyProcessor();
		this.orthography.initialise();
		this.syntax = new SyntaxProcessor();
		this.syntax.initialise();
		this.formatter = new TextFormatter();
	}

	@Override
	public NLGElement realise(NLGElement element) {
		if (this.debug) {
			System.out.println("INITIAL TREE\n"); //$NON-NLS-1$
			System.out.println(element.printTree(null));
		}
		NLGElement postSyntax = this.syntax.realise(element);
		if (this.debug) {
			System.out.println("\nPOST-SYNTAX TREE\n"); //$NON-NLS-1$
			System.out.println(postSyntax.printTree(null));
		}
		NLGElement postMorphology = this.morphology.realise(postSyntax);
		if (this.debug) {
			System.out.println("\nPOST-MORPHOLOGY TREE\n"); //$NON-NLS-1$
			System.out.println(postMorphology.printTree(null));
		}
		NLGElement postOrthography = this.orthography.realise(postMorphology);
		if (this.debug) {
			System.out.println("\nPOST-ORTHOGRAPHY TREE\n"); //$NON-NLS-1$
			System.out.println(postOrthography.printTree(null));
		}
		NLGElement postFormatter = null;
		if (this.formatter != null) {
			postFormatter = this.formatter.realise(postOrthography);
			if (this.debug) {
				System.out.println("\nPOST-FORMATTER TREE\n"); //$NON-NLS-1$
				System.out.println(postFormatter.printTree(null));
			}
		} else {
			postFormatter = postOrthography;
		}
		return postFormatter;
	}
	
	/** Convenience class to realise any NLGElement as a sentence
	 * @param element
	 * @return String realisation of the NLGElement
	 */
	public String realiseSentence(NLGElement element) {
		NLGElement realised = null;
		if (element instanceof DocumentElement)
			realised = realise(element);
		else {
			DocumentElement sentence = new DocumentElement(DocumentCategory.SENTENCE, null);
			sentence.addComponent(element);
			realised = realise(sentence);
		}
		
		if (realised == null)
			return null;
		else
			return realised.getRealisation();
	}

	@Override
	public List<NLGElement> realise(List<NLGElement> elements) {
		return null;
	}

	@Override
	public void setLexicon(Lexicon newLexicon) {
		this.syntax.setLexicon(newLexicon);
		this.morphology.setLexicon(newLexicon);
		this.orthography.setLexicon(newLexicon);
	}

	public void setFormatter(NLGModule formatter) {
		this.formatter = formatter;
	}
	
	public void setDebugMode(boolean debugOn) {
		this.debug = debugOn;
	}
}
