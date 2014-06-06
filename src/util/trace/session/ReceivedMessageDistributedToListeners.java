package util.trace.session;



public class ReceivedMessageDistributedToListeners extends AddressedMessageInfo{

	public ReceivedMessageDistributedToListeners(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public ReceivedMessageDistributedToListeners(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ReceivedMessageDistributedToListeners toTraceable(String aMessage) {
		return new ReceivedMessageDistributedToListeners(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	
	public static ReceivedMessageDistributedToListeners newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ReceivedMessageDistributedToListeners retVal = new ReceivedMessageDistributedToListeners(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
