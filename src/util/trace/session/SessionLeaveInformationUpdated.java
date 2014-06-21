package util.trace.session;


public class SessionLeaveInformationUpdated extends SessionInfo {	
	public SessionLeaveInformationUpdated(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	public SessionLeaveInformationUpdated(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static SessionLeaveInformationUpdated toTraceable(String aMessage) {
		return new SessionLeaveInformationUpdated(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
//	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
//		return  toString(aProcessName, aUserName, anApplicationName, aSessionName);
//	}	
	public static SessionLeaveInformationUpdated newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		SessionLeaveInformationUpdated retVal = new SessionLeaveInformationUpdated(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
