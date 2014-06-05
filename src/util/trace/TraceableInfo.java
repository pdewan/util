package util.trace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public  class TraceableInfo extends Traceable {
	static boolean printDuplicates = true; // info means this is about steps, which should be duplicated by default
	static boolean printSource = false; // source printed when event class is the info first arg
	static boolean printTraceable = false; // traceable printed when firing class is the info source
	
	
	public TraceableInfo (String aMessage) {
		super(aMessage);
		printDuplicates = true; 
//		Tracer.info(aFinder, aMessage);
//		TraceableBus.newEvent(this);
	}
	public TraceableInfo (String aMessage, Object aFinder) {
		super(aMessage, aFinder);
//		Tracer.info(aFinder, aMessage);
//		TraceableBus.newEvent(this);
	}
	public TraceableInfo(String aMessage, Long aTimeStamp, String aThreadName, Object aFinder) {
		super(aMessage, aTimeStamp, aThreadName, aFinder);
		
	}

	public void init (Object aFinder) {
		super.init(aFinder);
		Tracer.info(aFinder, getMessage());

//		TraceableBus.newEvent(this);
		
	}
	
	@Override
	protected
	void maybePrintMessage(String aMessage, boolean isDuplicate) {

		if (printDuplicates || !isDuplicate) {
			Class sourceClass = (finder instanceof Class)? ((Class) finder):finder.getClass();
			String aFinderMessage = printTraceable? Tracer.EVENT_SOURCE + "(" + this.getClass().getSimpleName() + ") " + aMessage:aMessage;
			String aTraceableMessage = printSource? Tracer.EVENT_TYPE + "(" + sourceClass.getSimpleName() + ") " + aMessage:aMessage;
			Tracer.info(finder, aFinderMessage); // discriminate by event firer
			Tracer.info(this, aTraceableMessage); // discriminate by event
		}
	}
	public static TraceableInfo toTraceable (String aMessage) {
		Long aTimeStamp = toTimeStamp(aMessage);
		String aThreadName = toThreadName(aMessage);
		return new TraceableInfo(aMessage, aTimeStamp, aThreadName, null);		
	}
	public static List<String> getArgs(String aTraceLine, String aDescriptor) {
//		aTraceLine = aTraceLine.trim();
		List<String> retVal = new ArrayList();
		
		try {
			int startArgsIndex = aTraceLine.indexOf(aDescriptor + "(");
			int endArgsIndex = aTraceLine.indexOf(')', startArgsIndex);
			String anArgsString = aTraceLine.substring(startArgsIndex, endArgsIndex).trim();
			int startArgIndex = 0;			
			while (true) {
				int endArgIndex = aTraceLine.indexOf(',');
				if (endArgIndex == -1)
					endArgIndex = anArgsString.length();
				String arg = anArgsString.substring(startArgIndex, endArgIndex);
				retVal.add(arg);
				if (endArgIndex == anArgsString.length())
					return retVal;
				startArgIndex = endArgIndex + 1;				
			}
		} catch (Exception e) {
			return null;
		}
	}
	public static Class toEvtSourceClass (String aTraceLine) {
		try {
			List<String> anArgs = getArgs(aTraceLine, Tracer.EVENT_SOURCE);
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
	public static boolean isPrintDuplicates() {
		return printDuplicates;
	}
	
	public static void setPrintDuplicates(boolean newVal) {
		printDuplicates = newVal;
	}
	
	public static TraceableInfo newInfo(String aMessage, Object aFinder) {    	
		TraceableInfo retVal = new TraceableInfo(aMessage, aFinder);
   	    retVal.announce();
    	return retVal;
	}
	public static boolean isPrintSource() {
		return printSource;
	}
	public static void setPrintSource(boolean printSource) {
		TraceableInfo.printSource = printSource;
	}
	public static boolean isPrintTraceable() {
		return printTraceable;
	}
	public static void setPrintTraceable(boolean printTraceable) {
		TraceableInfo.printTraceable = printTraceable;
	}
}
