package util.trace.session;



public class ServerLeaveRequestMarshalled extends AddressedMessageInfo{

	public ServerLeaveRequestMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ServerLeaveRequestMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ServerLeaveRequestMarshalled toTraceable(String aMessage) {
		return new ServerLeaveRequestMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ServerLeaveRequestMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ServerLeaveRequestMarshalled retVal = new ServerLeaveRequestMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
