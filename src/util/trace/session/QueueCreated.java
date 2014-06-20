package util.trace.session;

import util.trace.Traceable;

public class QueueCreated extends ProcessInfo {
	String name;
	public QueueCreated(String aMessage, ProcessInfo aTraceable,  String aName) {
		super(aMessage, aTraceable);
		name = aName;
	}	
	public QueueCreated(String aMessage, 
			String aProcessName,
			String aName,
			Object aFinder) {
		super(aMessage, aProcessName, aFinder);
		name = aName;

	}
	public QueueCreated(String aMessage, String aProcessName,
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
	
	public static QueueCreated toTraceable(String aMessage) {
		return new QueueCreated(aMessage, ProcessInfo.toTraceable(aMessage), getName(aMessage));
	}
	
		
	public static QueueCreated newCase (String aProcessName, String aThreadName,  Object aFinder) {
		String aMessage = toString (aProcessName, aThreadName);
		QueueCreated retVal = new QueueCreated(aMessage, aProcessName, aThreadName, aFinder);
		retVal.announce();
		return retVal;
	}

}
