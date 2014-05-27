package util.trace.session;

import util.trace.TraceableInfo;

public class SessionInfo extends TraceableInfo {
	String userName;
//	String applicationName;
//	String sessionName;
	public SessionInfo(String aMessage, 
			String aUserName, 
			String anApplicationName, 
//			String aSessionName, 
			Object aFinder) {
		super(aMessage, aFinder);
		userName = aUserName;
//		applicationName = anApplicationName;
//		sessionName = aSessionName;
	}
	public String getUserName() {
		return userName;
	}
//	public String getApplicationName() {
//		return applicationName;
//	}
//	public String getSessionName() {
//		return applicationName;
//	}
	public static String toString (String aUserName, String anApplicationName) {
		return 
//				"("
			 aUserName 
//			((anApplicationName == null || anApplicationName.isEmpty())?"":", anApplicationName")
			+ "," + anApplicationName
//			+ "," + aSessionName
			;
//				+ ")";
	}	
//	
	
}
