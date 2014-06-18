package util.trace.query;

import util.trace.Traceable;

public class ClassInstanceFound extends ClassInstanceSearch {
	public ClassInstanceFound(String aMessage, Integer anIndex1, Integer anIndex2,  Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		super(aMessage, anIndex1, anIndex2, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
	}
	public ClassInstanceFound(String aMessage, Integer anIndex1, Integer anIndex2,  Class aPreviousObject,
			Class anExpectedObject, Class aLaterObject) {
		super(aMessage,  anIndex1, anIndex2, aPreviousObject, anExpectedObject, aLaterObject);
	}
	
	public static ClassInstanceFound toTraceable(String aMessage) {
		try {
		return new ClassInstanceFound (aMessage, 
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
	public static ClassInstanceFound newCase (Integer anIndex1, Integer anIndex2, Class aPreviousObject, Class anExpectedObject, Class aLaterObject, Object aFinder) {
		String aMessage = toString(aPreviousObject, anExpectedObject, aLaterObject);
		ClassInstanceFound retVal = new ClassInstanceFound(aMessage, anIndex1, anIndex2, aPreviousObject, anExpectedObject, aLaterObject, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
