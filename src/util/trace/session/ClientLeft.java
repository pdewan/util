package util.trace.session;


public class ClientLeft extends SessionInfo {	
	public ClientLeft(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	
	public ClientLeft(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientLeft toTraceable(String aMessage) {
		return new ClientLeft(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
		
	public static ClientLeft newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientLeft retVal = new ClientLeft(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
