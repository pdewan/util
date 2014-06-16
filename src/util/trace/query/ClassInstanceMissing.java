package util.trace.query;

import util.trace.Traceable;

public class ClassInstanceMissing extends ObjectFound {
	public ClassInstanceMissing(String aMessage, Object aPreviousObject, Class anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public ClassInstanceMissing(String aMessage, Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	public static Traceable toTraceable(String aMessage) {
		return new ClassInstanceMissing (aMessage, 
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage));
	}
	public static ClassInstanceMissing newCase (Object aPreviousObject, Class anExpectedObject, Object aLaterObject) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		ClassInstanceMissing retVal = new ClassInstanceMissing(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
		retVal.announce();
		return retVal;
	}
	

}
