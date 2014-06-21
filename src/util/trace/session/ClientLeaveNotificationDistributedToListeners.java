package util.trace.session;


public class ClientLeaveNotificationDistributedToListeners extends SessionInfo {	
	public ClientLeaveNotificationDistributedToListeners(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	public ClientLeaveNotificationDistributedToListeners(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientLeaveNotificationDistributedToListeners toTraceable(String aMessage) {
		return new ClientLeaveNotificationDistributedToListeners(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
//	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
//		return  toString(aProcessName, aUserName, anApplicationName, aSessionName);
//	}	
	public static ClientLeaveNotificationDistributedToListeners newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientLeaveNotificationDistributedToListeners retVal = new ClientLeaveNotificationDistributedToListeners(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
