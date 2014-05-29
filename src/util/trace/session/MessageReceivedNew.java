package util.trace.session;



public class MessageReceivedNew extends MessageInfo{

	public MessageReceivedNew(String aMessage, String aProcessName, Object aDataItem, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aFinder);
	}
	
	public static MessageReceivedNew newCase(String aProcessName,
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		MessageReceivedNew retVal = new MessageReceivedNew(aMessage, aProcessName, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
