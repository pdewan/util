package util.trace.session;

import util.trace.Traceable;

public class ThreadCreated extends ProcessInfo {
	String name;
	public ThreadCreated(String aMessage, ProcessInfo aTraceable,  String aName) {
		super(aMessage, aTraceable);
		name = aName;
	}	
	public ThreadCreated(String aMessage, 
			String aProcessName,
			String aName,
			Object aFinder) {
		super(aMessage, aProcessName, aFinder);
		name = aName;

	}
	public ThreadCreated(String aMessage, String aProcessName,
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
	
	public static ThreadCreated toTraceable(String aMessage) {
		return new ThreadCreated(aMessage, ProcessInfo.toTraceable(aMessage), getName(aMessage));
	}
	
		
	public static ThreadCreated newCase (String aThreadName, String aProcessName,  Object aFinder) {
		String aMessage = toString (aProcessName, aThreadName);
		ThreadCreated retVal = new ThreadCreated(aMessage, aProcessName, aThreadName, aFinder);
		retVal.announce();
		return retVal;
	}

}
