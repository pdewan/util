package util.trace.session;



public class MessageInSendingQueue extends MessageInfo{

	public MessageInSendingQueue(String aMessage, Object aDataItem, Object aFinder) {
		super(aMessage, aDataItem,  aFinder);
	}
	
	public static MessageInSendingQueue newCase(
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aDataItem);
		MessageInSendingQueue retVal = new MessageInSendingQueue(aMessage, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
