package util.trace.query;

import util.trace.Traceable;

public class EqualObjectSearch extends TraceableSearch {
	public EqualObjectSearch(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	
	public EqualObjectSearch(String aMessage, Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	public static String toString (Object aPreviousObject, Object anExpectedObject, Object aLaterObject) {
		return 
			((aPreviousObject == null)? "": PREVIOUS + Traceable.NESTED_LEFT_MARKER + (aPreviousObject.toString()) + Traceable.NESTED_RIGHT_MARKER)
			+ " "	
			+ ((anExpectedObject == null)? "": EXPECTED + Traceable.NESTED_LEFT_MARKER + (anExpectedObject.toString()) + Traceable.NESTED_RIGHT_MARKER)
			
			+ ((aLaterObject == null)? "": 
				LATER + Traceable.NESTED_LEFT_MARKER + (aLaterObject.toString()) + Traceable.NESTED_RIGHT_MARKER);
	}
	
	

}
