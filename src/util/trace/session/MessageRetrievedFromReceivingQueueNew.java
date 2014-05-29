package util.trace.session;



public class MessageRetrievedFromReceivingQueueNew extends AddressedMessageInfo{

	public MessageRetrievedFromReceivingQueueNew(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public static MessageRetrievedFromReceivingQueueNew newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageRetrievedFromReceivingQueueNew retVal = new MessageRetrievedFromReceivingQueueNew(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
