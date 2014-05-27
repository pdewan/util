package util.trace.session;


public class ClientJoined extends SessionInfo {
	
	String sessionName;
	public ClientJoined(String aMessage, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aUserName, anApplicationName, aFinder);
		
		sessionName = aSessionName;
	}
//	public String getUserName() {
//		return userName;
//	}
//	public String getApplicationName() {
//		return applicationName;
//	}
	public String getSessionName() {
		return sessionName;
	}
	public static String toString (String aUserName, String anApplicationName, String aSessionName) {
		return SessionInfo.toString(aUserName, anApplicationName)
			+ "," + aSessionName;
	}	
	public static ClientJoined newCase (String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aUserName, anApplicationName, aSessionName);
		ClientJoined retVal = new ClientJoined(aMessage, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
