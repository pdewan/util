package util.trace.query;

import util.trace.Traceable;

public class OrderedEqualObjectDisplaced extends OrderedEqualObjectMissing {
	Integer displacement;
	public OrderedEqualObjectDisplaced(String aMessage, 
			Integer aTestIndex,
			Integer aReferenceIndex,
			Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Integer aDisplacement, Object aFinder) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		displacement = aDisplacement;
	}
	public OrderedEqualObjectDisplaced(String aMessage, 
			Integer aTestIndex,
			Integer aReferenceIndex,
			Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject, Integer aDisplacement) {
		super(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject);
		displacement = aDisplacement;
	}	
	
	public static OrderedEqualObjectDisplaced toTraceable(String aMessage) {
		try {
		return new OrderedEqualObjectDisplaced (aMessage, 
				TraceableIndices.getIndex1(aMessage),
				TraceableIndices.getIndex2(aMessage),
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage),
				getDisplacement(aMessage)
				);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Integer getDisplacement(String aMessage) {
		return Integer.parseInt(Traceable.getArgs(aMessage, DISPLACEMENT).get(0));
	}
	public static final String DISPLACEMENT = "DISPLACEMENT";
	public static String toString (Integer aTestIndex, Integer aReferenceIndex, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Integer aDisplacement) {
		return 
			toString(aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject) + 
			DISPLACEMENT + Traceable.FLAT_LEFT_MARKER + aDisplacement + Traceable.FLAT_RIGHT_MARKER;
	}
	public static OrderedEqualObjectDisplaced newCase (Integer aTestIndex, Integer aReferenceIndex, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Integer aDisplacement, Object aFinder) {
		String aMessage = toString(aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aDisplacement);
		OrderedEqualObjectDisplaced retVal = new OrderedEqualObjectDisplaced(aMessage, aTestIndex, aReferenceIndex, aPreviousObject, anExpectedObject, aLaterObject, aDisplacement, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
