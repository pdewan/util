package util.trace.session;


public class ClientJoinNotificationReceived extends SessionInfo {	
	public ClientJoinNotificationReceived(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	public ClientJoinNotificationReceived(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientJoinNotificationReceived toTraceable(String aMessage) {
		return new ClientJoinNotificationReceived(aMessage, SessionInfo.toTraceable(aMessage));
	}
//	
//	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
//		return  toString(aProcessName, aUserName, anApplicationName, aSessionName);
//	}	
	public static ClientJoinNotificationReceived newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientJoinNotificationReceived retVal = new ClientJoinNotificationReceived(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
