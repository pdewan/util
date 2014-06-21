package util.trace.session;



public class ServerLeaveNotificationDeliveredToLocalObservers extends AddressedMessageInfo{

	public ServerLeaveNotificationDeliveredToLocalObservers(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerLeaveNotificationDeliveredToLocalObservers(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerLeaveNotificationDeliveredToLocalObservers toTraceable(String aMessage) {
		return new ServerLeaveNotificationDeliveredToLocalObservers(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerLeaveNotificationDeliveredToLocalObservers newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerLeaveNotificationDeliveredToLocalObservers retVal = new ServerLeaveNotificationDeliveredToLocalObservers(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
