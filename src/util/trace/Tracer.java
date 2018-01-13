package util.trace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import util.annotations.Keywords;

public class Tracer {

	public static String ALL_KEYWORDS = "All Key Words";
	static boolean displayThreadName = false;
	static boolean bufferTracedMessages = false;
	static StringBuffer tracedMessages = new StringBuffer();
	

	

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
	public static boolean isShowInfo(String aText) {
		return aText.contains(SHOW_INFO);
	}
	public static void showWarnings(boolean newValue) {
		showWarnings = newValue;
	}
	
	public static final String SHOW_INFO = "Tracer: showInfo = ";

	public static void showInfo(boolean newValue) {
		if (showInfo == newValue) return;
		System.out.println(INFO_PREFIX + SHOW_INFO + newValue);
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
	public static boolean showInfo() {
		return showInfo || tracingLevel.ordinal() >= TracingLevel.INFO.ordinal();
	}
	public static void info(String info) {
		
		if (!showInfo() && !isBufferTracedMessages() ) {
			return;
		}
		String aMessage = INFO_PREFIX + info;
		if (isBufferTracedMessages()) {
			tracedMessages.append(aMessage + "\n");
		} else {
			System.out.println(aMessage);

		}
//		if (showInfo()) {
//			System.out.println(aMessage);
//		} else {
//			tracedMessages.append(aMessage + "\n");
//		}
//		
//		if (showInfo) {
//			System.out.println(INFO_PREFIX + info);
//		} else if (tracingLevel.ordinal() >= TracingLevel.INFO.ordinal()) {
//			System.out.println(INFO_PREFIX + info);
//		}
	}
	
	public static String toInfo(String info) {
		if (showInfo) {
			return INFO_PREFIX + info;
		} else if (tracingLevel.ordinal() >= TracingLevel.INFO.ordinal()) {
			return INFO_PREFIX + info;
		}
		return null;
	}

	public static void info(String keyWord, String info) {
		if (getKeywordPrintStatus(keyWord)) {
			infoWithPrefix(keyWord, info);
		}
	}
	
	public static final String EVENT_SOURCE = "EvtSrc";
	public static final String EVENT_TYPE = "EvtType";


	public static void infoWithPrefix(String prefix, String info) {
//		String qualifier = prefix.contains("trace")?EVENT_TYPE:EVENT_SOURCE;
//		info(qualifier + "(" + prefix + ") " + info);
		printInfo(toInfo(prefix, info));
	}
	
	public static String toInfoWithPrefix(String prefix, String info) {
//		String qualifier = prefix.contains("trace")?EVENT_TYPE:EVENT_SOURCE;
		String qualifier = "";
		if (isDisplayThreadName()) {
			String aThreadName = Thread.currentThread().getName();
			qualifier += "{" + aThreadName + "}";
		}
		return toInfo(qualifier + "(" + prefix + ") " + info);
	}

	public static void info(Object object, String keyWord, String info) {
		printInfo(toInfo(object, keyWord, info));
//		if (!getKeywordPrintStatus(keyWord))
//			return;
//		switch (messagePrefixKind) {
//		case SHORT_CLASS_NAME:
//			infoWithPrefix(object.getClass().getSimpleName(), info);
//			break;
//
//		case FULL_CLASS_NAME:
//			infoWithPrefix(object.getClass().getName(), info);
//			break;
//
//		case PACKAGE_NAME:
//			infoWithPrefix(keyWord, info);
//			break;
//		case OBJECT_TO_STRING:
//			infoWithPrefix(object.toString(), info);
//			break;
//
//		case NONE:
//			info(info);
//
//		}

	}
	public static String toInfoBody(Class object, String keyWord, String info) {
//		if (!getKeywordPrintStatus(keyWord))
//			return null;
		switch (messagePrefixKind) {
		case SHORT_CLASS_NAME:
			return toInfoWithPrefix(object.getSimpleName(), info);

		case FULL_CLASS_NAME:
			return toInfoWithPrefix(object.getName(), info);

		case PACKAGE_NAME:
			return toInfoWithPrefix(keyWord, info);
		case OBJECT_TO_STRING:
			return toInfoWithPrefix(object.toString(), info);

		case NONE:
			return toInfo(info);

		}
		return null;

	}
	
	public static String toInfo(Object object, String keyWord, String info) {
		if (!getKeywordPrintStatus(keyWord))
			return null;
//		return toInfoBody(object, keyWord, info);
		switch (messagePrefixKind) {
		case SHORT_CLASS_NAME:
			return toInfoWithPrefix(object.getClass().getSimpleName(), info);

		case FULL_CLASS_NAME:
			return toInfoWithPrefix(object.getClass().getName(), info);

		case PACKAGE_NAME:
			return toInfoWithPrefix(keyWord, info);
		case OBJECT_TO_STRING:
			return toInfoWithPrefix(object.toString(), info);

		case NONE:
			return toInfo(info);

		}
		return null;

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

//		info(caller, getImplicitPrintKeyword(caller), info);
		String implicitWordKind = getImplicitPrintKeyword(caller);
		if (!getKeywordPrintStatus(implicitWordKind))
			return ;
		
		String body = toInfoBody(caller, getImplicitPrintKeyword(caller), info);
		if (body != null) {
		System.out.println(body);
		}
//		System.out.println(toInfoBody(caller, getImplicitPrintKeyword(caller), info));
	}

	public static void info(Object caller, String info) {
		if (caller instanceof Class) {
			info ((Class) caller, info);
			return;
		}

//		info(caller, getImplicitPrintKeyword(caller), info);
		printInfo(toInfo(caller, info));
	}
	
	public static boolean isPrintInfoEnabled(Object caller) {
		String keyWord =  getImplicitPrintKeyword(caller);
		return getKeywordPrintStatus(keyWord);
	}
	public static boolean isPrintInfoEnabled(Class caller) {
		String keyWord =  getImplicitPrintKeyword(caller);
		return getKeywordPrintStatus(keyWord);
	}
	static void  printInfo(String anInfo) {
		if (anInfo == null) return;
		if (isBufferTracedMessages()) {
			tracedMessages.append(anInfo + "\n");
		} else {
		System.out.println(anInfo);
		}
	}
	public static String toInfo(Object caller, String info) {

		return toInfo(caller, getImplicitPrintKeyword(caller), info);
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
			if (c.getPackage() != null) {			
			return c.getPackage().getName();
			} else return c.getName();
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
	public static boolean isDisplayThreadName() {
		return displayThreadName;
	}
	public static void setDisplayThreadName(boolean displayThreadName) {
		Tracer.displayThreadName = displayThreadName;
	}
	public static boolean isBufferTracedMessages() {
		return bufferTracedMessages;
	}
	public static void setBufferTracedMessages(boolean bufferUntracedMessages) {
		System.out.println("Buffer traced nessages ="  + bufferUntracedMessages);
		Tracer.bufferTracedMessages = bufferUntracedMessages;
	}
	
	public static void clearTracedMessages() {
		tracedMessages.setLength(0);
	}
	public static String getBufferedTracedMessages() {
		return tracedMessages.toString();
	}

	static {
		// do not need this as lack of this entry means false
//		setKeyWordStatus(ALL_KEYWORDS, false);
	}
}
