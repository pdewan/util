package util.trace.session;


public class ClientJoinNotificationDistributedToListeners extends SessionInfo {	
	public ClientJoinNotificationDistributedToListeners(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	public ClientJoinNotificationDistributedToListeners(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientJoinNotificationDistributedToListeners toTraceable(String aMessage) {
		return new ClientJoinNotificationDistributedToListeners(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
//	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
//		return  toString(aProcessName, aUserName, anApplicationName, aSessionName);
//	}	
	public static ClientJoinNotificationDistributedToListeners newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientJoinNotificationDistributedToListeners retVal = new ClientJoinNotificationDistributedToListeners(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
