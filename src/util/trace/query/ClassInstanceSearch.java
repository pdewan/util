package util.trace.query;

import util.trace.Traceable;

public class ClassInstanceSearch extends TraceableSearch {
	public ClassInstanceSearch(String aMessage, Integer aTestIndex, Integer aReferenceIndex, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public static Class forName(String aClassName) {
		try {
			return Class.forName(aClassName);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	public ClassInstanceSearch(String aMessage, Integer anIndex1, Integer anIndex2, Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject) {
		super(aMessage, anIndex1, anIndex2, aPreviousObject, anExpectedObject, aLaterObject);
	}
	public static String toString (Integer aTestIndex, Integer aReferenceIndex, Class aPreviousObject, Class anExpectedObject, Class aLaterObject) {
		return 
			TraceableIndices.toString(aTestIndex, aReferenceIndex) +
			((aPreviousObject == null)? "": PREVIOUS + Traceable.NESTED_LEFT_MARKER + (aPreviousObject.getName()) + Traceable.NESTED_RIGHT_MARKER)
			+ " "	
			+ ((anExpectedObject == null)? "": EXPECTED + Traceable.NESTED_LEFT_MARKER + (anExpectedObject.getName()) + Traceable.NESTED_RIGHT_MARKER)
			
			+ ((aLaterObject == null)? "": 
				LATER + Traceable.NESTED_LEFT_MARKER + (aLaterObject.getName()) + Traceable.NESTED_RIGHT_MARKER);
	}
	public static ClassInstanceSearch toTraceable(String aMessage) {
		try {
		return new ClassInstanceSearch (aMessage, 
				TraceableIndices.getIndex1(aMessage),
				TraceableIndices.getIndex2(aMessage),
				forName(getPrevious(aMessage)),
				forName(getExpected(aMessage)),
				forName(getLater(aMessage))
				);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
