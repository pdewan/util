package util.trace.session;



public class MessagePutInQueue extends AddressedMessageInfo{
	String queueName;
	public MessagePutInQueue(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, String aQueueName,  Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public MessagePutInQueue(String aMessage, AddressedMessageInfo aSuperClassInfo,  String aQueueName) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessagePutInQueue toTraceable(String aMessage) {
		return new MessagePutInQueue(aMessage, AddressedMessageInfo.toTraceable(aMessage), ThreadCreated.getName(aMessage));
	}
	
	public static String toString(String aProcessName, Object aDataItem, String aSourceOrDestination, String aQueueName) {
		return toString(aProcessName, aDataItem, aSourceOrDestination) + " " + ThreadCreated.NAME + "(" + aQueueName + ")";
	}

	
	public static MessagePutInQueue newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, String aQueueName, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, aQueueName);
		MessagePutInQueue retVal = new MessagePutInQueue(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aQueueName, aFinder);
		retVal.announce();
		return retVal;
	}

}
