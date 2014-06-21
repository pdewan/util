package util.trace.session;



public class ServerRemoteLeaveNotificationMarshalled extends AddressedMessageInfo{

	public ServerRemoteLeaveNotificationMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerRemoteLeaveNotificationMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerRemoteLeaveNotificationMarshalled toTraceable(String aMessage) {
		return new ServerRemoteLeaveNotificationMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerRemoteLeaveNotificationMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerRemoteLeaveNotificationMarshalled retVal = new ServerRemoteLeaveNotificationMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
