package util.trace.query;

import util.trace.Traceable;

public class ClassInstanceSearch extends TraceableSearch {
	public ClassInstanceSearch(String aMessage, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public static Class forName(String aClassName) {
		try {
			return Class.forName(aClassName);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	public ClassInstanceSearch(String aMessage, Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	public static String toString (Class aPreviousObject, Class anExpectedObject, Class aLaterObject) {
		return 
			((aPreviousObject == null)? "": PREVIOUS + Traceable.NESTED_LEFT_MARKER + (aPreviousObject.getName()) + Traceable.NESTED_RIGHT_MARKER)
			+ " "	
			+ ((anExpectedObject == null)? "": EXPECTED + Traceable.NESTED_LEFT_MARKER + (anExpectedObject.getName()) + Traceable.NESTED_RIGHT_MARKER)
			
			+ ((aLaterObject == null)? "": 
				LATER + Traceable.NESTED_LEFT_MARKER + (aLaterObject.getName()) + Traceable.NESTED_RIGHT_MARKER);
	}
	
	

}
