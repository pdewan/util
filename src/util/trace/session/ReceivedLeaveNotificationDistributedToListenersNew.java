package util.trace.session;



public class ReceivedLeaveNotificationDistributedToListenersNew extends AddressedMessageInfo{

	public ReceivedLeaveNotificationDistributedToListenersNew(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public static ReceivedLeaveNotificationDistributedToListenersNew newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ReceivedLeaveNotificationDistributedToListenersNew retVal = new ReceivedLeaveNotificationDistributedToListenersNew(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
