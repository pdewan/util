package util.trace.session;



public class MessageSent extends MessageInfo{
	String destination;
	public MessageSent(String aDestination, String aMessage, Object aDataItem, Object aFinder) {
		super(aMessage, aDataItem, aFinder);
	}
	
	public String getDestination() {
		return destination;
	}
	public static String toString(String aDestination, Object aDataItem) {
		return  MessageInfo.toString(aDataItem) + "->"  + aDestination;
	}
	public static MessageSent newCase(String aDestination,
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aDestination, aDataItem);
		MessageSent retVal = new MessageSent(aDestination, aMessage, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
