package util.trace.query;

import util.trace.Traceable;

public class OrderedEqualObjectMissing extends EqualObjectFound {
	public OrderedEqualObjectMissing(String aMessage,
			Integer aTestIndex, Integer aReferenceIndex,
			Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public OrderedEqualObjectMissing(String aMessage, 
			Integer aTestIndex, Integer aReferenceIndex,
			Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static OrderedEqualObjectMissing toTraceable(String aMessage) {
		try {
		return new OrderedEqualObjectMissing (aMessage, 
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
	public static OrderedEqualObjectMissing newCase (
			Integer aTestIndex,
			Integer aReferenceIndex,
			Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		String aMessage = toString(aTestIndex, aReferenceIndex,
				
				aPreviousObject, anExpectedObject, aLaterObject);
		OrderedEqualObjectMissing retVal = 
				new OrderedEqualObjectMissing(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
