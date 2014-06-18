package util.trace.query;

import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.console.ConsoleInput;

public class TraceableFound extends TraceableSearch {
	

	public TraceableFound(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public TraceableFound(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
		
	public static TraceableFound toTraceable(String aMessage) {
		return new TraceableFound (aMessage, 
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage));
	}
	public static TraceableFound newCase (Object aPreviousObject, Object anExpectedObject, Object aLaterObject) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		TraceableFound retVal = new TraceableFound(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
		retVal.announce();
		return retVal;
	}

}
