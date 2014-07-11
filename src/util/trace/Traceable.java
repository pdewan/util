package util.trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.annotations.Visible;
import util.models.EqualPropertiesDefiner;

// will not make it abstract as we will unparse a string into it
public  class Traceable extends RuntimeException implements EqualPropertiesDefiner {
	static Set<String> messages = new HashSet<String>();
	protected Object finder;
	protected Long timeStamp;	
	protected String threadName;
	protected String eventSource;
	protected boolean display;
	protected boolean wait;
	protected boolean exists;
	protected List<String> equalPropertiesList;
	protected String[] equalPropertiesArray = {};
	
	static boolean printThread = true; // rmi threads can be have different names in different invocations
	

	static boolean printTime = true; // again time makes it difficult to diff different traces
	protected void maybePrintMessage(String aMessage, boolean isDuplicate) {
		
	}
	public Traceable(String aMessage, Object aFinder) {
		super(aMessage);
		init(aMessage, aFinder, System.currentTimeMillis(), Thread.currentThread().getName(), toEvtSourceClass(aMessage));
//		finder = aFinder;
//		timeStamp = System.currentTimeMillis();
//		exists = messages.contains(aMessage);	
//		boolean retVal;
//		if (!exists)
//		 retVal =messages.add(aMessage);
	}
	@Visible(false)

	public void init (String aMessage, Object aFinder, Long aTimeStamp, String aThreadName, String anEventSource) {
		finder = aFinder;
		timeStamp = aTimeStamp;
		threadName = aThreadName;
		exists = messages.contains(aMessage);	
		eventSource = anEventSource;
		boolean retVal;
		// what are we doing with retVal?
		if (!exists)
		 retVal =messages.add(aMessage);
//		equalPropertiesList = Arrays.asList(equalPropertiesArray);
		setEqualPropertiesList();
	}
	protected void setEqualPropertiesList() {
		equalPropertiesList = Arrays.asList(equalPropertiesArray);

	}
	public Traceable(String aMessage, Long aTimeStamp, String aThreadName, String anEventSource, Object aFinder) {
		super(aMessage);
		init(aMessage, aFinder, aTimeStamp, aThreadName, anEventSource);
//		finder = aFinder;
//		timeStamp = System.currentTimeMillis();
//		exists = messages.contains(aMessage);	
//		boolean retVal;
//		if (!exists)
//		 retVal =messages.add(aMessage);
//		maybePrintMessage(aMessage, exists);		
	}
	public Traceable(String aMessage, Traceable aTraceable) {
		super(aMessage);
		init(aMessage, aTraceable.getFinder(), aTraceable.getTimeStamp(), aTraceable.getThreadName(), aTraceable.getEventSource());
//		finder = aFinder;
//		timeStamp = System.currentTimeMillis();
//		exists = messages.contains(aMessage);	
//		boolean retVal;
//		if (!exists)
//		 retVal =messages.add(aMessage);
	}
	
	public String getThreadName() {
		return threadName;
	}
	public String getEventSource() {
		return eventSource;
	}
	@Visible(false)

	public static Set<String> getMessages() {
		return messages;
	}
	
	public void announce() {
		maybePrintMessage(getMessage(), exists);

		TraceableBus.newEvent(this);

	}
	
	public Traceable(String aMessage) {	
		super(aMessage);
		setEqualPropertiesList();

		// no firing of event, postponed until init
	}
	public void init (Object aFinder) {
		finder = aFinder;
		timeStamp = System.currentTimeMillis();
		TraceableBus.newEvent(this);
		
	}
	@Visible(false)

	public Object getFinder() {
		return finder;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	
//	public boolean getDisplay() {
//		return Tracer.getKeywordDisplayStatus(this) || Tracer.getKeywordDisplayStatus(getFinder());
//	}
	@Visible(false)

	public boolean getDisplay() {
		return display || Tracer.getKeywordDisplayStatus(getFinder());
	}
	
	public void setDisplay(boolean newVal) {
		display = newVal;
	}
//	public boolean getWait() {
//		return Tracer.getKeywordWaitStatus(this) ||  Tracer.getKeywordWaitStatus(getFinder()) ;
//	}
	@Visible(false)

	public boolean getWait() {
		return wait ||  Tracer.getKeywordWaitStatus(getFinder()) ;
	}
	
	public void setWait(boolean newVal) {
		wait = newVal;
	}
	public static String toTime(Date aDate) {
		return aDate.getHours() + ":" + aDate.getMinutes() + ":" + aDate.getSeconds();
	}
//	public static TraceableInfo toTraceable (String aMessage) {
//		return null;
//	}

	public static final String TIME = "Time";
	public static final String THREAD = "Thread";
	public static String toString (long aTime) {
		
		Date date = new Date(aTime);
		
//		return   " Time(" + aTime + ", " + toTime(date) + ")" + 
//		" " + "Thread(" + Thread.currentThread().getName() + ")" ;
		return (printTime?  TIME + "(" + aTime + ", " + toTime(date) + ")":"") +
//				" Time(" + aTime + ", " + toTime(date) + ")" + 
//		" " + "Thread(" + Thread.currentThread().getName() + ")" ;
		(printThread?" " +  THREAD + "(" +  Thread.currentThread().getName() + ")" :"");
		
	}
	public static Traceable toTraceable (String aMessage) {
		Long aTimeStamp = toTimeStamp(aMessage);
		String aThreadName = toThreadName(aMessage);
		String anEventSource = toEvtSourceClass(aMessage);
		return new Traceable(aMessage, aTimeStamp, aThreadName, anEventSource,null);		
	}
//	public Traceable(String aMessage, Long aTimeStamp, String aThreadName, String anEventSource, Object aFinder) {
//	this(aMessage, aTimeStamp, aThreadName, aFinder);
//	
//}
	public static String getArgOfCompositeDescriptor(String aTraceLine, String aDescriptor) {
		int aStartDesciptorIndex = aTraceLine.indexOf(aDescriptor + "_");
		if (aStartDesciptorIndex < 0)
			return null;
		int startArgIndex = aStartDesciptorIndex + 1 + aDescriptor.length();
		int endArgIndex = aTraceLine.indexOf(')', aStartDesciptorIndex) + 1; // incude closing paren in inner operation which is the arg

		return aTraceLine.substring(startArgIndex, endArgIndex);
	}
	public static String getSuffixOfCompositeDescriptor(String aTraceLine, String aDescriptor) {
		int aStartDesciptorIndex = aTraceLine.indexOf(aDescriptor + "_");
		if (aStartDesciptorIndex < 0)
			return null;
		int startArgIndex = aStartDesciptorIndex + 1 + aDescriptor.length();

		return aTraceLine.substring(startArgIndex, aTraceLine.length());
	}
	public static final String FLAT_LEFT_MARKER = "(";
	public static final String FLAT_RIGHT_MARKER = ")";
	public static final String NESTED_LEFT_MARKER = "{";
	public static final String NESTED_RIGHT_MARKER = "}";

	public static List<String> getArgs(String aTraceLine, String aDescriptor) {
		return getArgs(aTraceLine, aDescriptor, FLAT_LEFT_MARKER, FLAT_RIGHT_MARKER);
	}
	public static String getArg(String aTraceLine, String aDescriptor) {
		return getArgs(aTraceLine, aDescriptor, FLAT_LEFT_MARKER, FLAT_RIGHT_MARKER).get(0);
	}
	public static List<String> getNestedArgs(String aTraceLine, String aDescriptor) {
		return getArgs(aTraceLine, aDescriptor, NESTED_LEFT_MARKER, NESTED_RIGHT_MARKER);
	}

	public static List<String> getArgs(String aTraceLine, String aDescriptor, String aLeftMarker, String aRightMarker) {
//		aTraceLine = aTraceLine.trim();
		List<String> retVal = new ArrayList();
		
		try {
			int startDesciptorIndex = aTraceLine.indexOf(aDescriptor + aLeftMarker) ;
			if (startDesciptorIndex < 0) {
				return retVal;
			}
//			int startArgsIndex = aTraceLine.indexOf(aDescriptor + "(") + aDescriptor.length() + 1; // need to go past descriptor and the parenthesis
			int startArgsIndex = startDesciptorIndex + aDescriptor.length() + 1; // need to go past descriptor and the parenthesis

			int endArgsIndex = aTraceLine.indexOf(aRightMarker, startArgsIndex);
			String anArgsString = aTraceLine.substring(startArgsIndex, endArgsIndex).trim();
			int startArgIndex = 0;			
			while (true) {
				int endArgIndex = anArgsString.indexOf(',', startArgIndex);
				if (endArgIndex == -1)
					endArgIndex = anArgsString.length();
				String arg = anArgsString.substring(startArgIndex, endArgIndex);
				retVal.add(arg.trim());
				if (endArgIndex == anArgsString.length())
					return retVal;
				startArgIndex = endArgIndex + 1;				
			}
		} catch (Exception e) {
			return null;
		}
	}
	public static String toEvtSourceClass (String aTraceLine) {
		try {
			List<String> anArgs = getArgs(aTraceLine, Tracer.EVENT_SOURCE);
			return anArgs.get(0);
		} catch (Exception e) {
			return null;
		}
	}
	public static Class toEvtTypeClass (String aTraceLine) {
		try {
			List<String> anArgs = getArgs(aTraceLine, Tracer.EVENT_TYPE);
			return Class.forName(anArgs.get(0));
		} catch (Exception e) {
			return null;
		}
	}
	public static Long toTimeStamp (String aTraceLine) {
		try {
			List<String> anArgs = getArgs(aTraceLine, TIME);
			return Long.parseLong(anArgs.get(0));
		} catch (Exception e) {
			return null;
		}
	}
	public static String toThreadName (String aTraceLine) {
		try {
			List<String> anArgs = getArgs(aTraceLine, THREAD);
			return anArgs.get(0);
		} catch (Exception e) {
			return null;
		}
	}
	@Visible(false)

	public static boolean isPrintThread() {
		return printThread;
	}
	@Visible(false)

	public static void setPrintThread(boolean isPrintThread) {
		Traceable.printThread = isPrintThread;
	}
	@Visible(false)

	public static boolean isPrintTime() {
		return printTime;
	}
	public static void setPrintTime(boolean isPrintTime) {
		Traceable.printTime = isPrintTime;
	}
	
	public List<String> equalProperties() {
		return equalPropertiesList;
	}

}
