package util.trace.session;


public class MulticastGroupLeaveInformationUpdated extends SessionInfo {	
	public MulticastGroupLeaveInformationUpdated(String aMessage, String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		super(aMessage, aProcessName, aUserName, anApplicationName, aSessionName, aFinder);		
	}
	public MulticastGroupLeaveInformationUpdated(String aMessage, SessionInfo aSessionInfo) {
		super(aMessage, aSessionInfo);
	}
	
	public static MulticastGroupLeaveInformationUpdated toTraceable(String aMessage) {
		return new MulticastGroupLeaveInformationUpdated(aMessage, SessionInfo.toTraceable(aMessage));
	}
	
//	public static String toString (String aProcessName, String aUserName, String anApplicationName, String aSessionName) {
//		return  toString(aProcessName, aUserName, anApplicationName, aSessionName);
//	}	
	public static MulticastGroupLeaveInformationUpdated newCase (String aProcessName, String aUserName, String anApplicationName, String aSessionName, Object aFinder) {
		String aMessage = toString (aProcessName, aUserName, anApplicationName, aSessionName);
		MulticastGroupLeaveInformationUpdated retVal = new MulticastGroupLeaveInformationUpdated(aMessage, aProcessName, aUserName, anApplicationName, aSessionName,  aFinder);
		retVal.announce();
		return retVal;
	}
	
}
