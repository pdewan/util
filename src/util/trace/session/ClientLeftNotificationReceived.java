package util.trace.session;


public class ClientLeftNotificationReceived extends SessionInfo {

	public ClientLeftNotificationReceived(String aMessage, String aUserName, String anApplicationName,  Object aFinder) {
		super(aMessage, aUserName, anApplicationName, aFinder);
		
	}
	
	public static ClientLeftNotificationReceived newCase (String aUserName, String anApplicationName, Object aFinder) {
		String aMessage = toString (aUserName, anApplicationName);
		ClientLeftNotificationReceived retVal = new ClientLeftNotificationReceived(aMessage, aUserName, anApplicationName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
