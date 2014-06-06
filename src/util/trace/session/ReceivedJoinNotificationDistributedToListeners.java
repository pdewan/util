package util.trace.session;



public class ReceivedJoinNotificationDistributedToListeners extends AddressedMessageInfo{

	public ReceivedJoinNotificationDistributedToListeners(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ReceivedJoinNotificationDistributedToListeners(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ReceivedJoinNotificationDistributedToListeners toTraceable(String aMessage) {
		return new ReceivedJoinNotificationDistributedToListeners(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ReceivedJoinNotificationDistributedToListeners newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ReceivedJoinNotificationDistributedToListeners retVal = new ReceivedJoinNotificationDistributedToListeners(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
