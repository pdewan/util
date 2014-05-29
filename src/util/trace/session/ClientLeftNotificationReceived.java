package util.trace.session;


public class ClientLeftNotificationReceived extends BasicSessionInfo {	
	String applicationName;
	public ClientLeftNotificationReceived(String aMessage, String aProcessName, String aUserName, String anApplicationName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aFinder);		
		applicationName = anApplicationName;
	}
	
	public static ClientLeftNotificationReceived newCase (String aProcessName, String aUserName, String anApplicationName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName);
		ClientLeftNotificationReceived retVal = new ClientLeftNotificationReceived(aMessage, aProcessName, aUserName, anApplicationName, aFinder);
		retVal.announce();
		return retVal;
	}
	
}
