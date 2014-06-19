package util.trace.query;

import util.trace.Traceable;
import util.trace.TraceableInfo;

public class UnmatchedObject extends TraceableIndices {
	Object unmatchedObject;
	public UnmatchedObject(String aMessage, 
			Integer aTestIndex, Integer aReferenceIndex, 
			Object anUnmatchedObject, Object aFinder) {
		super(aMessage, aTestIndex, aReferenceIndex, aFinder);
		unmatchedObject = anUnmatchedObject;
	}
	public UnmatchedObject(String aMessage, Integer aTestIndex, Integer aReferenceIndex,
			Object anUnmatchedObject) {
		super(aMessage, aTestIndex, aReferenceIndex);
		unmatchedObject = anUnmatchedObject;

	}
	
	public static final String UNMATCHED = "Unmatched";
	
	public static final String toString(Integer aTestIndex, Integer aReferenceIndex, Object anMatchedObject) {
		return TraceableIndices.toString(aTestIndex, aReferenceIndex) + " " +
				UNMATCHED + Traceable.FLAT_LEFT_MARKER + 
				anMatchedObject +
				TraceableInfo.FLAT_RIGHT_MARKER;
	}
	public static String getUnmatched(String aMessage) {
		return Traceable.getArgs(aMessage, UNMATCHED).get(0);
	}
	
	public static UnmatchedObject toTraceable(String aMessage, 
			Integer aTestIndex, 
			Integer aReferenceIndex) {
		try {
		return new UnmatchedObject (				
				aMessage, 
				TraceableIndices.getIndex1(aMessage),
				TraceableIndices.getIndex2(aMessage),
				getUnmatched(aMessage));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static UnmatchedObject newCase (Integer aTestIndex, Integer aReferenceIndex, Object anUnmatchedObject,  Object aFinder) {
		String aMessage = toString(aTestIndex, aReferenceIndex, anUnmatchedObject);
		UnmatchedObject retVal = new UnmatchedObject(aMessage, aTestIndex, aReferenceIndex, anUnmatchedObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
