package util.trace.session;



public class ToUserDataSendMarshalled extends AddressedMessageInfo{

	public ToUserDataSendMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ToUserDataSendMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ToUserDataSendMarshalled toTraceable(String aMessage) {
		return new ToUserDataSendMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static ToUserDataSendMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ToUserDataSendMarshalled retVal = new ToUserDataSendMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
