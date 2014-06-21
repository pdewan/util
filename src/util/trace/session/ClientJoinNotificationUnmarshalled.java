package util.trace.session;



public class ClientJoinNotificationUnmarshalled extends AddressedMessageInfo{

	public ClientJoinNotificationUnmarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ClientJoinNotificationUnmarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ClientJoinNotificationUnmarshalled toTraceable(String aMessage) {
		return new ClientJoinNotificationUnmarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ClientJoinNotificationUnmarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ClientJoinNotificationUnmarshalled retVal = new ClientJoinNotificationUnmarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
