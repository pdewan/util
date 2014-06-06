package util.trace.session;


public class ClientJoinedNotificationReceived extends SessionInfo {	
	public ClientJoinedNotificationReceived(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	public ClientJoinedNotificationReceived(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientJoinedNotificationReceived toTraceable(String aMessage) {
		return new ClientJoinedNotificationReceived(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
		return  toString(aProcessName, aUserName, anApplicationName, aSessionName);
	}	
	public static ClientJoinedNotificationReceived newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientJoinedNotificationReceived retVal = new ClientJoinedNotificationReceived(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
