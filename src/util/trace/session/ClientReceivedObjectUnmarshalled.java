package util.trace.session;



public class ClientReceivedObjectUnmarshalled extends AddressedMessageInfo{

	public ClientReceivedObjectUnmarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	public ClientReceivedObjectUnmarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ClientReceivedObjectUnmarshalled toTraceable(String aMessage) {
		return new ClientReceivedObjectUnmarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ClientReceivedObjectUnmarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ClientReceivedObjectUnmarshalled retVal = new ClientReceivedObjectUnmarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
