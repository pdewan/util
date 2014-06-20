package util.trace.session;



public class MessageRetrievedFromQueue extends AddressedMessageInfo{
	String queueName;
	public MessageRetrievedFromQueue(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, String aQueueName,  Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public MessageRetrievedFromQueue(String aMessage, AddressedMessageInfo aSuperClassInfo,  String aQueueName) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessageRetrievedFromQueue toTraceable(String aMessage) {
		return new MessageRetrievedFromQueue(aMessage, AddressedMessageInfo.toTraceable(aMessage), ThreadCreated.getName(aMessage));
	}
	
	public static String toString(String aProcessName, Object aDataItem, String aSourceOrDestination, String aQueueName) {
		return toString(aProcessName, aDataItem, aSourceOrDestination) + " " + ThreadCreated.NAME + "(" + aQueueName + ")";
	}

	
	public static MessageRetrievedFromQueue newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, String aQueueName, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, aQueueName);
		MessageRetrievedFromQueue retVal = new MessageRetrievedFromQueue(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aQueueName, aFinder);
		retVal.announce();
		return retVal;
	}

}
