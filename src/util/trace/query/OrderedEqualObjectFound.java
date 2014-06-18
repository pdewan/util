package util.trace.query;

import util.trace.Traceable;

public class OrderedEqualObjectFound extends EqualObjectFound {
	public OrderedEqualObjectFound(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public OrderedEqualObjectFound(String aMessage, Object aPreviousObject,
			Object anExpectedObject, Object aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static OrderedEqualObjectFound toTraceable(String aMessage) {
		try {
		return new OrderedEqualObjectFound (aMessage, 
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static OrderedEqualObjectFound newCase (Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		OrderedEqualObjectFound retVal = new OrderedEqualObjectFound(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
