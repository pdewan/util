package util.trace.query;

import util.trace.Traceable;

public class OrderedClassInstanceFound extends ClassInstanceFound {
	public OrderedClassInstanceFound(String aMessage, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public OrderedClassInstanceFound(String aMessage, Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static OrderedClassInstanceFound toTraceable(String aMessage) {
		try {
		return new OrderedClassInstanceFound (aMessage, 
				forName(getPrevious(aMessage)),
				forName(getExpected(aMessage)),
				forName(getLater(aMessage)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static OrderedClassInstanceFound newCase (Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		OrderedClassInstanceFound retVal = new OrderedClassInstanceFound(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
