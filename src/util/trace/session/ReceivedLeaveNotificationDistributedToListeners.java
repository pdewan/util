package util.trace.session;



public class ReceivedLeaveNotificationDistributedToListeners extends MessageInfo{

	public ReceivedLeaveNotificationDistributedToListeners(String aMessage, String aProcessName, Object aDataItem, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aFinder);
	}
	
	public static ReceivedLeaveNotificationDistributedToListeners newCase(String aProcessName,
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		ReceivedLeaveNotificationDistributedToListeners retVal = new ReceivedLeaveNotificationDistributedToListeners(aMessage, aProcessName, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
