package util.trace.session;


public class ClientJoinedNotificationReceived extends BasicSessionInfo {	
	String sessionName;
	public ClientJoinedNotificationReceived(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aFinder);		
		sessionName = aSessionName;
	}
	public String getSessionName() {
		return sessionName;
	}
	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
		return  toString(aProcessName, aUserName, anApplicationName)
			+ "," + aSessionName;
	}	
	public static ClientJoinedNotificationReceived newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		ClientJoinedNotificationReceived retVal = new ClientJoinedNotificationReceived(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
