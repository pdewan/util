package util.trace.session;


public class SessionJoinInformationUpdated extends SessionInfo {	
	public SessionJoinInformationUpdated(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	public SessionJoinInformationUpdated(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static SessionJoinInformationUpdated toTraceable(String aMessage) {
		return new SessionJoinInformationUpdated(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
//	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
//		return  toString(aProcessName, aUserName, anApplicationName, aSessionName);
//	}	
	public static SessionJoinInformationUpdated newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		SessionJoinInformationUpdated retVal = new SessionJoinInformationUpdated(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
