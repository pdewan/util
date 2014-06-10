package util.trace;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import util.annotations.Keywords;

public class Tracer {

	public static String ALL_KEYWORDS = "All Key Words";
	static Map<String, Boolean> keyWordToPrintStatus = new HashMap();
	static Map<String, Boolean> keyWordToDisplayStatus = new HashMap();
	static Map<String, Boolean> keyWordToWaitStatus = new HashMap(); //wait status is stronger as it implicit displaying


	static TracingLevel tracingLevel = TracingLevel.ERROR;

	static boolean showWarnings = true;
	static boolean showInfo = false;

	static MessagePrefixKind messagePrefixKind = MessagePrefixKind.SHORT_CLASS_NAME;
	static ImplicitKeywordKind implicitPrintKeywordKind = ImplicitKeywordKind.OBJECT_PACKAGE_NAME;
	static ImplicitKeywordKind implicitDisplayKeywordKind = ImplicitKeywordKind.OBJECT_PACKAGE_NAME;

//	static ImplicitKeywordKind implicitDisplayKeywordKind = ImplicitKeywordKind.OBJECT_CLASS_NAME;
//	static ImplicitKeywordKind implicitWaitKeywordKind = ImplicitKeywordKind.OBJECT_CLASS_NAME;
	static ImplicitKeywordKind implicitWaitKeywordKind = ImplicitKeywordKind.OBJECT_PACKAGE_NAME;



	public static boolean isInfo(String aText) {
		return aText.contains(INFO_PREFIX);
		
	}
	public static void showWarnings(boolean newValue) {
		showWarnings = newValue;
	}

	public static void showInfo(boolean newValue) {
		if (showInfo == newValue) return;
		System.out.println("Tracer: showInfo = " + newValue);
		showInfo = newValue;
	}

	public static void setMessagePrefixKind(MessagePrefixKind newValue) {
		messagePrefixKind = newValue;
	}

	public static void setImplicitPrintKeywordKind(ImplicitKeywordKind newValue) {
		implicitPrintKeywordKind = newValue;
	}
	
	public static void setImplicitDisplayKeywordKind(ImplicitKeywordKind newValue) {
		implicitDisplayKeywordKind = newValue;
	}
	
	public static void setImplicitWaitKeywordKind(ImplicitKeywordKind newValue) {
		implicitWaitKeywordKind = newValue;
	}

	public static void fatalError(String error) {
		String msg = "Fatal Error***" + error + ". Terminating program";
		System.out.println(msg);
		JOptionPane.showMessageDialog(null, msg);
		System.exit(1);
	}

	public static void error(String error) {
		if (tracingLevel.ordinal() >= TracingLevel.ERROR.ordinal()) {
			System.out.println("E***" + error);
		}
	}

	public static void warning(String warning) {
		if (showWarnings) {
			System.out.println("W***" + warning);
		} else if (tracingLevel.ordinal() >= TracingLevel.WARNING.ordinal()) {
			System.out.println("W***" + warning);
		}
	}
	public static final String INFO_PREFIX = "I***";
	public static void info(String info) {
		if (showInfo) {
			System.out.println(INFO_PREFIX + info);
		} else if (tracingLevel.ordinal() >= TracingLevel.INFO.ordinal()) {
			System.out.println(INFO_PREFIX + info);
		}
	}

	public static void info(String keyWord, String info) {
		if (getKeywordPrintStatus(keyWord)) {
			infoWithPrefix(keyWord, info);
		}
	}
	
	public static final String EVENT_SOURCE = "EvtSrc";
	public static final String EVENT_TYPE = "EvtType";


	public static void infoWithPrefix(String prefix, String info) {
		String qualifier = prefix.contains("trace")?EVENT_TYPE:EVENT_SOURCE;
		info(qualifier + "(" + prefix + ") " + info);
	}

	public static void info(Object object, String keyWord, String info) {
		if (!getKeywordPrintStatus(keyWord))
			return;
		switch (messagePrefixKind) {
		case SHORT_CLASS_NAME:
			infoWithPrefix(object.getClass().getSimpleName(), info);
			break;

		case FULL_CLASS_NAME:
			infoWithPrefix(object.getClass().getName(), info);
			break;

		case PACKAGE_NAME:
			infoWithPrefix(keyWord, info);
			break;
		case OBJECT_TO_STRING:
			infoWithPrefix(object.toString(), info);
			break;

		case NONE:
			info(info);

		}

	}
	
	public static void info(Class object, String keyWord, String info) {
		if (!getKeywordPrintStatus(keyWord))
			return;
		switch (messagePrefixKind) {
		case SHORT_CLASS_NAME:
			infoWithPrefix(object.getSimpleName(), info);
			break;

		case FULL_CLASS_NAME:
			infoWithPrefix(object.getName(), info);
			break;

		case PACKAGE_NAME:
			infoWithPrefix(keyWord, info);
			break;
		case OBJECT_TO_STRING:
			infoWithPrefix(object.toString(), info);
			break;

		case NONE:
			info(info);

		}

	}
	// duplciating code above, but the two kinds of info calls create an issue
	public static String infoPrintBody(Class object) {
		
		switch (messagePrefixKind) {
		case SHORT_CLASS_NAME:
			return object.getSimpleName();

		case FULL_CLASS_NAME:
			return object.getName();

		case PACKAGE_NAME:
			return object.getPackage().getName();
		case OBJECT_TO_STRING:
			return object.toString();

		case NONE:
			return "";
			default:
				return "";

		}

	}
	public static void info(Class caller, String info) {

		info(caller, getImplicitPrintKeyword(caller), info);
	}

	public static void info(Object caller, String info) {

		info(caller, getImplicitPrintKeyword(caller), info);
	}

	public static String getImplicitPrintKeyword(Object caller) {
		return getImplicitKeyword(caller, implicitPrintKeywordKind);
//		switch (implicitPrintKeywordKind) {
//		case OBJECT_CLASS_NAME:
//			return caller.getClass().getName();
//		case OBJECT_PACKAGE_NAME:
//			return caller.getClass().getPackage().getName();
//		case OBJECT_TO_STRING:
//			return caller.toString();
//		}
//		return "";
	}
	
	public static String getImplicitKeyword(Object caller, ImplicitKeywordKind anImplicitKeywordKind) {
		if (caller == null) return "null";
		switch (anImplicitKeywordKind) {
		case OBJECT_CLASS_NAME:
			return caller.getClass().getName();
		case OBJECT_PACKAGE_NAME:
			return caller.getClass().getPackage().getName();
		case OBJECT_TO_STRING:
			return caller.toString();
		}
		return "";
	}
	
	public static String getImplicitDisplayKeyword(Object caller) {
		return getImplicitKeyword(caller, implicitDisplayKeywordKind);

	}
	
	public static String getImplicitWaitKeyword(Object caller) {
		return getImplicitKeyword(caller, implicitWaitKeywordKind);

	}

	public static void setKeywordPrintStatus(Object object, Boolean status) {
		String keyword = getImplicitPrintKeyword(object);
//		System.out.println("Tracer: " + keyword + ":" + status);
		setKeywordPrintStatus(getImplicitPrintKeyword(object), status);
	}
	
	public static void setKeywordDisplayStatus(Object object, Boolean status) {
		String keyword = getImplicitPrintKeyword(object);
		setKeyWordDisplayStatus(getImplicitDisplayKeyword(object), status);
	}
	
	public static void setKeywordWaitStatus(Object object, Boolean status) {
		String keyword = getImplicitWaitKeyword(object);
//		System.out.println("Tracer: " + keyword + ":" + status);
		setKeyWordWaitStatus(getImplicitPrintKeyword(object), status);
	}

	public static String getImplicitPrintKeyword(Class c) {
		return getImplicitKeyword (c, implicitPrintKeywordKind);
//		switch (implicitPrintKeywordKind) {
//		case OBJECT_CLASS_NAME:
//			return c.getName();
//		case OBJECT_PACKAGE_NAME:
//			return c.getPackage().getName();
//		case OBJECT_TO_STRING:
//			error("Cannot get implicit keyword for class as implicit keyword is OBJECT_TO_STRING");
//			return "";
//		}
//		return "";
	}
	
	public static String getImplicitDisplayKeyword(Class c) {
		return getImplicitKeyword (c, implicitDisplayKeywordKind);
//		switch (implicitPrintKeywordKind) {
//		case OBJECT_CLASS_NAME:
//			return c.getName();
//		case OBJECT_PACKAGE_NAME:
//			return c.getPackage().getName();
//		case OBJECT_TO_STRING:
//			error("Cannot get implicit keyword for class as implicit keyword is OBJECT_TO_STRING");
//			return "";
//		}
//		return "";
	}
	
	public static String getImplicitWaitKeyword(Class c) {
		return getImplicitKeyword (c, implicitWaitKeywordKind);
//		switch (implicitPrintKeywordKind) {
//		case OBJECT_CLASS_NAME:
//			return c.getName();
//		case OBJECT_PACKAGE_NAME:
//			return c.getPackage().getName();
//		case OBJECT_TO_STRING:
//			error("Cannot get implicit keyword for class as implicit keyword is OBJECT_TO_STRING");
//			return "";
//		}
//		return "";
	}
	
	public static String getImplicitKeyword(Class c, ImplicitKeywordKind anImplicitKeywordKind) {
		switch (anImplicitKeywordKind) {
		case OBJECT_CLASS_NAME:
			return c.getName();
		case OBJECT_PACKAGE_NAME:
			return c.getPackage().getName();
		case OBJECT_TO_STRING:
			error("Cannot get implicit keyword for class as implicit keyword is OBJECT_TO_STRING");
			return "";
		}
		return "";
	}

	public static void setKeywordPrintStatus(Class c, Boolean status) {
		setKeywordPrintStatus(getImplicitPrintKeyword(c), status);
//		keyWordToPrintStatus.put(getImplicitPrintKeyword(c), status);
	}
	
	public static void setKeywordDisplayStatus(Class c, Boolean status) {
		
		setKeyWordDisplayStatus(getImplicitDisplayKeyword(c), status);

//		keyWordToDisplayStatus.put(getImplicitDisplayKeyword(c), status);
	}
	
	public static void setKeywordWaitStatus(Class c, Boolean status) {
		setKeyWordWaitStatus(getImplicitWaitKeyword(c), status);

//		keyWordToWaitStatus.put(getImplicitWaitKeyword(c), status);
	}

	public static void debug(String debugMessage) {
		if (tracingLevel.ordinal() >= TracingLevel.DEBUG.ordinal()) {
			System.out.println("D***" + debugMessage);
		}
	}

	public static void userMessage(String userMessage) {
//		if (tracingLevel.ordinal() >= TracingLevel.USER_MESSAGE.ordinal()) {
			System.out.println("U***" + userMessage);
//		}
	}

	public static TracingLevel getTracingLevel() {
		return tracingLevel;
	}

	public static void setTracingLevel(TracingLevel aTracingLevel) {
		tracingLevel = aTracingLevel;
	}

	public static void setKeywordPrintStatus(String keyWord, Boolean status) {
		keyWordToPrintStatus.put(keyWord, status);
//		System.out.println("Tracer (Print): " + keyWord + ":" + status);

	}
	
	public static void setKeyWordDisplayStatus(String keyWord, Boolean status) {
		keyWordToDisplayStatus.put(keyWord, status);
//		System.out.println("Tracer (Display): " + keyWord + ":" + status);

	}
	
	public static void setKeyWordWaitStatus(String keyWord, Boolean status) {
		keyWordToWaitStatus.put(keyWord, status);
//		System.out.println("Tracer (Wait): " + keyWord + ":" + status);

	}
	
	public static Boolean getKeywordDisplayStatus(Object anObject) {
		String keyword = getImplicitDisplayKeyword(anObject);
		return getKeywordDisplayStatus(keyword);
	}
	
	public static Boolean getKeywordDisplayStatus(Class aClass) {
		String keyword = getImplicitDisplayKeyword(aClass);
		return getKeywordDisplayStatus(keyword);
	}
	
	public static Boolean getKeywordWaitStatus(Object anObject) {
		String keyword = getImplicitWaitKeyword(anObject);
		return getKeywordWaitStatus(keyword);
	}
	
	public static Boolean getKeywordWaitStatus(Class aClass) {
		String keyword = getImplicitWaitKeyword(aClass);
		return getKeywordWaitStatus(keyword);
	}

	
	public static boolean getKeywordPrintStatus(String keyWord) {
		return (keyWordToPrintStatus.get(ALL_KEYWORDS) != null && keyWordToPrintStatus.get(ALL_KEYWORDS))
				|| (keyWordToPrintStatus.get(keyWord) != null && keyWordToPrintStatus
						.get(keyWord));
	}

	public static boolean getKeywordDisplayStatus(String keyWord) {
		return (keyWordToDisplayStatus.get(ALL_KEYWORDS) != null && keyWordToDisplayStatus.get(ALL_KEYWORDS))
				|| (keyWordToDisplayStatus.get(keyWord) != null && keyWordToDisplayStatus
						.get(keyWord));
	}
	
	public static boolean getKeywordWaitStatus(String keyWord) {
		return (keyWordToWaitStatus.get(ALL_KEYWORDS) != null && keyWordToWaitStatus.get(ALL_KEYWORDS))
				|| (keyWordToWaitStatus.get(keyWord) != null && keyWordToWaitStatus
						.get(keyWord));
	}

	static {
		// do not need this as lack of this entry means false
//		setKeyWordStatus(ALL_KEYWORDS, false);
	}
}
