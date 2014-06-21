package util.trace.session;

import util.trace.Traceable;

public class MulticastGroupCreated extends ProcessInfo {
	String name;
	public MulticastGroupCreated(String aMessage, ProcessInfo aTraceable,  String aName) {
		super(aMessage, aTraceable);
		name = aName;
	}	
	public MulticastGroupCreated(String aMessage, 
			String aProcessName,
			String aName,
			Object aFinder) {
		super(aMessage, aProcessName, aFinder);
		name = aName;

	}
	public MulticastGroupCreated(String aMessage, String aProcessName,
			String aName,
			Traceable aTraceable
//			String aProcessName,			
//			Long aTimeStamp,
//			String aThreadName,
//			Object aFinder
			) {
		super(aMessage, aProcessName, aTraceable);
	}
	
	public String getName() {
		return name;
	}
	
	public static final String NAME = "Name";
	public static String toString(String aProcessName, String aName) {
		return toString(aProcessName) + " " + NAME + "(" + aName + ")";
	}
	
	public static String getName (String aMessage) {
		return getArg(aMessage, NAME);
	}
	
	public static MulticastGroupCreated toTraceable(String aMessage) {
		return new MulticastGroupCreated(aMessage, ProcessInfo.toTraceable(aMessage), getName(aMessage));
	}
	
		
	public static MulticastGroupCreated newCase (String aProcessName, String aThreadName,  Object aFinder) {
		String aMessage = toString (aProcessName, aThreadName);
		MulticastGroupCreated retVal = new MulticastGroupCreated(aMessage, aProcessName, aThreadName, aFinder);
		retVal.announce();
		return retVal;
	}

}
