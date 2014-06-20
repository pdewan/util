package util.trace.session;


public class ServerClientJoined extends SessionInfo {	
	public ServerClientJoined(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	
	public ServerClientJoined(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ServerClientJoined toTraceable(String aMessage) {
		return new ServerClientJoined(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
		
	public static ServerClientJoined newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ServerClientJoined retVal = new ServerClientJoined(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
