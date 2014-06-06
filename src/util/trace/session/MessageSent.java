package util.trace.session;



public class MessageSent extends AddressedMessageInfo{

	public MessageSent(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	public MessageSent(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessageSent toTraceable(String aMessage) {
		return new MessageSent(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static MessageSent newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageSent retVal = new MessageSent(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
