package util.trace.session;


public class ClientJoinInitiated extends SessionInfo {	
	public ClientJoinInitiated(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	
	public ClientJoinInitiated(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientJoinInitiated toTraceable(String aMessage) {
		return new ClientJoinInitiated(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
		
	public static ClientJoinInitiated newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientJoinInitiated retVal = new ClientJoinInitiated(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
