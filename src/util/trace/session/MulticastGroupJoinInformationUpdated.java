package util.trace.session;


public class MulticastGroupJoinInformationUpdated extends SessionInfo {	
	public MulticastGroupJoinInformationUpdated(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	public MulticastGroupJoinInformationUpdated(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static MulticastGroupJoinInformationUpdated toTraceable(String aMessage) {
		return new MulticastGroupJoinInformationUpdated(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
//	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
//		return  toString(aProcessName, aUserName, anApplicationName, aSessionName);
//	}	
	public static MulticastGroupJoinInformationUpdated newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		MulticastGroupJoinInformationUpdated retVal = new MulticastGroupJoinInformationUpdated(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
