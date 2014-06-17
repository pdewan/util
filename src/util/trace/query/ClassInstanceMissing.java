package util.trace.query;

import util.trace.Traceable;

public class ClassInstanceMissing extends ClassInstanceSearch {
	public ClassInstanceMissing(String aMessage, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public ClassInstanceMissing(String aMessage, Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static ClassInstanceMissing toTraceable(String aMessage) {
		try {
		return new ClassInstanceMissing (aMessage, 
				forName(getPrevious(aMessage)),
				forName(getExpected(aMessage)),
				forName(getLater(aMessage)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ClassInstanceMissing newCase (Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		ClassInstanceMissing retVal = new ClassInstanceMissing(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
