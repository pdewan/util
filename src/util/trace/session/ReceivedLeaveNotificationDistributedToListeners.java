package util.trace.session;



public class ReceivedLeaveNotificationDistributedToListeners extends AddressedMessageInfo{

	public ReceivedLeaveNotificationDistributedToListeners(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public static ReceivedLeaveNotificationDistributedToListeners newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ReceivedLeaveNotificationDistributedToListeners retVal = new ReceivedLeaveNotificationDistributedToListeners(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
