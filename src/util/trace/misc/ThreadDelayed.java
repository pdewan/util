package util.trace.misc;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class ThreadDelayed extends TraceableInfo {
	

	public ThreadDelayed (String aMessage, Object aFinder, 
			long aDelay ) {
		super (aMessage, aFinder);	
	}
	
	public static ThreadDelayed newCase( Object aSource, 
			long aDelay) {
    	String aMessage = Long.toString(aDelay);
    	ThreadDelayed retVal = 
    			new ThreadDelayed(aMessage, aSource, aDelay);
    	retVal.announce();
    	return retVal;
	}
	public static void enablePrint() {
		Tracer.setKeywordWaitStatus(ThreadDelayed.class, true);
	}

}
