package util.trace.session;



public class ReceivedJoinNotificationDistributedToListeners extends MessageInfo{

	public ReceivedJoinNotificationDistributedToListeners(String aMessage, String aProcessName, Object aDataItem, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aFinder);
	}
	
	public static ReceivedJoinNotificationDistributedToListeners newCase(String aProcessName,
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aProcessName);
		ReceivedJoinNotificationDistributedToListeners retVal = new ReceivedJoinNotificationDistributedToListeners(aMessage, aProcessName, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
