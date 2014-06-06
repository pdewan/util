package util.trace.session;



public class ReceivedLeaveNotificationDistributedToListeners extends AddressedMessageInfo{

	public ReceivedLeaveNotificationDistributedToListeners(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	public ReceivedLeaveNotificationDistributedToListeners(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ReceivedLeaveNotificationDistributedToListeners toTraceable(String aMessage) {
		return new ReceivedLeaveNotificationDistributedToListeners(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ReceivedLeaveNotificationDistributedToListeners newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ReceivedLeaveNotificationDistributedToListeners retVal = new ReceivedLeaveNotificationDistributedToListeners(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
