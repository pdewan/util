package util.trace.session;



public class MessageGivenToFilter extends AddressedMessageInfo{
	public MessageGivenToFilter(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public MessageGivenToFilter(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessageGivenToFilter toTraceable(String aMessage) {
		return new MessageGivenToFilter(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static String toString(String aProcessName, Object aDataItem, String aSourceOrDestination, String aQueueName) {
		return toString(aProcessName, aDataItem, aSourceOrDestination) + " " + ThreadCreated.NAME + "(" + aQueueName + ")";
	}

	
	public static MessageGivenToFilter newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination,  Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageGivenToFilter retVal = new MessageGivenToFilter(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
