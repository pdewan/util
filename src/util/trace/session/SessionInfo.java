package util.trace.session;


public class SessionInfo extends BasicSessionInfo {	
	String sessionName;
	public SessionInfo(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aFinder);		
		sessionName = aSessionName;
	}
	public String getSessionName() {
		return sessionName;
	}
	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
		return  toString(aProcessName, aUserName, anApplicationName)
			+ ", " + "Session(" + aSessionName + ")";
	}	
	
	
}
