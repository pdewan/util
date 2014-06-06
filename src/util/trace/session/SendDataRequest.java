package util.trace.session;



public class SendDataRequest extends AddressedSentMessageInfo{

	public SendDataRequest(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, anIsRelayed, aFinder);
	}
	
	
	
	public SendDataRequest(String aMessage, AddressedSentMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static SendDataRequest toTraceable(String aMessage) {
		return new SendDataRequest(aMessage, AddressedSentMessageInfo.toTraceable(aMessage));
	}
	
	
	
	
	
	public static SendDataRequest newCase(String aProcessName,
			Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, anIsRelayed);
		SendDataRequest retVal = new SendDataRequest(aMessage, aProcessName, aDataItem, aSourceOrDestination, anIsRelayed, aFinder);
		retVal.announce();
		return retVal;
	}

}
