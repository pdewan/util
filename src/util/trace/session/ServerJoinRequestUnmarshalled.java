package util.trace.session;



public class ServerJoinRequestUnmarshalled extends AddressedMessageInfo{

	public ServerJoinRequestUnmarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerJoinRequestUnmarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerJoinRequestUnmarshalled toTraceable(String aMessage) {
		return new ServerJoinRequestUnmarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerJoinRequestUnmarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerJoinRequestUnmarshalled retVal = new ServerJoinRequestUnmarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
