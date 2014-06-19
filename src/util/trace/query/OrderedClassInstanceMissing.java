package util.trace.query;

import util.trace.Traceable;

public class OrderedClassInstanceMissing extends ClassInstanceMissing {
	public OrderedClassInstanceMissing(String aMessage, Integer anIndex1, Integer anIndex2,  Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		super(aMessage, anIndex1, anIndex2, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public OrderedClassInstanceMissing(String aMessage, Integer anIndex1, Integer anIndex2,  Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject) {
		super(aMessage,  anIndex1, anIndex2, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static OrderedClassInstanceMissing toTraceable(String aMessage) {
		try {
		return new OrderedClassInstanceMissing (aMessage, 
				getIndex1(aMessage),
				getIndex2(aMessage),
				forName(getPrevious(aMessage)),
				forName(getExpected(aMessage)),
				forName(getLater(aMessage)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static OrderedClassInstanceMissing newCase (Integer aTestIndex, Integer aReferenceIndex, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		String aMessage = toString(aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject);
		OrderedClassInstanceMissing retVal = new OrderedClassInstanceMissing(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
