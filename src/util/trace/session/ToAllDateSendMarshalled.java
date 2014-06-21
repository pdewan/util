package util.trace.session;



public class ToAllDateSendMarshalled extends AddressedMessageInfo{

	public ToAllDateSendMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ToAllDateSendMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ToAllDateSendMarshalled toTraceable(String aMessage) {
		return new ToAllDateSendMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ToAllDateSendMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ToAllDateSendMarshalled retVal = new ToAllDateSendMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
