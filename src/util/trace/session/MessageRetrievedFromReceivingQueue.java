package util.trace.session;



public class MessageRetrievedFromReceivingQueue extends MessageInfo{

	public MessageRetrievedFromReceivingQueue(String aMessage, String aProcessName, Object aDataItem, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aFinder);
	}
	
	public static MessageRetrievedFromReceivingQueue newCase(String aProcessName,
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		MessageRetrievedFromReceivingQueue retVal = new MessageRetrievedFromReceivingQueue(aMessage, aProcessName, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
