package util.trace.session;



public class ServerJoinRequestMarshalled extends AddressedMessageInfo{

	public ServerJoinRequestMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerJoinRequestMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerJoinRequestMarshalled toTraceable(String aMessage) {
		return new ServerJoinRequestMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerJoinRequestMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerJoinRequestMarshalled retVal = new ServerJoinRequestMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
