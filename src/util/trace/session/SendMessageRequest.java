package util.trace.session;



public class SendMessageRequest extends AddressedSentMessageInfo{

	public SendMessageRequest(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, anIsRelayed, aFinder);
	}
	
	public static SendMessageRequest newCase(String aProcessName,
			Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {			
		String aMessage = toString(aDataItem, aSourceOrDestination, anIsRelayed);
		SendMessageRequest retVal = new SendMessageRequest(aMessage, aProcessName, aDataItem, aSourceOrDestination, anIsRelayed, aFinder);
		retVal.announce();
		return retVal;
	}

}
