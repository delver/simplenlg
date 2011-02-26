/*
 * 
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
package simplenlg.lexicon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import simplenlg.framework.LexicalCategory;
import simplenlg.framework.WordElement;

/** This class contains a set of lexicons, which are searched in
 * order for the specified word
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
 * @author ereiter
 *
 */
public class MultipleLexicon extends Lexicon {
	
	/* if this flag is true, all lexicons are searched for
	 * this word, even after a match is found
	 * it is false by default
	 * */
	private boolean alwaysSearchAll = false;
	
	/* list of lexicons, in order in which they are searched */
	private List<Lexicon> lexiconList = null;

	/**********************************************************************/
	// constructors
	/**********************************************************************/
	
	/**
	 * create an empty multi lexicon
	 */
	public MultipleLexicon() {
		super();
		lexiconList = new ArrayList<Lexicon>();
		alwaysSearchAll = false;
	}
	
	/** create a multi lexicon with the specified lexicons
	 * @param lexicons
	 */
	public MultipleLexicon(Lexicon... lexicons) {
		this();
		for (Lexicon lex: lexicons)
			lexiconList.add(lex);
	}
	
	/**********************************************************************/
	// routines to add more lexicons, change flags
	/**********************************************************************/

	/** add lexicon at beginning of list (is searched first)
	 * @param lex
	 */
	public void addInitialLexicon(Lexicon lex) {
		lexiconList.add(0, lex);
	}

	/** add lexicon at end of list (is searched last)
	 * @param lex
	 */
	public void addFinalLexicon(Lexicon lex) {
		lexiconList.add(0, lex);
	}

	/**
	 * @return the alwaysSearchAll
	 */
	public boolean isAlwaysSearchAll() {
		return alwaysSearchAll;
	}

	/**
	 * @param alwaysSearchAll the alwaysSearchAll to set
	 */
	public void setAlwaysSearchAll(boolean alwaysSearchAll) {
		this.alwaysSearchAll = alwaysSearchAll;
	}

	/**********************************************************************/
	// main methods
	/**********************************************************************/

	/* (non-Javadoc)
	 * @see simplenlg.lexicon.Lexicon#getWords(java.lang.String, simplenlg.features.LexicalCategory)
	 */
	@Override
	public List<WordElement> getWords(String baseForm, LexicalCategory category) {
		List<WordElement> result = new ArrayList<WordElement>();
		for (Lexicon lex: lexiconList) {
			List<WordElement> lexResult = lex.getWords(baseForm, category);
			if (lexResult != null && !lexResult.isEmpty()) {
				result.addAll(lexResult);
				if (!alwaysSearchAll)
					return result;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see simplenlg.lexicon.Lexicon#getWordsByID(java.lang.String)
	 */
	@Override
	public List<WordElement> getWordsByID(String id) {
		List<WordElement> result = new ArrayList<WordElement>();
		for (Lexicon lex: lexiconList) {
			List<WordElement> lexResult = lex.getWordsByID(id);
			if (lexResult != null && !lexResult.isEmpty()) {
				result.addAll(lexResult);
				if (!alwaysSearchAll)
					return result;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see simplenlg.lexicon.Lexicon#getWordsFromVariant(java.lang.String, simplenlg.features.LexicalCategory)
	 */
	@Override
	public List<WordElement> getWordsFromVariant(String variant, LexicalCategory category) {
		List<WordElement> result = new ArrayList<WordElement>();
		for (Lexicon lex: lexiconList) {
			List<WordElement> lexResult = lex.getWordsFromVariant(variant, category);
			if (lexResult != null && !lexResult.isEmpty()) {
				result.addAll(lexResult);
				if (!alwaysSearchAll)
					return result;
			}
		}
		return result;
	}


	/**********************************************************************/
	// other methods
	/**********************************************************************/

	/* (non-Javadoc)
	 * @see simplenlg.lexicon.Lexicon#close()
	 */
	@Override
	public void close() {
		// close component lexicons
		for (Lexicon lex: lexiconList)
			lex.close();
	}


}
