package util.trace.session;



public class MessageReceived extends MessageInfo{

	public MessageReceived(String aMessage, Object aDataItem, Object aFinder) {
		super(aMessage, aDataItem, aFinder);
	}
	
	public static MessageReceived newCase(
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aDataItem);
		MessageReceived retVal = new MessageReceived(aMessage, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
