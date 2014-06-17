package util.trace.query;

import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.console.ConsoleInput;

public class ObjectMissing extends ObjectSearch {
	

	public ObjectMissing(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public ObjectMissing(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
		
	public static Traceable toTraceable(String aMessage) {
		return new ObjectMissing (aMessage, 
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage));
	}
	public static ObjectMissing newCase (Object aPreviousObject, Object anExpectedObject, Object aLaterObject) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		ObjectMissing retVal = new ObjectMissing(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
		retVal.announce();
		return retVal;
	}

}
