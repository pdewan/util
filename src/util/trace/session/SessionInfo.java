package util.trace.session;


public class SessionInfo extends BasicSessionInfo {	
	String sessionName;
	public SessionInfo(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aFinder);		
		sessionName = aSessionName;
	}
	public SessionInfo(String aMessage, String aSessionName, BasicSessionInfo aBasicSessionInfo) {
		super(aMessage, aBasicSessionInfo);		
		sessionName = aSessionName;
	}
	public SessionInfo(String aMessage, SessionInfo aSessionInfo) {
		this(aMessage, aSessionInfo.getSessionName(), aSessionInfo);		
	}
	public static String getSessionName(String aMessage) {
		return getArgs(aMessage, SESSION).get(0);
	}
	public static SessionInfo toTraceable(String aMessage) {
		BasicSessionInfo aBasicSessionInfo = BasicSessionInfo.toTraceable(aMessage);
		return new SessionInfo(aMessage, getSessionName(aMessage), aBasicSessionInfo);
		
	}
	public String getSessionName() {
		return sessionName;
	}
	public static final String SESSION = "Session";
	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
		return  toString(aProcessName, aUserName, anApplicationName)
			+ ", " + 
				SESSION + 
			"(" + aSessionName + ")";
	}	
	
	
}
