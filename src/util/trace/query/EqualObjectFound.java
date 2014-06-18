package util.trace.query;

import util.trace.Traceable;

public class EqualObjectFound extends EqualObjectSearch {
	public EqualObjectFound(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public EqualObjectFound(String aMessage, Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static EqualObjectFound toTraceable(String aMessage) {
		try {
		return new EqualObjectFound (aMessage, 
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static EqualObjectFound newCase (Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		EqualObjectFound retVal = new EqualObjectFound(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
