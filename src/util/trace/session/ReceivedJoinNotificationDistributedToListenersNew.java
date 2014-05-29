package util.trace.session;



public class ReceivedJoinNotificationDistributedToListenersNew extends AddressedMessageInfo{

	public ReceivedJoinNotificationDistributedToListenersNew(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public static ReceivedJoinNotificationDistributedToListenersNew newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ReceivedJoinNotificationDistributedToListenersNew retVal = new ReceivedJoinNotificationDistributedToListenersNew(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
