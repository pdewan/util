package util.trace.session;



public class ServerRemoteJoinNotificationSent extends AddressedMessageInfo{

	public ServerRemoteJoinNotificationSent(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerRemoteJoinNotificationSent(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerRemoteJoinNotificationSent toTraceable(String aMessage) {
		return new ServerRemoteJoinNotificationSent(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerRemoteJoinNotificationSent newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerRemoteJoinNotificationSent retVal = new ServerRemoteJoinNotificationSent(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
