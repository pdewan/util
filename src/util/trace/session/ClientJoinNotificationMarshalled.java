package util.trace.session;



public class ClientJoinNotificationMarshalled extends AddressedMessageInfo{

	public ClientJoinNotificationMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ClientJoinNotificationMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ClientJoinNotificationMarshalled toTraceable(String aMessage) {
		return new ClientJoinNotificationMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ClientJoinNotificationMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ClientJoinNotificationMarshalled retVal = new ClientJoinNotificationMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
