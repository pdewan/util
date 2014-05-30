package util.trace.session;



public class MessageInSendingQueue extends MessageInfo{

	public MessageInSendingQueue(String aMessage, String aProcessName, Object aDataItem, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, /*anAddress,*/  aFinder);
	}
	
	public static MessageInSendingQueue newCase(String aProcessName,
			Object aDataItem, /*String anAddress,*/ Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		MessageInSendingQueue retVal = new MessageInSendingQueue(aMessage, aProcessName, aDataItem,  aFinder);
		retVal.announce();
		return retVal;
	}

}
