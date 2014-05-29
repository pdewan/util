package util.trace.session;


public class ClientJoined extends SessionInfo {	
	public ClientJoined(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
		sessionName = aSessionName;
	}
	
		
	public static ClientJoined newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientJoined retVal = new ClientJoined(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
