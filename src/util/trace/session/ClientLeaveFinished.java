package util.trace.session;


public class ClientLeaveFinished extends SessionInfo {	
	public ClientLeaveFinished(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	
	public ClientLeaveFinished(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientLeaveFinished toTraceable(String aMessage) {
		return new ClientLeaveFinished(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
		
	public static ClientLeaveFinished newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientLeaveFinished retVal = new ClientLeaveFinished(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
