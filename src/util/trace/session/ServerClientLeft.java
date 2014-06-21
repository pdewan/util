package util.trace.session;

//being deprecated, at least for a while

public class ServerClientLeft extends SessionInfo {	
	public ServerClientLeft(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	
	public ServerClientLeft(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ServerClientLeft toTraceable(String aMessage) {
		return new ServerClientLeft(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
		
	public static ServerClientLeft newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ServerClientLeft retVal = new ServerClientLeft(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
