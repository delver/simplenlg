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
package simplenlg.test.xmlrealiser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.junit.Ignore;

import simplenlg.xmlrealiser.Recording;
import simplenlg.xmlrealiser.XMLRealiser;
import simplenlg.xmlrealiser.XMLRealiserException;
import simplenlg.xmlrealiser.XMLRealiser.LexiconType;
import simplenlg.xmlrealiser.wrapper.DocumentRealisation;
import simplenlg.xmlrealiser.wrapper.RecordSet;

/**
 * This class is intended for regression testing of the XMl realiser framework.
 * It works by accepting an xml file (representing the test cases) and a path to
 * the lexicon file to use, and instantiating an <code>XMLRealiser</code> to map
 * the XML to simplenlg objects. It outputs the results in an XML file, named
 * like the input file (with the suffix <i>out</i>), in which the realisation
 * has been appended to each test case.
 * 
 * @author Christopher Howell, Agfa Healthcare Corporation
 */
@Ignore public class Tester {

	/**
	 * The main method. The arguments expecetd are:
	 * <OL>
	 * <LI>-test</LI>
	 * <LI>path to xml file with recording element, or to a directory containing
	 * such files, OR path to file with request element, or to a directory
	 * containing such files</LI>
	 * <LI>path to the file containing the NIH DB lexicon to use</LI>
	 * </OL>
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		boolean processTestSets;
		String testsPath = "";
		String xmlFile = "";
		String lexDB;
		int ix = 0;
		String usage = "usage: Tester [-test <xml file with Recording element or path to such files> | "
				+ "<xml file with Request element>] <NIH db location>";

		if (args.length < 2) {
			System.out.println(usage);
			return;
		}

		if (args[ix].matches("-test")) {
			ix++;
			processTestSets = true;
			testsPath = args[ix++];
		} else {
			processTestSets = false;
			xmlFile = args[ix++];
		}

		if (args.length < ix + 1) {
			System.out.println(usage);
			return;
		}

		lexDB = (String) args[ix];
		XMLRealiser.setLexicon(LexiconType.NIHDB, lexDB);

		if (processTestSets) {
			Collection<File> testFiles;
			FilenameFilter filter = new TestFilenameFilter();
			File path = new File(testsPath);
			if (path.isDirectory()) {
				testFiles = listFiles(path, filter, true);
			} else {
				testFiles = new Vector<File>();
				testFiles.add(path);
			}

			for (File testFile : testFiles) {
				try {
					FileReader reader = new FileReader(testFile);
					RecordSet input = XMLRealiser.getRecording(reader);
					RecordSet output = new RecordSet();
					output.setName(input.getName());

					for (DocumentRealisation test : input.getRecord()) {
						DocumentRealisation testOut = new DocumentRealisation();
						testOut.setName(test.getName());
						testOut.setDocument(test.getDocument());
						String realisation = XMLRealiser.realise(test
								.getDocument());
						testOut.setRealisation(realisation);
						output.getRecord().add(testOut);
					}

					String outFileName = testFile.getAbsolutePath();
					outFileName = outFileName
							.replaceFirst("\\.xml$", "Out.xml");
					FileOutputStream outFile = new FileOutputStream(outFileName);
					outFile.getChannel().truncate(0);
					Recording.writeRecording(output, outFile);

				} catch (XMLRealiserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {

			String result = "";
			FileReader reader;
			try {
				reader = new FileReader(xmlFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			try {
				result = XMLRealiser.realise(XMLRealiser.getRequest(reader)
						.getDocument());
			} catch (XMLRealiserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(result);
		}
	}

	// /////////////// copied code ///////////////////////////////
	// Copied from http://snippets.dzone.com/posts/show/1875
	/**
	 * List files as array.
	 * 
	 * @param directory
	 *            the directory
	 * @param filter
	 *            the filter
	 * @param recurse
	 *            the recurse
	 * @return the file[]
	 */
	public static File[] listFilesAsArray(File directory,
			FilenameFilter filter, boolean recurse) {
		Collection<File> files = listFiles(directory, filter, recurse);
		// Java4: Collection files = listFiles(directory, filter, recurse);

		File[] arr = new File[files.size()];
		return files.toArray(arr);
	}

	/**
	 * List files.
	 * 
	 * @param directory
	 *            the directory
	 * @param filter
	 *            the filter
	 * @param recurse
	 *            the recurse
	 * @return the collection
	 */
	public static Collection<File> listFiles(
	// Java4: public static Collection listFiles(
			File directory, FilenameFilter filter, boolean recurse) {
		// List of files / directories
		Vector<File> files = new Vector<File>();
		// Java4: Vector files = new Vector();

		// Get files / directories in the directory
		File[] entries = directory.listFiles();

		// Go over entries
		for (File entry : entries) {
			// Java4: for (int f = 0; f < files.length; f++) {
			// Java4: File entry = (File) files[f];

			// If there is no filter or the filter accepts the
			// file / directory, add it to the list
			if (filter == null || filter.accept(directory, entry.getName())) {
				files.add(entry);
			}

			// If the file is a directory and the recurse flag
			// is set, recurse into the directory
			if (recurse && entry.isDirectory()) {
				files.addAll(listFiles(entry, filter, recurse));
			}
		}

		// Return collection of files
		return files;
	}
}

// //////////////////////// end of copied code ////////////////////////

@Ignore class TestFilenameFilter implements FilenameFilter {
	@Override
	public boolean accept(File dir, String name) {
		if (name.endsWith(".xml") && !name.endsWith("Out.xml"))
			return true;
		else
			return false;
	}
}
