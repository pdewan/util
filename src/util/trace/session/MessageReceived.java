package util.trace.session;



public class MessageReceived extends AddressedMessageInfo{

	public MessageReceived(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public MessageReceived(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessageReceived toTraceable(String aMessage) {
		return new MessageReceived(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static MessageReceived newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageReceived retVal = new MessageReceived(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
