package util.web;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import util.annotations.WebDocuments;
import util.misc.Common;
import util.misc.MainArgsProcessor;
import util.trace.Tracer;

// Sample Input:
//A NAME="9"></A>@util.annotations.HTMLDocumentation({<FONT ID="StringLiteral">"4_State_Notes.pdf"</FONT>,
//	<A NAME="10"></A>    <FONT ID="StringLiteral">"http://delivery.acm.org/10.1145/2160000/2157159/p69-dewan.pdf?ip=152.2.128.61&amp;acc=ACTIVE%20SERVICE&amp;CFID=88842060&amp;CFTOKEN=32985736&amp;__acm__=1331662176_de4ded7d47b745b5f8c6f6256cf58c25"</FONT>
//	<A NAME="11"></A>    })

// Sample Replacement:
//<A NAME="9"></A>@util.annotations.HTMLDocumentation({<A HREF="http://www.cs.unc.edu/~carterjl/teaching/notes/4_State_Notes.pdf">"4_State_Notes.pdf",
//	<A NAME="10"></A>    <A HREF="http://delivery.acm.org/10.1145/2160000/2157159/p69-dewan.pdf?ip=152.2.128.61&amp;acc=ACTIVE%20SERVICE&amp;CFID=88842060&amp;CFTOKEN=32985736&amp;__acm__=1331662176_de4ded7d47b745b5f8c6f6256cf58c25">"http://delivery.acm.org/10.1145/2160000/2157159/p69-dewan.pdf?ip=152.2.128.61&amp;acc=ACTIVE%20SERVICE&amp;CFID=88842060&amp;CFTOKEN=32985736&amp;__acm__=1331662176_de4ded7d47b745b5f8c6f6256cf58c25"
//	<A NAME="11"></A>    })

public class J2HWithDocumentLinks {

	static String HTML_PATTERN = WebDocuments.class.getSimpleName();
	static String LINK_START = "<A HREF=\"";
	static String LINK_END = "\">";
	// each url denotation is a string quote so it is associated with the
	// following:
	static String START_STRING_FONT = "<FONT ID=\"StringLiteral\">";
	static String END_STRING_FRONT = "</FONT>";
	static String HTML_SUFFIX_1 = ".html";
	static String HTML_SUFFIX_2 = ".htm";
	static String PACKAGE_KEYWORD = "<FONT ID=\"Package\">package</FONT>";
	static String CLASS_KEYWORD = "<FONT ID=\"Class\">class</FONT>";

	// public static StringBuilder readFile (String filename) throws IOException
	// {
	// StringBuilder text = new StringBuilder();
	// try {
	// Scanner scanner = new Scanner(new FileInputStream(filename));
	//
	// while (scanner.hasNextLine()){
	// String line = scanner.nextLine();
	// text.append(line + NL);
	// }
	// } finally {
	// return text;
	// }
	// }

	public static String getNameFollowingKeyword(StringBuilder contents,
			String keyWordName) {
		int keywordStartIndex = contents.indexOf(keyWordName);
		if (keywordStartIndex == -1)
			return null;
		int keywordEndIndex = keywordStartIndex + keyWordName.length();
		int nameStartIndex = keywordEndIndex;
		while (nameStartIndex < contents.length()) {
			char ch = contents.charAt(nameStartIndex);
			if (Common.isNameChar(ch))
				break;
			nameStartIndex++;

		}
		int nameEndIndex = nameStartIndex + 1;
		while (nameEndIndex < contents.length()) {
			char ch = contents.charAt(nameEndIndex);
			if (Common.isNameChar(ch))
				nameEndIndex++;
			else
				break;

		}
		// int nameEndIndex = contents.indexOf("", nameStartIndex);
		if (nameEndIndex < 0)
			return null;
		String name = contents.substring(nameStartIndex, nameEndIndex);
		return name;

	}

	// messy code because not copying input, editing it in place
	public static void processHTMLAnnotation(StringBuilder contents) {
		String packageName = getNameFollowingKeyword(contents, PACKAGE_KEYWORD);
		String className = getNameFollowingKeyword(contents, CLASS_KEYWORD);
		if (packageName != null)
			className = packageName + "." + className;

		try {
			int patternStartIndex = contents.indexOf(HTML_PATTERN);
			if (patternStartIndex < 0)
				return;
			// the previous might have been an import			
			int secondPatternStartIndex = contents.indexOf(HTML_PATTERN, patternStartIndex + 1);
			if (secondPatternStartIndex >= 0) {
				patternStartIndex = secondPatternStartIndex;
			}
			int valueStartIndex = 0;
			int valueEndIndex = patternStartIndex + 1;
			int valueLimitIndex = contents.indexOf(")", patternStartIndex + 1);
			if (valueLimitIndex < 0)
				return;
			;
			while (true) {
				int startStringFont = contents.indexOf(START_STRING_FONT,
						valueEndIndex + 1);
				if (startStringFont < 0) {
					break;
//					Tracer.error(START_STRING_FRONT + " not found");
//					for (int i = valueEndIndex; i < contents.length(); i++) {
//						System.out.print(contents.charAt(i));
//					}
//					System.exit(-1);
				}
				Tracer.info("found start string font before:" + contents.charAt(startStringFont + START_STRING_FONT.length() + 1));
				// choose html font over string font
				contents.delete(startStringFont, startStringFont
						+ START_STRING_FONT.length());
//				int endStringFont = contents.indexOf(END_STRING_FRONT,
//						valueEndIndex + 1);
				int endStringFont = contents.indexOf(END_STRING_FRONT,
						startStringFont + 1);
				contents.delete(endStringFont,
						endStringFont + END_STRING_FRONT.length());
				

				valueStartIndex = contents.indexOf("\"", startStringFont) + 1;

				if (valueStartIndex < 0 || valueStartIndex > valueLimitIndex)
					break;
				valueEndIndex = contents.indexOf("\"", valueStartIndex + 1);
				String urlDenotion = contents.substring(valueStartIndex,
						valueEndIndex);
				
				contents.delete(valueStartIndex - 1, valueEndIndex + 1);
				Tracer.info("URL Denotion:" + urlDenotion);
				String actualURL = DocPackageRegistry.toMaybeExpandedURLString(
						className, urlDenotion);
				String replacementURL = urlDenotion;
				if (actualURL == urlDenotion) {
					replacementURL = PackageRegistry.toKeyword(urlDenotion);
				}
				// String replacementString = LINK_START + actualURL + LINK_END
				// + "\"" + urlDenotion + "\"";
				String replacementString = LINK_START + actualURL + LINK_END
						+ "\"" + replacementURL + "\"";
				
				Tracer.info("Actual URL:" + replacementString);
				contents.insert(valueStartIndex - 1, replacementString);
				// System.out.println(contents);
				int increasedLength = replacementString.length()
						- urlDenotion.length();
				valueEndIndex += increasedLength - END_STRING_FRONT.length();
				valueLimitIndex += increasedLength
						- START_STRING_FONT.length()
						- END_STRING_FRONT.length();
				// System.out.println(contents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void processFile(String fileName) {
		try {

			File file = new File(fileName);
			if (!file.exists()) {
				System.out.println("File does not exist:" + fileName);
			}
			processFile(file);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void processFile(File file) {
		try {

			// StringBuilder contents = readFile(fileName);
			StringBuilder contents = Common.readFile(file);
			if (contents == null)
				return;
			processHTMLAnnotation(contents);
			// writeFile(fileName, contents.toString());
			Common.writeText(file, contents.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void processDirectory(String aDirectoryName) {
		File directory = new File(aDirectoryName);
		if (!directory.exists()) {
			System.out.println("Directory does not exist:" + aDirectoryName);
			return;
		}
		processDirectory(directory);

	}

	public static void processDirectory(File directory) {
		File[] children = directory.listFiles();
		for (File child : children) {
			if (child.isDirectory()) {
				processDirectory(child);
			} else {
				if (child.getName().endsWith(HTML_SUFFIX_1)
						|| child.getName().endsWith(HTML_SUFFIX_2)) {
					processFile(child);
				}

			}
		}

	}

	public static final String J2H_DIRECTORY = "-d";
	public static final String PACKAGE_SPECIFICATION = "-p";
	public static final String[] boolFlags = {};
	public static final String[] regFlags = {};
	public static final String[] dupFlags = { J2H_DIRECTORY,
			PACKAGE_SPECIFICATION };

	public static void main(String args[]) {
		Hashtable table = MainArgsProcessor.toTable(regFlags, boolFlags,
				dupFlags, args);

		Tracer.showInfo(true);

		List<String> packageSpecifications = (List<String>) table
				.get(PACKAGE_SPECIFICATION);
		// if (packageSpecifications != null) {
		for (String aSpecification : packageSpecifications) {
			String[] pair = Common.getLeftAndRightInColonSeparatedString(aSpecification);
			DocPackageRegistry.register(pair[0], pair[1]);
		}
		// }
		List<String> directories = (List<String>) table.get(J2H_DIRECTORY);
		if (directories.size() == 0)
			processDirectory("htmls");
		else {
			// processDirectory((String) table.get(J2H_DIRECTORY));
			for (String dir : directories) {
				processDirectory(dir);
			}
		}
		// processFile ("ABMISpreadsheet.html");

	}

}
