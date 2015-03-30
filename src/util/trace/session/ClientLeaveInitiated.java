package util.trace.session;


public class ClientLeaveInitiated extends SessionInfo {	
	public ClientLeaveInitiated(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	
	public ClientLeaveInitiated(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientLeaveInitiated toTraceable(String aMessage) {
		return new ClientLeaveInitiated(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
		
	public static ClientLeaveInitiated newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientLeaveInitiated retVal = new ClientLeaveInitiated(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
