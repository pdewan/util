package util.trace.session;



public class ClientLeaveNotificationMarshalled extends AddressedMessageInfo{

	public ClientLeaveNotificationMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ClientLeaveNotificationMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ClientLeaveNotificationMarshalled toTraceable(String aMessage) {
		return new ClientLeaveNotificationMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ClientLeaveNotificationMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ClientLeaveNotificationMarshalled retVal = new ClientLeaveNotificationMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
