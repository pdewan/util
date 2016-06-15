/*
 * Created on Jun 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.misc;

import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.models.ABoundedBuffer;
import util.trace.Tracer;

//import bus.uigen.Message;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Common {
	
	
	public static int indexOf(Container parent, Component comp) {
		if (parent == null)
			return -1;
		return indexOf(parent.getComponents(), comp);
	}

	public static int indexOf(Object[] parent, Object child) {
		if (parent == null)
			return -1;
		for (int i = 0; i < parent.length; i++) {
			if (parent[i] == child)
				return i;
		}
		return -1;
	}

	public static String toString(Object[] anArray) {
		return toString(anArray, ", ");
//		String retVal = "";
//		if (anArray.length < 1)
//			return "";
//		retVal = anArray[0].toString();
//		for (int i = 1; i < anArray.length; i++) {
//			retVal += ", " + anArray[i];
//		}
//		return retVal;
	}
	public static String toString(Object[] anArray, String aSeparator) {
		String retVal = "";
		if (anArray.length < 1)
			return "";
//		retVal = anArray[0].toString(); 
		retVal = "" + anArray[0]; // element can be null
		for (int i = 1; i < anArray.length; i++) {
			retVal += aSeparator + anArray[i];
		}
		return retVal;
	}

	/*
	 * public static void sort (List objectList) { Hashtable stringToObject =
	 * new Hashtable(); Vector strings = new Vector(); for (int i = 0; i <
	 * objectList.size(); i++) { String string = objectList.get(i).toString();
	 * strings.addElement(string); stringToObject.put(string,
	 * objectList.get(i)); } Collections.sort(strings); for (int i = 0; i <
	 * strings.size(); i++) { objectList.set(i,
	 * stringToObject.get(strings.elementAt(i))); }
	 * 
	 * }
	 */
	public static Vector toVector(Enumeration elements) {
		Vector retVal = new Vector();
		while (elements.hasMoreElements())
			retVal.addElement(elements.nextElement());
		return retVal;

	}

	public static Object[] add(Object[] original, Vector newElements) {
		if (newElements == null)
			return original;
		Object[] newArray = new Object[original.length + newElements.size()];
		for (int i = 0; i < original.length; i++) {
			newArray[i] = original[i];
		}
		for (int i = 0; i < newElements.size(); i++)
			newArray[i + original.length] = newElements.elementAt(i);
		return newArray;

	}

	// public static Object deepCopy(Object o) {
	// if (o == null)
	// return null;
	// try {
	// Object o_copy;
	// ObjectOutputStream fo = new ObjectOutputStream(new
	// FileOutputStream("pasteFile"));
	// fo.writeObject(o);
	// fo.close();
	// ObjectInputStream fi = new ObjectInputStream(new
	// FileInputStream("pasteFile"));
	// //Object o_copy = fi.readObject();
	// o_copy = fi.readObject();
	// fi.close();
	// return o_copy;
	// } catch (Exception e) {
	// return null;
	// }
	// }

	public static Vector deepArrayToVector(Object[] a) {
		if (a == null)
			return null;
		Vector retVal = new Vector();
		for (int i = 0; i < a.length; i++) {
			Object nextElement = a[i];
			if (a[i] != null && a[i].getClass().isArray())
				nextElement = deepArrayToVector((Object[]) a[i]);
			retVal.addElement(nextElement);
		}
		return retVal;
	}

	public static ArrayList deepArrayToArrayList(Object[] a) {
		ArrayList retVal = new ArrayList();
		for (int i = 0; i < a.length; i++) {
			Object nextElement = a[i];
			if (a[i].getClass().isArray())
				nextElement = deepArrayToArrayList((Object[]) a[i]);
			retVal.add(nextElement);
		}
		return retVal;
	}

	public static void nestedArrayToMap(Object[][] source, Map target) {
		if (source == null)
			return;
		for (int i = 0; i < source.length; i++) {
			Object[] element = source[i];
			if (element.length != 2) {
				Tracer.warning("arrayToMap: Element " + element
						+ " not a pair. Ignoring it.");
				continue;
			}
			target.put(element[0], element[1]);
		}
	}

	public static Hashtable nestedArrayToHashtable(Object[][] source) {
		Hashtable retVal = new Hashtable();
		nestedArrayToMap(source, retVal);
		return retVal;
	}

	public static HashMap nestedArrayToHashmap(Object[][] source) {
		HashMap retVal = new HashMap();
		nestedArrayToMap(source, retVal);
		return retVal;
	}

	public static void arrayToList(Object[] source, java.util.List target) {
		if (source == null)
			return;
		for (int i = 0; i < source.length; i++) {
			target.add(source[i]);
		}
	}
	
	
	public static int indexOfIgnoreCase(List<String> list, String s) {
		if (list == null) return -1;
		for (int i = 0; i < list.size(); i++) {
			String element = list.get(i);
			if (element.equalsIgnoreCase(s))
				return i;
			
		}
		return -1;
	}
	
	 public static boolean contains(List aList, Object o) {
	       return indexOf (aList, o) != -1;
	    }
	 
	 public static boolean containsReference(Object[] aList, Object o) {
		 for (Object element:aList) {
			 if (element == o) {
				 return true;
			 }
		 }
		 return false;
	    }
	 
	 
	 public static  int indexOf(List aList, Object o) {
	        if (o == null) {
		            return aList.indexOf(o);
	        } else {
	            for (int i = 0 ; i < aList.size() ; i++)
	                if (o == aList.get(i))
	                    return i;
	        }
	        return -1;
	    }
	 
	
	public static boolean containsIgnoreCaseAndRemove(List<String> list, String s) {
		int index = indexOfIgnoreCase(list, s);
		if (index == -1) return false;
		list.remove(index);
		return true;
	}

	public static void arrayToSet(Object[] source, java.util.Set target) {
		if (source == null)
			return;
		for (int i = 0; i < source.length; i++) {
			target.add(source[i]);
		}
	}

	public static Set arrayToSet(Object[] source) {
		if (source == null) return null;
		Set retVal = new HashSet();
		arrayToSet(source, retVal);
		return retVal;
	}

	public static Vector arrayToVector(Object[] source) {
		if (source == null) return null;
		Vector retVal = new Vector();
		arrayToList(source, retVal);
		return retVal;
	}

	public static ArrayList arrayToArrayList(Object[] source) {
		if (source == null) return null;
		ArrayList retVal = new ArrayList();
		arrayToList(source, retVal);
		return retVal;
	}
	
	public static ArrayList<String> arrayToArrayList(List[] source) {
		if (source == null) return null;
		ArrayList<String> retVal = new ArrayList();
		arrayToList(source, retVal);
		return retVal;
	}
	

	public static char control(char c) {
		if (Character.isUpperCase(c)) {
			return (char) (c - 'A' + 1);
		} else if (Character.isLowerCase(c)) {

			return (char) (c - 'a' + 1);
		} else
			return c;
	}
	
	public static String classToSourceFileName(Class aClass) {
		return classNameToSourceFileName(aClass.getName());

	}
	
	public static String classToSourceFileNameInSrc(Class aClass) {
		return classNameToSourceFileNameInSrc(aClass.getName());

	}

	public static String classNameToSourceFileName(String className) {
		String fileName = className.replace('.', '/') + ".java";		
		return fileName;

	}
	public static String classNameToSourceFileNameInSrc(String className) {
		return "src/" + classNameToSourceFileName(className);
	}
	public static String classNameToSourceFileNameInSrc(Class aClass) {
		return "src/" + classToSourceFileName(aClass);
	}
	public static String classNameToBinaryFileName(String className) {
		String fileName = className.replace('.', '/') + ".class";		
		return fileName;

	}

	public static File open(Class c) {
		return open(c.getName());
	}

	public static File open(String className) {
		String fileName = classNameToSourceFileName(className);
		File file = new File(fileName);
		return file;
	}

	public static File open(String sourceDirectory, String className) {
		try {
			String fileName = "";
			if (sourceDirectory != null) {
				fileName = sourceDirectory + "/";
			}
			fileName += classNameToSourceFileName(className);
			// if (sourceDirectory == null)
			// fileName = toFileName(className);
			// else
			// fileName = sourceDirectory + "/" + toFileName(className);
			File file = new File(fileName);
			return file;
		} catch (Exception e) {
			return null;
		}
	}

	static public void append(StringBuffer sb, String s) {
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == '\t') {
				sb.append("    ");
			} else
				sb.append(s.charAt(i));
	}
	public static final String FILE_SEPARATOR = "/";


	public static String quotePath(String path) {
		if (!path.contains(" ")) return path;
	    boolean startSlash = path.startsWith("\\") || path.startsWith("/");
	    boolean endSlash = path.endsWith("\\") || path.endsWith("/");
	    String[] split = path.split("[\\\\/]+");

	    StringBuilder quotPath = new StringBuilder(path.length());

	    if (startSlash) {
	        quotPath.append(FILE_SEPARATOR);
	    }
	    
	    for(int i = 0; i < split.length; i ++) {
	    	String s = split[i];
	        if (s.contains(" ")) {
//	            s = "\"" + s + "\"";
	            s = "\\\"" + s + "\\\"";

	        }
	        quotPath.append(s);
	        if (i+1 < split.length) {
	            quotPath.append(FILE_SEPARATOR);
	        }
	    }
	    
	    if (endSlash) {
	        quotPath.append(FILE_SEPARATOR);
	    }
	    
	    return quotPath.toString();
	}

	static public String fileNameToPackageName(String fileName) {
		String removeDot = fileName;
		if (fileName.startsWith(".\\"))
			removeDot = fileName.substring(2); // no idea why 3 does not work
		else if (fileName.startsWith("."))
			removeDot = fileName.substring(1);

		String replaceForwardSlash = removeDot.replace('/', '.');
		String replaceBackwardSlash = replaceForwardSlash.replace("\\", ".");
		String removeBin = replaceBackwardSlash;
		if (removeBin.startsWith("bin."))
			removeBin = replaceBackwardSlash.substring(4);
		else if (removeBin.startsWith("bin"))
			removeBin = replaceBackwardSlash.substring(3);
		return removeBin;
	}
	static public String classNameToPackageName(String aClassName) {
		int lastDotIndex = aClassName.lastIndexOf(".");
		if (lastDotIndex == -1) {
			return "";
//			Tracer.error("Cannot extract package name from class name that does not have a .");
//			return "";
		}
		return aClassName.substring(0, lastDotIndex);
		
	}
	
	public static final String INITIAL_BACK_SLASH = "\\";
	
	public static String projectRelativeNameToClassName(String aProjectRelativeFileName) {
		String retVal = aProjectRelativeFileName.substring(0, aProjectRelativeFileName.length() - ".java".length());
		retVal = retVal.replace('/', '.');
		retVal = retVal.replace("\\", ".");
		return retVal;
		
	}

	static public String toText(File f) {
		if (f == null) {
			// System.out.println();
			return "Source of class not in main directory";
		}
		StringBuffer sb = new StringBuffer();
		try {
			DataInputStream dataIn = new DataInputStream(new FileInputStream(f));
			for (;;) {
				String nextLine = dataIn.readLine();
				if (nextLine == null)
					break;
				// System.out.println("new line" + nextLine);
				// sb.append(nextLine+'\n');
				append(sb, nextLine);
				sb.append("\n");
			}
			dataIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public final static int ESTIMATED_SOURCE_FILE_LENGTH = 1000;

	 public static StringBuffer toText(BufferedReader aBufferedReader) {
		StringBuffer retVal = new StringBuffer(ESTIMATED_SOURCE_FILE_LENGTH);
		String inputLine;
		while (true) {
			try {
				inputLine = aBufferedReader.readLine();
				if (inputLine == null) break;
			} catch (Exception e) {
				break;
			}
			retVal.append(inputLine +"\n");
		}
		try {
			aBufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}
	 
	 public static StringBuffer toText(InputStream anInputStream) {
		 return toText(toBufferedReader(anInputStream));
	 }
	 
	 public static StringBuffer toStringBuffer(File aFile) throws IOException{
		 return toText(toBufferedReader(new FileInputStream(aFile)));
	 }
	 
	 public static BufferedReader toBufferedReader(InputStream anInputStream) {
		 Reader reader = new InputStreamReader(anInputStream);
		 return new BufferedReader(reader);
	 }
	 
	
	 
	 public static BufferedReader toBufferedReader(String aFileName) throws FileNotFoundException {
//		 try {
		 return new BufferedReader(
			        new FileReader(aFileName));
//		 } catch (Exception e) {
////			 e.printStackTrace();
//			 return null;
//		 }
	 }
	 
	 public static StringBuffer toText(String aFileName) {
		 try {
		StringBuffer retVal = new StringBuffer(ESTIMATED_SOURCE_FILE_LENGTH);
		BufferedReader bufferedReader = toBufferedReader(aFileName);
		return toText(bufferedReader);		
		 } catch (Exception e) {
			 return new StringBuffer("no file: " + aFileName);
		 }
	}
	 
	 

	public static boolean isJavaClass(Class c) {
		// if (c == null) return true;
		return isJavaClass(c.getName());
	}
	
	public static String toCanonicalFileName(String aFileName) {
		String retVal = "";
		for (int i = 0; i < aFileName.length(); i++) {
			char currentChar = aFileName.charAt(i);
			if (currentChar == '\\')
				retVal += '/';
			else
				retVal += currentChar;
		}
		return retVal;
//		return aFileName.replaceAll("\\", "\");
		
	}
	
	public static String toWindowsFileName(String aFileName) {
		String retVal = "";
		for (int i = 0; i < aFileName.length(); i++) {
			char currentChar = aFileName.charAt(i);
			if (currentChar == '/')
				retVal += '\\';
			else
				retVal += currentChar;
		}
		return retVal;
//		return aFileName.replaceAll("\\", "\");
		
	}
	
	public static int numOccurences (String aString, char aChar) {
		int count = 0;
	    for (int i=0; i < aString.length(); i++)
	    {
	        if (aString.charAt(i) == aChar)
	        {
	             count++;
	        }
	    }
	    return count;
	}
	public static int numMiddleOccurences (String aString, char aChar) {
		int retVal = numOccurences(aString, aChar);
		if (aString.charAt(aString.length() - 1) == aChar) {
			return retVal - 1;
		}
		return retVal;
	}
	
	public static String[] docSuffixes = {".doc", ".docx", ".pdf", ".ppt", ".pptx", ".txt"};
	public static boolean isDocumentName(String aName) {
		for (String suffix:docSuffixes) {
			if (aName.endsWith(suffix))
				return true;
		}
		return false;
		
	}
	
	public static String getFileSuffix(String aFileName) {
		int index = aFileName.indexOf('.');
		return aFileName.substring(index+1);
	}
	
	public static String toRelativeName (String aParentName, String aChildName) {
		try {
			// how did this ever work?
//		int parentStart = aChildName.indexOf(aChildName);
		int parentStart = aChildName.toLowerCase().indexOf(aParentName.toLowerCase());
		// why is this not always 0
		if (parentStart < 0) {
			Tracer.error(aParentName + " is not in " + aParentName);
			return null;
		}
		String retVal = aChildName.substring(parentStart + aParentName.length() + 1);
		return retVal;	
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}
	
	public static String getParentFileName (String aChildName) {
		if (aChildName == null) return null;
		int parentStart = aChildName.lastIndexOf("/");
		return aChildName.substring(0, parentStart);			
	}
	public static String toFilePrefix(String anInputFile) {
		int dotIndex = anInputFile.indexOf(".");
		return anInputFile.substring(0, dotIndex);
	}
	public static String absoluteNameToLocalName (String anAbsoluteName) {
		int lastSlashIndex = anAbsoluteName.lastIndexOf("/");
		if (lastSlashIndex < 0) {
			lastSlashIndex = anAbsoluteName.lastIndexOf("\\");
			if (lastSlashIndex < 0)
				return anAbsoluteName;
		} //else
		return anAbsoluteName.substring(lastSlashIndex + 1, anAbsoluteName.length());
		
	}
	
	

	public static boolean isJavaClass(String className) {
		return className.startsWith("java.");
	}

	public static String[] filterOutJavaClasses(String[] input) {
		List<String> output = new Vector();
		for (int i = 0; i < input.length; i++) {
			if (input[i].startsWith("java."))
				continue;
			output.add(input[i]);
		}
		String[] template = {};
		return output.toArray(template);

	}

	static Thread classForNameThread;
	static ClassForNameRunnable classForNameRunnable;

	static ClassForNameRunnable getExistingClassForNameRunnable() {
		if (classForNameRunnable == null) {
			classForNameRunnable = new ClassForNameRunnable();
		}
		classForNameRunnable.resetClass();
		return classForNameRunnable;
	}

	static ClassForNameRunnable getNewClassForNameRunnable() {
		// if (classForNameRunnable == null) {
		classForNameRunnable = new ClassForNameRunnable();
		// }
		// classForNameRunnable.resetClass();
		return classForNameRunnable;
	}

	static Thread getNewClassForNameThread() {
		// if (classForNameThread == null) {

		classForNameThread = new Thread(getNewClassForNameRunnable());
		classForNameThread.start();
		// }
		return classForNameThread;
	}

	static Thread getExistingClassForNameThread() {
		if (classForNameThread == null) {

			classForNameThread = new Thread(getExistingClassForNameRunnable());
			classForNameThread.start();
		}
		return classForNameThread;
	}

	static long timeOut = 100;
	static ABoundedBuffer<String> classNamesBuffer = new ABoundedBuffer();
	static ABoundedBuffer<Class> classesBuffer = new ABoundedBuffer();

	/*
	 * public static Class asynchronousClassForName (String name) { Thread
	 * thread = getClassForNameThread(); ClassForNameRunnable runnable =
	 * getClassForNameRunnable(); runnable.setClassName(name); try {
	 * 
	 * thread.start(); thread.join(timeOut); Class classFromName =
	 * runnable.getClassFromName(); if (classFromName == null) {
	 * System.out.println("asynchronousClassForName could not convert " + name +
	 * " to class probably because of infinite loop in initializers of static variables."
	 * ); thread.stop(new Exception()); //thread.interrupt(); return null; }
	 * return classFromName; //return runnable.getClassFromName();
	 * 
	 * } catch (Exception e) {
	 * System.out.println("asynchronousClassForName: could not convert:" + name
	 * +
	 * " to class probably because of infinite loop in initializers of static variables."
	 * ); thread.interrupt(); return null; }
	 * 
	 * 
	 * }
	 */
	public static Class asynchronousClassForName(String name) {
		getExistingClassForNameThread();
		ClassForNameRunnable runnable = getExistingClassForNameRunnable();
		// runnable.setClassName(name);
		try {

			// thread.start();
			// thread.join(timeOut);
			classNamesBuffer.put(name);
//			Class classFromName = (Class) classesBuffer.get(timeOut);
			Class classFromName = (Class) classesBuffer.get();

			// Class classFromName = runnable.getClassFromName();
			if (classFromName == null) {
				System.out
						.println("asynchronousClassForName could not convert "
								+ name
								+ " to class probably because of infinite loop in initializers of static variables.");
				// thread.stop(new Exception());
				// thread.interrupt();
				getNewClassForNameThread();
				return null;
			}
			return classFromName;
			// return runnable.getClassFromName();

		} catch (Exception e) {
			System.out
					.println("asynchronousClassForName: could not convert:"
							+ name
							+ " to class probably because of infinite loop in initializers of static variables.");
			// thread.interrupt();
			return null;
		}

	}

	// -------------------------------
	static Thread newInstanceThread;
	static NewInstanceRunnable newInstanceRunnable;

	static NewInstanceRunnable getExistingNewInstanceRunnable() {
		if (newInstanceRunnable == null) {
			newInstanceRunnable = new NewInstanceRunnable();
		}
		// newInstanceRunnable.resetClass();
		return newInstanceRunnable;
	}

	static NewInstanceRunnable getNewNewInstanceRunnable() {
		// if (newInstanceRunnable == null) {
		newInstanceRunnable = new NewInstanceRunnable();
		// }
		// newInstanceRunnable.resetClass();
		return newInstanceRunnable;
	}

	static Thread getNewNewInstanceThread() {
		// if (newInstanceThread == null) {

		newInstanceThread = new Thread(getNewNewInstanceRunnable());
		newInstanceThread.start();
		// }
		return newInstanceThread;
	}

	static Thread getExistingNewInstanceThread() {
		if (newInstanceThread == null) {

			newInstanceThread = new Thread(getExistingNewInstanceRunnable());
			newInstanceThread.start();
		}
		return newInstanceThread;
	}

	// static long timeOut = 100;
	static ABoundedBuffer<Class> instanceClassesBuffer = new ABoundedBuffer();
	static ABoundedBuffer<Object> instanceBuffer = new ABoundedBuffer();

	public static Object asynchronousNewInstance(Class c) {
		classesVisited.clear();

		return doAsynchronousNewInstance(c);
		/*
		 * getExistingNewInstanceThread(); NewInstanceRunnable runnable =
		 * getExistingNewInstanceRunnable(); //runnable.setClassName(name); try
		 * {
		 * 
		 * //thread.start(); //thread.join(timeOut);
		 * instanceClassesBuffer.put(c); //Object instance =
		 * instanceBuffer.get(timeOut); Object instance = instanceBuffer.get();
		 * if (instance == null) {
		 * System.out.println("asynchronousNewInstance could not instantiate " +
		 * c); //thread.stop(new Exception()); //thread.interrupt();
		 * getNewNewInstanceThread(); return null; } return instance; //return
		 * runnable.getClassFromName();
		 * 
		 * } catch (Exception e) {
		 * System.out.println("asynchronousNewInstance could not instantiate " +
		 * c); //thread.interrupt(); return null; }
		 */

	}

	static Object doAsynchronousNewInstance(Class c) {
		if (classesVisited.contains(c))
			return null;
		classesVisited.add(c);

		getExistingNewInstanceThread();
		NewInstanceRunnable runnable = getExistingNewInstanceRunnable();
		// runnable.setClassName(name);
		try {

			// thread.start();
			// thread.join(timeOut);
			instanceClassesBuffer.put(c);
			// Object instance = instanceBuffer.get(timeOut);
			Object instance = instanceBuffer.get();
			if (instance == null) {
				System.out
						.println("asynchronousNewInstance could not instantiate "
								+ c);
				// thread.stop(new Exception());
				// thread.interrupt();
				getNewNewInstanceThread();
				return null;
			}
			return instance;
			// return runnable.getClassFromName();

		} catch (Exception e) {
			System.out.println("asynchronousNewInstance could not instantiate "
					+ c);
			// thread.interrupt();
			return null;
		}

	}

	static Vector classesVisited = new Vector();

	public static Constructor parameterLessConstructor(Class theClass) {
		Constructor[] constructors = theClass.getConstructors();
		for (int i = 0; i < constructors.length; i++) {
			if (constructors[i].getParameterTypes().length == 0)
				return constructors[i];
		}
		// return constructors[0];
		return null;
	}

	static Hashtable<Class, Object> classToDefaultInstance = new Hashtable();
	static Hashtable<Integer, Object> classIDToDefaultInstance = new Hashtable();
	static boolean classToDefaultInstancesInitialized = false;

	public static Object defaultValue(Class c) {
		initClassToDefaultInstances();
		return classToDefaultInstance.get(c);
	}

	public static void setDefaultValue(Class cl, Object newVal) {

		classToDefaultInstance.put(cl, newVal);
	}

	public static boolean isParsable(Class c) {
		return c.isPrimitive()
				|| (Number.class.isAssignableFrom(c) && !Modifier.isAbstract(c
						.getModifiers())) || c.equals(String.class)
				|| c.equals(Boolean.class);
	}

	static void initClassToDefaultInstances() {
		if (classToDefaultInstancesInitialized)
			return;
		classToDefaultInstancesInitialized = true;
		classToDefaultInstance.put(Double.class, 0.0);
		classToDefaultInstance.put(Double.TYPE, 0.0);
		classToDefaultInstance.put(Float.class, 0.0);
		classToDefaultInstance.put(Float.TYPE, 0.0);
		classToDefaultInstance.put(Integer.class, 0);
		classToDefaultInstance.put(Integer.TYPE, 0);
		classToDefaultInstance.put(Short.class, 0);
		classToDefaultInstance.put(Short.TYPE, 0);
		classToDefaultInstance.put(Long.class, 0);
		classToDefaultInstance.put(Long.TYPE, 0);
		classToDefaultInstance.put(Character.class, '.');
		classToDefaultInstance.put(Character.TYPE, '.');
		classToDefaultInstance.put(Boolean.class, true);
		classToDefaultInstance.put(Boolean.TYPE, true);
		classToDefaultInstance.put(String.class, "");

	}

	public static String packageName(String longName) {
		int index = longName.lastIndexOf('.');
		if (index <= 0)
			return null;
		return longName.substring(0, index);

	}
	
	

	public static String shortClassName(String longName) {
		int index = longName.lastIndexOf('.');
		if (index <= 0)
			return longName;
		return longName.substring(index + 1);
	}
	
	public static String shortFileName(String longName) {
		int index = longName.lastIndexOf('/');
		if (index <= 0)
			return longName;
		return longName.substring(index + 1);
	}

	static Object instantiateClassOfInterface(String packageName,
			String interfaceName, String prefix) {

		String className = prefix + interfaceName;
		String fullClassName = className;
		if (packageName != null)
			fullClassName = packageName + '.' + className;
		try {
			Class c = Class.forName(fullClassName);
			return Common.newInstanceWithDefaultParameters(c);
			// return doAsynchronousNewInstance(c);
		} catch (Exception e) {
			return null;
		}
	}

	static Object instantiateClassOfInterface(Class c) {
		String interfaceLongName = c.getName();
		String packageName = packageName(interfaceLongName);
		String interfaceName = c.getSimpleName();
		Object newInstance = instantiateClassOfInterface(packageName,
				interfaceName, "A");
		if (newInstance != null)
			return newInstance;
		newInstance = instantiateClassOfInterface(packageName, interfaceName,
				"An");
		if (newInstance != null)
			return newInstance;
		newInstance = instantiateClassOfInterface(packageName, interfaceName,
				"Another");
		if (newInstance != null)
			return newInstance;
		return null;

	}

	static Object instantiateEnum(Class c) {
		Object[] constants = c.getEnumConstants();
		if (constants.length > 0)
			return constants[0];
		return null;
	}

	public static Object newInstanceWithDefaultParameters(
			Constructor theConstructor) {
		initClassToDefaultInstances();
		Class[] parameterTypes = theConstructor.getParameterTypes();
		Object[] parameterValues = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			/*
			 * if (parameterTypes[i].isInterface()) parameterValues[i] =
			 * instantiateClassOfInterface(parameterTypes[i]); else {
			 */

			parameterValues[i] = classToDefaultInstance.get(parameterTypes[i]);
			if (parameterValues[i] == null)
				// parameterValues[i] =
				// doAsynchronousNewInstance(parameterTypes[i]);
				parameterValues[i] = newInstanceWithDefaultParameters(parameterTypes[i]);
			// }
		}

		try {
			return theConstructor.newInstance(parameterValues);

		} catch (Exception e) {
			return null;
		}
	}

	public static Object newInstanceWithPotentialParameters(
			Constructor theConstructor, Object[] parameterValues) {
		// initClassToDefaultInsatnces();
		Class[] parameterTypes = theConstructor.getParameterTypes();
		if (parameterTypes.length != parameterValues.length)
			return null;
		// Object[] parameterValues = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {

			if (!parameterTypes[i].isPrimitive()
					&& !parameterTypes[i].isAssignableFrom(parameterValues[i]
							.getClass()))
				return null;

		}

		try {
			return theConstructor.newInstance(parameterValues);

		} catch (Exception e) {
			return null;
		}
	}

	public static Object newInstanceWithDefaultParameters(Class theClass) {
		if (theClass.isInterface())
			return instantiateClassOfInterface(theClass);
		else if (theClass.isEnum())
			return instantiateEnum(theClass);
		Object newInstance = null;
		try {
			newInstance = theClass.newInstance();
			return newInstance;
		} catch (Exception e) {
			newInstance = null;
		}
		if (newInstance == null) {
			Constructor[] constructors = theClass.getConstructors();
			for (int i = 0; i < constructors.length; i++) {
				Object retVal = newInstanceWithDefaultParameters(constructors[i]);
				if (retVal != null)
					return retVal;
			}

		}
		return null;
	}

	public static Object newInstanceWithParameters(Class theClass,
			Object[] params) {

		Constructor[] constructors = theClass.getConstructors();
		for (int i = 0; i < constructors.length; i++) {
			Object retVal = newInstanceWithPotentialParameters(constructors[i],
					params);
			if (retVal != null)
				return retVal;
		}

		return null;
	}
	public static String getCurrentDirectory() {
		return  System.getProperty("user.dir");
	}
	// changing user.dir
	 public static boolean setCurrentDirectory(String directory_name)
	    {
	        boolean result = false;  // Boolean indicating whether directory was set
	        File    directory;       // Desired current working directory

	        directory = new File(directory_name).getAbsoluteFile();
	        if (directory.exists() || directory.mkdirs())
	        {
	            result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
	        }

	        return result;
	    }
	public static Image toImage (String anImageFileName) {
		try {
		File anImageFile = new File(anImageFileName).getAbsoluteFile(); // respecting user.dir
		return ImageIO.read(anImageFile);
		} catch (Exception e) {
			System.out.println("Could not convert file " + anImageFileName + " to image because of:" + e);
			return null;
		}		
	}

	
	public static Image toImage (String imageFile, Object aCaller) {
		try {
		File file = new File(imageFile);
		
//		ImageIcon icon =new ImageIcon(imageFile);
//		Image image = icon.getImage();	
//		int height = image.getHeight(null);
//		int width = image.getWidth(null);

		
		Image image = Toolkit.getDefaultToolkit().getImage(imageFile);
		if (file.exists())
			return image;
//		int height = image.getHeight(null);
//		int width = image.getWidth(null);
//		if (image != null  && (width > 0 || height > 0)) // if the file does not exist both return -1
//			return image;
		ClassLoader classLoader = aCaller.getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(imageFile);
		return ImageIO.read(inputStream);
		} catch (Exception e) {
			return null;
		}

	}
	
//	public static File toFile (String fileName, Object aCaller) {
//		File file = new File(fileName);
//		if (file.exists())
//			return file;
//		ClassLoader classLoader = aCaller.getClass().getClassLoader();
//		InputStream inputStream = classLoader.getResourceAsStream(fileName);
//		
//		
//		
//	}
	
	public static Date toDate (String aSakaiTimestamp ) {
		try {
		       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		       dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		       Date date = dateFormat.parse(aSakaiTimestamp);
		       return date;
		       
			} catch (Exception e) {
//				e.printStackTrace();

				return null;
			}
		
	}
	
	
	public static ImageIcon toImageIcon(String imageFile, Object aCaller) {
		if (imageFile == null)
			return null;
		try {
		ImageIcon icon = new ImageIcon(imageFile);
		if (icon.getIconHeight() < 0 || icon.getIconWidth() < 0) {
			ClassLoader classLoader = aCaller.getClass().getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(imageFile);
			byte imageBytes[] = new byte[inputStream.available()];
    	    inputStream.read(imageBytes);
    	    icon = new ImageIcon(imageBytes);
		}
	    return icon;

		} catch (Exception e) {
			return null;
		}
		
	}

	public static String beautify(String name) {
		// System.out.println("beautify" + name);
		if (name == null || name.length() == 0)
			return name;
		int start = 0;
		/*
		 * if (name.charAt(0) == '.') start =1; else start = 0;
		 */

		String label = "" + Character.toUpperCase(name.charAt(start));
		char c;
		for (int nameIndex = start + 1; nameIndex < name.length(); nameIndex++) {
			if (

			(Character.isUpperCase(c = name.charAt(nameIndex)) &&

			((nameIndex + 1) < name.length()
					&& !Character.isUpperCase(name.charAt(nameIndex + 1)) || ( // a
																				// single
																				// upper
																				// case
																				// letter
																				// is
																				// not
																				// an
																				// acronym
					(nameIndex + 2) < name.length() && !Character
							.isUpperCase(name.charAt(nameIndex + 1)))
					&& (nameIndex == 1) || !Character.isUpperCase(name
					.charAt(nameIndex - 1))))

					// (Character.isUpperCase( c = name.charAt(nameIndex))
					// // && notUpperCaseLetter(name.charAt(nameIndex - 1))
					// )
					|| (Character.isDigit(c) && !Character.isDigit(name
							.charAt(nameIndex - 1))))
				label = label + " ";
			label = label + c;
		}
		// System.out.println("beautify return" + label);
		return label;
	}

	public static String beautify(String name, String displayName) {
		if (name.equals(displayName))
			return beautify(name);
		else
			return displayName;
	}

	public static boolean notUpperCaseLetter(char c) {
		return Character.isLetter(c) && !Character.isUpperCase(c);
	}

	/**
	 * Returns a copy of the object, or null (not true, returns the same object) if the object cannot be serialized.
	 */

	public static Object deepCopy(Object orig) {		
		Object obj = orig;
		try {
//			Class origClass = orig.getClass();
//			ClassLoader objectClassLoader = origClass.getClassLoader();
//			ClassLoader myClassLoader = Thread.currentThread().getContextClassLoader();
//			Thread.currentThread().setContextClassLoader(objectClassLoader);

			
			// Write the object out to a byte array
			FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(fbos);
			out.writeObject(orig);
//			Thread.currentThread().setContextClassLoader(myClassLoader);

			out.flush();
			out.close();

			// Retrieve an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());
			obj = in.readObject();
		} catch (InvalidClassException ice) {
			
		} catch (NotSerializableException  nse) {
//			System.out.println(nse);
//			nse.printStackTrace();

		} catch (IOException e) {
			System.out.println(e + ": " + orig);
//			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
//			cnfe.printStackTrace();
		}
		return obj;
	}

	// /**
	// * Returns a copy of the object, or the same object if the object cannot
	// * be serialized.
	// */
	// public static Object deepCopy(Object orig) {
	//
	// Object obj = orig;
	// try {
	// // Write the object out to a byte array
	// FastByteArrayOutputStream fbos =
	// new FastByteArrayOutputStream();
	// ObjectOutputStream out = new ObjectOutputStream(fbos);
	// out.writeObject(orig);
	// out.flush();
	// out.close();
	//
	// // Retrieve an input stream from the byte array and read
	// // a copy of the object back in.
	// ObjectInputStream in =
	// new ObjectInputStream(fbos.getInputStream());
	// obj = in.readObject();
	// }
	// catch (NotSerializableException nse) {
	// nse.printStackTrace();
	// }
	// catch(IOException e) {
	// //Message.warning("Class " + obj.getClass() +
	// " not serializable. Implciit refresh may not work." );
	// e.printStackTrace();
	// }
	// catch(ClassNotFoundException cnfe) {
	// cnfe.printStackTrace();
	// }
	// return obj;
	// }
	static Registry myRegistry;

	public static Registry getRegistry(String host) {
		return getRegistry(host, DEFAULT_RMI_PORT);
	}

	public static Registry getRegistry(String host, int port) {
		if (myRegistry != null)
			return myRegistry;
		Registry registry = null;
		try {

			registry = LocateRegistry.getRegistry(host, port);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		myRegistry = registry;
		return registry;

	}

	public static int DEFAULT_RMI_PORT = 1099;

	public static Registry getOrCreateRegistry() {
		return getOrCreateRegistry(getHostName(), DEFAULT_RMI_PORT);
	}

	public static Registry getOrCreateRegistry(String host, int port) {
		if (myRegistry != null)
			return myRegistry;
		Registry registry = null;
		try {

			registry = LocateRegistry.getRegistry(host, port);
			try {
				registry.lookup("Dummy");
			} catch (NotBoundException e) {
			} catch (Exception e) {
				// NotBoundException indicates that the registry exists, so we
				// should
				// not attempt to recreate it
				System.err.println("SessionRegistry: STARTING ...");
				registry = LocateRegistry.createRegistry(port);
			}
		} catch (Exception e) {
			System.err.println("SessionRegistry::startRegistry Error: "
					+ e.getMessage());
			e.printStackTrace();
		}
		myRegistry = registry;
		return registry;

	}

	public static String getHostName() {
		try {
			return java.net.InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			return null;
		}
	}

	public static void sleep(long interval) {
		try {
			Thread.sleep(interval);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int indexOfReference(List aList, Object anElement) {
		for (int i = 0; i < aList.size(); i++) {
			if (aList.get(i) == anElement)
				return i;
		}
		return -1;
	}

	public static int random(int min, int rangeSize) {

		double random = Math.random();
		long scaledRandom = Math.round((rangeSize -1) * random);
		return min + (int) scaledRandom;
	}

	public static String toString(ByteBuffer message) {
		byte[] msgBytes = new byte[message.remaining()];
		int oldPosition = message.position();
		message.get(msgBytes); // will modify position
		message.position(oldPosition); // restore it to make it side effect free
		return new String(msgBytes);
	}

	public static Font toFontSize(Font oldFont, Integer size) {	
		if (oldFont == null)
			oldFont = Common.getDefaultFont();
		if (size == null || size == 0)
			return oldFont;
		return getFont(oldFont, null, null, size);
		
	}

	public static Font getFont(Font oldFont, String name, Integer style, Integer size) {			
			
			if (oldFont == null) return null;
			int newStyle;
			if (style == null)
				newStyle = oldFont.getStyle();
			else 
				newStyle = style;
			int newSize;
			if (size == null)
				newSize = oldFont.getSize();
			else
				newSize = size;
			String newName;
			if (name == null)
				newName = oldFont.getName();
			else
				newName = name;
			return new Font(newName, newStyle, newSize);
	}

	public static StringBuilder readFile(File file) throws IOException {
		StringBuilder text = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new FileInputStream(file));
	
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				text.append(line + Common.NL);
			}
		} finally {
			return text;
		}
	}

	public static String NL = System.getProperty("line.separator");

	// public static void writeFile(String filename, String text) throws
	// IOException {
	// FileOutputStream fos = null;
	// try {
	// fos = new FileOutputStream(filename);
	// fos.write(text.getBytes());
	// } catch (IOException e) {
	// close(fos);
	// throw e;
	// }
	// close(fos);
	// }
	
	// multiple console threads could be appending at the same time
	public synchronized static void appendText(String aFileName, String text) throws IOException {
		File file = new File(aFileName);
		if (file.exists())
			appendText(file, text);
		else
			writeText(file, text);
		
	}
	static Date date = new Date();
	public static String currentTimeAsDate() {
		date.setTime(System.currentTimeMillis());
		return date.toString();
		
	}
	public static void appendText(File file, String text) throws IOException {
		StringBuffer currentText = toStringBuffer(file);
		currentText.append( text);
		writeText(file, currentText.toString());
		
		
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(file);
//			fos.write(text.getBytes());
//			
//		} catch (IOException e) {
//			System.out.println(file.getName() + " " + e.getMessage());
//			Common.close(fos);
//			throw e;
//		}
//		Common.close(fos);
	}
	public static void clearOrCreateFile (String aFileName) throws IOException {
		File aFile = new File (aFileName);
//		try {
		if (!aFile.exists()) {
				File aFolder = aFile.getParentFile();
				if (!aFolder.exists()) 
					aFolder.mkdirs();
				aFile.createNewFile();
			}
		
		writeText(aFile, "" );
//	} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
	public static void writeText(File file, String text) throws IOException {
		if (!file.exists()) {
			File aFolder = file.getParentFile();
			if (!aFolder.exists()) 
				aFolder.mkdirs();
			file.createNewFile();
		}
//		if (!file.exists())
//			file.mkdirs();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(text.getBytes());
			fos.flush();
			
		} catch (IOException e) {
			System.out.println(file.getName() + " " + e.getMessage());
			Common.close(fos);
			throw e;
		}
		Common.close(fos);
	}
	
	public static void writeText(String aFileName, String text) throws IOException {
//		 writeText (new File(aFileName), text);
		 try(  PrintWriter out = new PrintWriter( aFileName )  ){
			    out.println( text );
			}
	}

	public static void close(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException ignored) {
		}
	}

	public static boolean isNameChar(char ch) {
		return Character.isLetter(ch) || ch == '.' || ch == '_' || Character.isDigit(ch);
	}

	public static String getNameFollowingKeyword(StringBuilder contents,
			String keyWordName) {
		int keywordStartIndex = contents.indexOf(keyWordName);
		if (keywordStartIndex == -1)
			return null;
		int keywordEndIndex = keywordStartIndex + keyWordName.length();
		int nameStartIndex = keywordEndIndex;
		while (nameStartIndex < contents.length()) {
			char ch = contents.charAt(nameStartIndex);
			if (isNameChar(ch))
				break;
			nameStartIndex++;
	
		}
		int nameEndIndex = nameStartIndex + 1;
		while (nameEndIndex < contents.length()) {
			char ch = contents.charAt(nameEndIndex);
			if (isNameChar(ch))
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

	public static String[] getLeftAndRightInColonSeparatedString(String aString) {
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
	
	public static void exec (String aCommand) {
		try {
			Process proc = Runtime.getRuntime().exec(aCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void exec (String[] aCommand) {
		try {
			Process proc = Runtime.getRuntime().exec(aCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	 static String htmlLinkRegex = "((https?|http|ftp|file)\\://[:/?#\\[\\]@!%$&'()*+,;=a-zA-Z0-9._\\-~]+)";
	 static Pattern htmlLinkPattern = Pattern.compile(htmlLinkRegex);
	 
	 public static String[] toMatches(Matcher aMatcher) {
		 String retVal[] = new String[aMatcher.groupCount()];
		 for (int i = 0; i < retVal.length; i++) {
			 retVal[i] = aMatcher.group(i);
		 }
		 return retVal;
	 }
//	 public static String[] extractHTMLLinks(String aString) {
//		 Matcher matcher = htmlLinkPattern.matcher(aString);
//		 if (matcher.find())
//			{
//			    System.out.println(matcher.group());
//
//			    System.out.println(matcher.group(1));
//			}
//		 return toMatches(matcher);
//		
//	 }
	 
	 public static String extractHTMLLink(String aString) {
		 Matcher matcher = htmlLinkPattern.matcher(aString);
		 if (matcher.find())
			{
			    return matcher.group();

			}
		 return null;
		
	 }
	 
	 
	 
	 public static URI[] toURIs(String[] someLinks) {
		 URI[] retVal = new URI[someLinks.length];
		for (int index = 0; index < someLinks.length; index++) {
			try {
				retVal[index] = new URI(someLinks[index]);
			} catch (URISyntaxException e) {
				e.printStackTrace();			
			}			
		}
		return retVal;		
	 }
	 
	 public static URI toURI(String aString) {
		
			try {
				return new URI(aString);
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return null;
			}			
		
	 }
	 
//	 public static URI[] extractURIs(String aString) {
//		 return toURIs(extractHTMLLinks(aString));
//	 }
	 
	 public static URI extractURI(String aString) {
		 String aLink = extractHTMLLink(aString);
		 if (aLink == null)
			 return null;
		 return toURI(aLink);
	 }
	 
	 public static final String WEB_ADDRESS_START = "www.";
	 public static String toWebAddress (String aPossiblyShortWebAddress) {
		 String retVal = aPossiblyShortWebAddress;
		 if (!(aPossiblyShortWebAddress.startsWith(WEB_ADDRESS_START)))
			 retVal = WEB_ADDRESS_START + retVal;
		 return retVal;
	 }
	 
	 
	 public static final String A_HREF_START = "<a href=\"";
	 public static final String HREF_CLOSED = "\">";
	 public static final String HREF_END = "</a>";	

	 public static String toAHREF (String aPossiblyShortWebAddress, String aLabel) {
		 return A_HREF_START + toWebAddress(aPossiblyShortWebAddress) + HREF_CLOSED + aLabel + HREF_END;		 
	 }
	 
	 public static String toBlueUnderlinedAHRef (String aPossiblyShortWebAddress, String aLabel) {
		 return toBlue(toUnderlined(toAHREF(aPossiblyShortWebAddress, aLabel)));		 
	 }
	 
	 	 

	 
	 public static final String UNDERLINE_START = "<U>";
	 public static final String UNDERLINE_END = "</U>";

	 
	 public static final String toUnderlined(String aString) {
		 return UNDERLINE_START + aString + UNDERLINE_END;
		 
	 }
	 public static final String BLUE_FONT = "<FONT color=\"#000099\">";
	 public static final String toBlue(String aString) {
		 return BLUE_FONT + aString;
		 
	 }
	 public static final String HTML_START = "<html>";
	 public static final String HTML_END = "</html>";
	 public static String toHTML (String aString) {
		return HTML_START + aString + HTML_END;	 
	 }
	 
	 public static String toBlueColoredUnderlinedHrefHTML (String aReference, String aLabel) {
		return toHTML(toBlueUnderlinedAHRef(aReference, aLabel));
		 }
	 
	 public static void browse(URI aURI )  {
		 if (!Desktop.isDesktopSupported()) {
			 Tracer.error("No desktop to browse:" + aURI);
		        return ;
		    }
		 Desktop desktop = java.awt.Desktop.getDesktop();
		 if (!desktop.isSupported(Desktop.Action.BROWSE)) {
			 Tracer.error("No desktop browser for:" + aURI);
			 return;
		 }
		 try {
			desktop.browse(aURI);
		} catch (IOException e) {
			 Tracer.error("Could not browse:" + aURI);
			e.printStackTrace();
		}
			 
	 }
	 
	 public static boolean isBrowsingSupported() {
		    if (!Desktop.isDesktopSupported()) {
		        return false;
		    }
		    boolean result = false;
		    Desktop desktop = java.awt.Desktop.getDesktop();
		    if (desktop.isSupported(Desktop.Action.BROWSE)) {
		        result = true;
		    }
		    return result;

		}
		
	
	 public static boolean equal (Object first, Object second) {
			if (first == null && second == null)
				return true;
			if (first == null || second == null) 
				return false;
			return first.equals(second);
		}
	 
		public static final JLabel jLabel = new JLabel("");

		public static Font getDefaultFont() {
			return jLabel.getFont();
		}
		
		
		public static int getFontStringWidth(Font aFont, String aString, Integer aFontSize) {
//			if (aFont == null) {
//				aFont = Common.getDefaultFont();
//			}
			aFont = toFontSize(aFont, aFontSize);
	 
		FontMetrics fontMetrics = (FontMetrics) jLabel.getFontMetrics(aFont);
		return fontMetrics.stringWidth(aString);
	
		}
		
		public static int getFontStringWidth(Graphics g, String aString) {
			return getFontStringWidth(g.getFont(), aString, null);			 
		
			}
			
		public static int getDefaultFontHeight() {
			return getFontHeight(getDefaultFont(), null);
		}
		
		public static int getDefaultFontStringWidth(String aString) {
			return getFontStringWidth(getDefaultFont(), aString, null);
		}
		
		public static int getFontHeight(Font aFont, Integer aFontSize) {
//			if (aFont == null)
				aFont = Common.toFontSize(aFont, aFontSize);
			 
			FontMetrics fontMetrics = (FontMetrics) jLabel.getFontMetrics(aFont);
			return fontMetrics.getHeight();
		
		}
		
		public static Integer intSuffix(String aString) {
			int startSuffix = aString.length() - 1;
			for (int i = aString.length() - 1; i >= 0; i--) {
				if (Character.isDigit(aString.charAt(i))) {
					continue;
//					start = i;
				} else {
					startSuffix = i + 1;
					break;
				}
			}
			String digitString = aString.substring(startSuffix);
			if (digitString.length() > 0) {
				return Integer.parseInt(digitString);
			}
			return null;
		}

		public static Object fromString (Class aClass, String aString) {
			String aStringLC = aString.replaceAll("\\s+","").toLowerCase();
			if (!aClass.isEnum()) return null;
			Object[] enumConstants = aClass.getEnumConstants();
			for (Object constant:enumConstants) {
				if (constant.toString().replaceAll("\\s+","").toLowerCase().equals(aStringLC))
					return constant;
			}
			return null;
		}
}
