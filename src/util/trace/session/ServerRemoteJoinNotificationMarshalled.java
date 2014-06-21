package util.trace.session;



public class ServerRemoteJoinNotificationMarshalled extends AddressedMessageInfo{

	public ServerRemoteJoinNotificationMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerRemoteJoinNotificationMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerRemoteJoinNotificationMarshalled toTraceable(String aMessage) {
		return new ServerRemoteJoinNotificationMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerRemoteJoinNotificationMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerRemoteJoinNotificationMarshalled retVal = new ServerRemoteJoinNotificationMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
