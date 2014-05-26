package util.trace.session;



public class MessageRetrievedFromReceivingQueue extends MessageInfo{

	public MessageRetrievedFromReceivingQueue(String aMessage, Object aDataItem, Object aFinder) {
		super(aMessage, aDataItem,  aFinder);
	}
	
	public static MessageRetrievedFromReceivingQueue newCase(
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aDataItem);
		MessageRetrievedFromReceivingQueue retVal = new MessageRetrievedFromReceivingQueue(aMessage, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
