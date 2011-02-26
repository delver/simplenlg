/*
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.PrintWriter;

import simplenlg.framework.LexicalCategory;
import simplenlg.framework.WordElement;

// this class reads in a word list, looks up the words in the NIH lexicon,
// and writes the XML words into an output file
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
 */
public class dumpXML {
	
	// filenames
	static final String DB_FILENAME = "E:\\NIHDB\\lexAccess2009";  // DB location
	static final String WORDLIST_FILENAME = "E:\\NIHDB\\wordlist.csv";  // word list
	static final String XML_FILENAME = "E:\\NIHDB\\default-lexicon.xml";  // word list

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Lexicon lex = new NIHDBLexicon(DB_FILENAME);
		try {
			LineNumberReader wordListFile = new LineNumberReader(new FileReader (WORDLIST_FILENAME));
			FileWriter xmlFile = new FileWriter(XML_FILENAME);
			xmlFile.write(String.format("<lexicon>%n"));
			String line = wordListFile.readLine();
			while (line != null) {
				String[] cols = line.split(",");
				String base = cols[0];
				String cat = cols[1];
				WordElement word = null;
				if (cat.equalsIgnoreCase("noun"))
					word = lex.getWord(base, LexicalCategory.NOUN);
				else if (cat.equalsIgnoreCase("verb"))
					word = lex.getWord(base, LexicalCategory.VERB);
				else if (cat.equalsIgnoreCase("adv"))
					word = lex.getWord(base, LexicalCategory.ADVERB);
				else if (cat.equalsIgnoreCase("adj"))
					word = lex.getWord(base, LexicalCategory.ADJECTIVE);
				else if (cat.equalsIgnoreCase("det"))
					word = lex.getWord(base, LexicalCategory.DETERMINER);
				else if (cat.equalsIgnoreCase("prep"))
					word = lex.getWord(base, LexicalCategory.PREPOSITION);
				else if (cat.equalsIgnoreCase("pron"))
					word = lex.getWord(base, LexicalCategory.PRONOUN);
				else if (cat.equalsIgnoreCase("conj"))
					word = lex.getWord(base, LexicalCategory.CONJUNCTION);
				else if (cat.equalsIgnoreCase("modal"))
					word = lex.getWord(base, LexicalCategory.MODAL);
				else if (cat.equalsIgnoreCase("interjection"))
					word = lex.getWord(base, LexicalCategory.NOUN); // Kilgarriff;s interjections are mostly nouns in the lexicon
				
				if (word == null)
					System.out.println("Missing " + base + ":" + cat);
				else
					xmlFile.write(word.toXML());
				line = wordListFile.readLine();;
			}
			xmlFile.write(String.format("</lexicon>%n"));
			wordListFile.close();
			xmlFile.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lex.close();
		System.out.println("done");

	}

}
