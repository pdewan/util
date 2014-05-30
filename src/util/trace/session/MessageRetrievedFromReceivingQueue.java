package util.trace.session;



public class MessageRetrievedFromReceivingQueue extends AddressedMessageInfo{

	public MessageRetrievedFromReceivingQueue(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public static MessageRetrievedFromReceivingQueue newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageRetrievedFromReceivingQueue retVal = new MessageRetrievedFromReceivingQueue(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
