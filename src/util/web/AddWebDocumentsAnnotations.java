package util.web;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import util.annotations.WebDocuments;
import util.misc.Common;
import util.misc.MainArgsProcessor;
import util.trace.Tracer;
// format
//@WebDocuments("foo")
public class AddWebDocumentsAnnotations {
	
	static final String WEB_DOCUMENTS_FULL_NAME = WebDocuments.class.getName();
	static final String WEB_DOCUMENTS_SHORT_NAME = WebDocuments.class.getSimpleName();
	static final String PACKAGE_KEY_WORD = "package";
    static String IMPORT_WEB_DOCUMENTS_DECLARATION = "import " + WEB_DOCUMENTS_FULL_NAME;
    static  String  DOCUMENT_SUFFIX = ".pptx";
	static  String packageName;	
	static String documentNameWithoutPrefixAndSuffix;
    
    static void initialize (StringBuilder contents) {
    	packageName = Common.getNameFollowingKeyword(contents, PACKAGE_KEY_WORD);
    	documentNameWithoutPrefixAndSuffix = toDocumentNameWithoutSuffix(packageName);

    }
    
    static String toDocumentNameWithoutSuffix(String aPackageName) {
    	StringBuilder stringBuilder = new StringBuilder(aPackageName.length());
    	if (aPackageName.length() == 0) return stringBuilder.toString();
    	int startIndex = 0 ;
    	for (int i = 0; i < startLevel; i++) {
    		startIndex = aPackageName.indexOf('.', startIndex);
    		if (startIndex == -1) {
    			Tracer.info("Package name " + aPackageName + " has lesss than " + startLevel + " levels. No annotation added for files in it.");
    			return null;
    		}
    	}
    	startIndex = (startIndex == 0)?0:startIndex + 1;
    	boolean changeCase = true;
    	for (int i = startIndex; i < aPackageName.length(); i++) {
    		char ch = aPackageName.charAt(i);
    		if (ch == '_' || ch == '.')
    			changeCase = true;
    		else if (changeCase) {
    			changeCase = false;
    			stringBuilder.append(Character.toUpperCase(ch));    			
    		} else {
    			stringBuilder.append(ch);
    		}
    	}
    	return stringBuilder.toString();
    	
    }

	
	
	public static void processFile(String fileName, List<String> aSuffixList) {
		try {

			File file = new File(fileName);
			if (!file.exists()) {
				System.err.println("File does not exist:" + fileName);
			}
			processFile(file, aSuffixList);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static int getEndOfLineAfterKeyWord(StringBuilder contents, String aKeyWord) {
		int keywordIndex = contents.indexOf(PACKAGE_KEY_WORD);
		int semiColonIndex =  contents.indexOf(";", keywordIndex);
		return contents.indexOf(Common.NL, semiColonIndex);
	}
	
	public static void addAnnotations(StringBuilder contents, List<String> aDocumentTypeList) {
		
		boolean importExists = contents.indexOf(IMPORT_WEB_DOCUMENTS_DECLARATION) > -1;
//		if (importExists) // manually added annotation
//			return;	
		if (!importExists) {
			
			int NLIndex = getEndOfLineAfterKeyWord(contents, PACKAGE_KEY_WORD);
			contents.insert(NLIndex + 1, IMPORT_WEB_DOCUMENTS_DECLARATION + ";" + Common.NL);
		}
		
		int classDeclarationIndex = contents.indexOf("public");
		if (classDeclarationIndex == -1)
			classDeclarationIndex = contents.indexOf("class");
		if (classDeclarationIndex == -1)
			return;	
		int insertionIndex = classDeclarationIndex;
//		String[] format = new String[]{"entry1", "entry2"};
		String annotation = "@" + WEB_DOCUMENTS_SHORT_NAME + "({";
;		
		List<String> anImportDeclarationList = new ArrayList();
		for (int i = 0; i < aDocumentTypeList.size(); i++ ) {
			String aDocumentType = aDocumentTypeList.get(i);
			String[] prefixAndSuffix = Common.getLeftAndRightInColonSeparatedString(aDocumentType);
			if (prefixAndSuffix == null || prefixAndSuffix.length != 2) {
				Tracer.error("Illegal document type:" + prefixAndSuffix);
				return;
			}
			annotation += "\"" + prefixAndSuffix[1] + "/" +  documentNameWithoutPrefixAndSuffix + prefixAndSuffix[0] + "\"";
			if (i <  aDocumentTypeList.size() - 1) {
				annotation += ", ";
//			String annotation = webDocumentsAnnotationWithouArrayItems + aSuffix + "\")" + Common.NL;
//			if (contents.indexOf(annotation) == -1) {
				
//				insertionIndex += annotation.length();
			}			
		}
		annotation +=  "})\n";
		contents.insert(insertionIndex, annotation);
	}

	public static void processFile(File file, List<String> aDocumentTypeList) {
		try {

			// StringBuilder contents = readFile(fileName);
			StringBuilder contents = Common.readFile(file);
			if (contents == null)
				return;
			initialize(contents);
			if (documentNameWithoutPrefixAndSuffix == null)
				return;
			boolean annotationExists = contents.indexOf(WEB_DOCUMENTS_SHORT_NAME) != -1;
			if (annotationExists) {
				Tracer.info("Not adding WebDocuments annotation to file " + file.getName() + " as it may exist");
				return;
			}
			addAnnotations(contents, aDocumentTypeList);
			// writeFile(fileName, contents.toString());
			Common.writeText(file, contents.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void processDirectory(String aDirectoryName, List<String> aDocumentTypeList) {
		File directory = new File(aDirectoryName);
		if (!directory.exists()) {
			System.err.println("Directory does not exist:" + aDirectoryName);
			return;
		}
		processDirectory(directory, aDocumentTypeList);

	}

	public static void processDirectory(File directory, List<String> aDocumentTypeList) {
		File[] children = directory.listFiles();
		for (File child : children) {
			if (child.isDirectory()) {
				processDirectory(child, aDocumentTypeList);
			} else {
				if (child.getName().endsWith("java")) {						
					processFile(child, aDocumentTypeList);
				}
			}
		}

	}

	public static void close(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException ignored) {
		}
	}

	public static String[] getPackageAndURL(String aString) {
		String[] retVal = new String[2];
		int colonIndex = aString.indexOf(":");
		if (colonIndex == -1) {
			Tracer.error("Excepting : in " + aString);
			return retVal;
		}
		retVal[0] = aString.substring(0, colonIndex);
		retVal[1] = aString.substring(colonIndex + 1);
		return retVal;

	}

	public static final String DOCUMENT_TYPE_SPECIFICATION = "-t";
	public static final String DIRECTORY = "-d";
	public static final String START_LEVEL = "-sl";


	public static final String[] boolFlags = {};
	public static final String[] regFlags = {START_LEVEL};
	public static final String[] dupFlags = { DIRECTORY, DOCUMENT_TYPE_SPECIFICATION};
	
	static int startLevel = 0;

	public static void main(String args[]) {
		Hashtable table = MainArgsProcessor.toTable(regFlags, boolFlags,
				dupFlags, args);

		Tracer.showInfo(true);
		
		String sl = (String) table.get(START_LEVEL);
		try {
			startLevel = Integer.parseInt(sl);
			
		} catch (Exception e) {
			startLevel = 0;
		}
		List<String> types = (List<String>) table
				.get(DOCUMENT_TYPE_SPECIFICATION);
		
		List<String> directories = (List<String>) table.get(DIRECTORY);
		if (directories == null) return;
		
			for (String dir : directories) {
				processDirectory(dir, types);
			}
		
		
		
		
		// processFile ("ABMISpreadsheet.html");

	}

}
