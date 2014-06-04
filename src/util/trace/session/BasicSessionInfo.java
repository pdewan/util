package util.trace.session;

import util.trace.TraceableInfo;

public class BasicSessionInfo extends ProcessInfo {
	String userName;
	String applicationName;
	public BasicSessionInfo(String aMessage, 
			String aProcessName, 
			String aUserName, 
			String anApplicationName, 
			Object aFinder) {
		super(aMessage, aProcessName,  aFinder);
		userName = aUserName;
		applicationName = anApplicationName;
	}
	public String getUserName() {
		return userName;
	}
	public String getApplicationName() {
		return applicationName;
	}

	public static String toString (String aProcessName, String aUserName, String anApplicationName) {
		return toString(aProcessName) + " User(" + aUserName+ ")" + ", " 
				+"App(" + anApplicationName + ")";

	}	

	
}
