package util.trace.session;


public class ClientLeaveNotificationReceived extends BasicSessionInfo {	
	public ClientLeaveNotificationReceived(String aMessage, String aProcessName, String aUserName, String anApplicationName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aFinder);		
	}
	public ClientLeaveNotificationReceived(String aMessage, BasicSessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientLeaveNotificationReceived toTraceable(String aMessage) {
		return new ClientLeaveNotificationReceived(aMessage, BasicSessionInfo.toTraceable(aMessage));
	}
	
	public static ClientLeaveNotificationReceived newCase (String aProcessName, String aUserName, String anApplicationName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName);
		ClientLeaveNotificationReceived retVal = new ClientLeaveNotificationReceived(aMessage, aProcessName, aUserName, anApplicationName, aFinder);
		retVal.announce();
		return retVal;
	}
	
}
