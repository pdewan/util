package util.trace.query;

import util.trace.Traceable;

public class EqualObjectMissing extends EqualObjectSearch {
	public EqualObjectMissing(String aMessage, Integer aTestIndex, Integer aReferenceIndex, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public EqualObjectMissing(String aMessage, Integer aTestIndex, Integer aReferenceIndex,  Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static EqualObjectMissing toTraceable(String aMessage) {
		try {
		return new EqualObjectMissing (aMessage, 
				TraceableIndices.getIndex1(aMessage),
				TraceableIndices.getIndex2(aMessage),
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static EqualObjectMissing newCase (Integer aTestIndex, Integer aReferenceIndex, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		String aMessage = toString(aTestIndex, aReferenceIndex,aPreviousObject, anExpectedObject, aLaterObject);
		EqualObjectMissing retVal = new EqualObjectMissing(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
