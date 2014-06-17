package util.trace.query;

import util.trace.Traceable;

public class OrderedClassInstanceMissing extends ClassInstanceFound {
	public OrderedClassInstanceMissing(String aMessage, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public OrderedClassInstanceMissing(String aMessage, Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static OrderedClassInstanceMissing toTraceable(String aMessage) {
		try {
		return new OrderedClassInstanceMissing (aMessage, 
				forName(getPrevious(aMessage)),
				forName(getExpected(aMessage)),
				forName(getLater(aMessage)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static OrderedClassInstanceMissing newCase (Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		OrderedClassInstanceMissing retVal = new OrderedClassInstanceMissing(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
