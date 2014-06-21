package util.trace.session;



public class ServerJoinNotificationDeliveredToListeners extends AddressedMessageInfo{

	public ServerJoinNotificationDeliveredToListeners(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerJoinNotificationDeliveredToListeners(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerJoinNotificationDeliveredToListeners toTraceable(String aMessage) {
		return new ServerJoinNotificationDeliveredToListeners(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerJoinNotificationDeliveredToListeners newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerJoinNotificationDeliveredToListeners retVal = new ServerJoinNotificationDeliveredToListeners(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
