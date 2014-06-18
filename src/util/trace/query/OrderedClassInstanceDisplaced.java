package util.trace.query;

import util.trace.Traceable;

public class OrderedClassInstanceDisplaced extends OrderedClassInstanceMissing {
	Integer displacement;
	public OrderedClassInstanceDisplaced(String aMessage, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Integer aDisplacement, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		displacement = aDisplacement;
	}
	public OrderedClassInstanceDisplaced(String aMessage, Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject, Integer aDisplacement) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
		displacement = aDisplacement;
	}	
	
	public static OrderedClassInstanceDisplaced toTraceable(String aMessage) {
		try {
		return new OrderedClassInstanceDisplaced (aMessage, 
				forName(getPrevious(aMessage)),
				forName(getExpected(aMessage)),
				forName(getLater(aMessage)),
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
	public static String toString (Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Integer aDisplacement) {
		return 
			toString(aPreviousObject, anExpectedObject, aLaterObject) + 
			DISPLACEMENT + Traceable.FLAT_LEFT_MARKER + aDisplacement + Traceable.FLAT_RIGHT_MARKER;
	}
	public static OrderedClassInstanceDisplaced newCase (Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Integer aDisplacement, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		OrderedClassInstanceDisplaced retVal = new OrderedClassInstanceDisplaced(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aDisplacement, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
