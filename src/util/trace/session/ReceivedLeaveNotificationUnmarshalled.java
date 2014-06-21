package util.trace.session;



public class ReceivedLeaveNotificationUnmarshalled extends AddressedMessageInfo{

	public ReceivedLeaveNotificationUnmarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	public ReceivedLeaveNotificationUnmarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ReceivedLeaveNotificationUnmarshalled toTraceable(String aMessage) {
		return new ReceivedLeaveNotificationUnmarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ReceivedLeaveNotificationUnmarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ReceivedLeaveNotificationUnmarshalled retVal = new ReceivedLeaveNotificationUnmarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
