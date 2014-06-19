package util.trace.query;

import util.trace.Traceable;

public class OrderedEqualObjectFound extends EqualObjectFound {
	public OrderedEqualObjectFound(String aMessage,
			Integer aTestIndex, Integer aReferenceIndex,
			Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public OrderedEqualObjectFound(String aMessage, 
			Integer aTestIndex, Integer aReferenceIndex,
			Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static OrderedEqualObjectFound toTraceable(String aMessage) {
		try {
		return new OrderedEqualObjectFound (aMessage, 
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
	public static OrderedEqualObjectFound newCase (
			Integer aTestIndex,
			Integer aReferenceIndex,
			Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		String aMessage = toString(aTestIndex, aReferenceIndex,
				
				aPreviousObject, anExpectedObject, aLaterObject);
		OrderedEqualObjectFound retVal = 
				new OrderedEqualObjectFound(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
