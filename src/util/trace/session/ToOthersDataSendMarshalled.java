package util.trace.session;



public class ToOthersDataSendMarshalled extends AddressedMessageInfo{

	public ToOthersDataSendMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ToOthersDataSendMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ToOthersDataSendMarshalled toTraceable(String aMessage) {
		return new ToOthersDataSendMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ToOthersDataSendMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ToOthersDataSendMarshalled retVal = new ToOthersDataSendMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
