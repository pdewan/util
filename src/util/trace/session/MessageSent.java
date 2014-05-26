package util.trace.session;



public class MessageSent extends MessageInfo{

	public MessageSent(String aMessage, Object aDataItem, Object aFinder) {
		super(aMessage, aDataItem, aFinder);
	}
	
	public static MessageSent newCase(
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aDataItem);
		MessageSent retVal = new MessageSent(aMessage, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
