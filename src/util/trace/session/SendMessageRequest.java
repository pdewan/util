package util.trace.session;



public class SendMessageRequest extends AddressedSentMessageInfo{

	public SendMessageRequest(String aMessage, Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {
		super(aMessage, aDataItem, aSourceOrDestination, anIsRelayed, aFinder);
	}
	
	public static SendMessageRequest newCase(
			Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {			
		String aMessage = toString(aDataItem, aSourceOrDestination, anIsRelayed);
		SendMessageRequest retVal = new SendMessageRequest(aMessage, aDataItem, aSourceOrDestination, anIsRelayed, aFinder);
		retVal.announce();
		return retVal;
	}

}
