package util.trace.session;

import util.trace.Traceable;
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
	public BasicSessionInfo(String aMessage,
			String aUserName, 
			String anApplicationName, 
			ProcessInfo aProcessInfo) {
		super(aMessage, aProcessInfo);
		userName = aUserName;
		applicationName = anApplicationName;
	}
	public String getUserName() {
		return userName;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public static String getUserName(String aMessage) {
		return getArgs(aMessage, USER).get(0);
	}
	public static String getAppName(String aMessage) {
		return getArgs(aMessage, APPLICATION).get(0);
	}
	public static BasicSessionInfo toTraceable(String aMessage) {
		ProcessInfo aProcessInfo = ProcessInfo.toTraceable(aMessage);
		return new BasicSessionInfo(aMessage, getUserName(aMessage), 
				getAppName(aMessage), aProcessInfo);
		
	}
	public static final String USER = "User";
	public static final String APPLICATION = "App";
	
	public static String toString (String aProcessName, String aUserName, String anApplicationName) {
		return toString(aProcessName) + 
				" " + USER + "(" +
//				" User(" + 
				aUserName+ ")" + ", " 
				+ APPLICATION + "(" +
//				+"App(" + 
				anApplicationName + ")";

	}	

	
}
