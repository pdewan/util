package util.trace.query;

import util.trace.Traceable;
import util.trace.TraceableInfo;

public class UnmatchedObject extends TraceableInfo {
	Object unmatchedObject;
	public UnmatchedObject(String aMessage,  Object anUnmatchedObject, Object aFinder) {
		super(aMessage, aFinder);
		unmatchedObject = anUnmatchedObject;
	}
	public UnmatchedObject(String aMessage, 
			Object anUnmatchedObject) {
		super(aMessage);
		unmatchedObject = anUnmatchedObject;

	}
	
	public static final String UNMATCHED = "Unmatched";
	
	public static final String toString(Object anMatchedObject) {
		return UNMATCHED + Traceable.FLAT_LEFT_MARKER + 
				anMatchedObject +
				TraceableInfo.FLAT_RIGHT_MARKER;
	}
	public static String getUnmatched(String aMessage) {
		return Traceable.getArgs(aMessage, UNMATCHED).get(0);
	}
	
	public static UnmatchedObject toTraceable(String aMessage) {
		try {
		return new UnmatchedObject (aMessage, 
				getUnmatched(aMessage));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static UnmatchedObject newCase (Object anUnmatchedObject,  Object aFinder) {
		String aMessage = toString(anUnmatchedObject);
		UnmatchedObject retVal = new UnmatchedObject(aMessage, anUnmatchedObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
