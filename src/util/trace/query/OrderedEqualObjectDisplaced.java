package util.trace.query;

import util.trace.Traceable;

public class OrderedEqualObjectDisplaced extends OrderedEqualObjectMissing {
	Integer displacement;
	public OrderedEqualObjectDisplaced(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Integer aDisplacement, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		displacement = aDisplacement;
	}
	public OrderedEqualObjectDisplaced(String aMessage, Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject, Integer aDisplacement) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
		displacement = aDisplacement;
	}	
	
	public static OrderedEqualObjectDisplaced toTraceable(String aMessage) {
		try {
		return new OrderedEqualObjectDisplaced (aMessage, 
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
	public static String toString (Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Integer aDisplacement) {
		return 
			toString(aPreviousObject, anExpectedObject, aLaterObject) + 
			DISPLACEMENT + Traceable.FLAT_LEFT_MARKER + aDisplacement + Traceable.FLAT_RIGHT_MARKER;
	}
	public static OrderedEqualObjectDisplaced newCase (Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Integer aDisplacement, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		OrderedEqualObjectDisplaced retVal = new OrderedEqualObjectDisplaced(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aDisplacement, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
