package util.trace.query;

import util.trace.Traceable;

public class ClassInstanceFound extends ObjectFound {
	public ClassInstanceFound(String aMessage, Object aPreviousObject, Class anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public ClassInstanceFound(String aMessage, Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	public static Traceable toTraceable(String aMessage) {
		return new ClassInstanceFound (aMessage, 
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage));
	}
	public static ClassInstanceFound newCase (Object aPreviousObject, Class anExpectedObject, Object aLaterObject) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		ClassInstanceFound retVal = new ClassInstanceFound(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
		retVal.announce();
		return retVal;
	}
	

}
