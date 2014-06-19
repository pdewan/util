package util.trace.query;

import util.trace.Traceable;

public class EqualObjectFound extends EqualObjectSearch {
	public EqualObjectFound(String aMessage, Integer aTestIndex, Integer aReferenceIndex, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public EqualObjectFound(String aMessage, Integer aTestIndex, Integer aReferenceIndex,  Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static EqualObjectFound toTraceable(String aMessage) {
		try {
		return new EqualObjectFound (aMessage, 
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
	public static EqualObjectFound newCase (Integer aTestIndex, Integer aReferenceIndex, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		String aMessage = toString(aTestIndex, aReferenceIndex,aPreviousObject, anExpectedObject, aLaterObject);
		EqualObjectFound retVal = new EqualObjectFound(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
