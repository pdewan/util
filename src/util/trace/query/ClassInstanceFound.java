package util.trace.query;

import util.trace.Traceable;

public class ClassInstanceFound extends ClassInstanceSearch {
	public ClassInstanceFound(String aMessage, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public ClassInstanceFound(String aMessage, Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject) {
		super(aMessage, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static ClassInstanceFound toTraceable(String aMessage) {
		try {
		return new ClassInstanceFound (aMessage, 
				forName(getPrevious(aMessage)),
				forName(getExpected(aMessage)),
				forName(getLater(aMessage)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ClassInstanceFound newCase (Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		ClassInstanceFound retVal = new ClassInstanceFound(aMessage, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
