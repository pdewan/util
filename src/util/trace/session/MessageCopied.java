package util.trace.session;



public class MessageCopied extends AddressedMessageInfo{

	public MessageCopied(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	public MessageCopied(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessageCopied toTraceable(String aMessage) {
		return new MessageCopied(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	public static MessageCopied newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageCopied retVal = new MessageCopied(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
