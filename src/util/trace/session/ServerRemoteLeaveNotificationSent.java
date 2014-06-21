package util.trace.session;



public class ServerRemoteLeaveNotificationSent extends AddressedMessageInfo{

	public ServerRemoteLeaveNotificationSent(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerRemoteLeaveNotificationSent(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerRemoteLeaveNotificationSent toTraceable(String aMessage) {
		return new ServerRemoteLeaveNotificationSent(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerRemoteLeaveNotificationSent newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerRemoteLeaveNotificationSent retVal = new ServerRemoteLeaveNotificationSent(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
