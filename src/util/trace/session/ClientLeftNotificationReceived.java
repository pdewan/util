package util.trace.session;


public class ClientLeftNotificationReceived extends BasicSessionInfo {	
	public ClientLeftNotificationReceived(String aMessage, String aProcessName, String aUserName, String anApplicationName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aFinder);		
	}
	public ClientLeftNotificationReceived(String aMessage, BasicSessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static ClientLeftNotificationReceived toTraceable(String aMessage) {
		return new ClientLeftNotificationReceived(aMessage, BasicSessionInfo.toTraceable(aMessage));
	}
	
	public static ClientLeftNotificationReceived newCase (String aProcessName, String aUserName, String anApplicationName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName);
		ClientLeftNotificationReceived retVal = new ClientLeftNotificationReceived(aMessage, aProcessName, aUserName, anApplicationName, aFinder);
		retVal.announce();
		return retVal;
	}
	
}
