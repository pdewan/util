package util.trace.session;


public class ClientJoinFinished extends SessionInfo {	
	public ClientJoinFinished(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	
	public ClientJoinFinished(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientJoinFinished toTraceable(String aMessage) {
		return new ClientJoinFinished(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
		
	public static ClientJoinFinished newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientJoinFinished retVal = new ClientJoinFinished(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
