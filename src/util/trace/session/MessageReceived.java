package util.trace.session;



public class MessageReceived extends AddressedMessageInfo{

	public MessageReceived(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public static MessageReceived newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageReceived retVal = new MessageReceived(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
