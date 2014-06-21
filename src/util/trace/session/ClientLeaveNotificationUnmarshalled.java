package util.trace.session;



public class ClientLeaveNotificationUnmarshalled extends AddressedMessageInfo{

	public ClientLeaveNotificationUnmarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ClientLeaveNotificationUnmarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ClientLeaveNotificationUnmarshalled toTraceable(String aMessage) {
		return new ClientLeaveNotificationUnmarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ClientLeaveNotificationUnmarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ClientLeaveNotificationUnmarshalled retVal = new ClientLeaveNotificationUnmarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
