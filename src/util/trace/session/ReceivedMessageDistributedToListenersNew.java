package util.trace.session;



public class ReceivedMessageDistributedToListenersNew extends AddressedMessageInfo{

	public ReceivedMessageDistributedToListenersNew(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public static ReceivedMessageDistributedToListenersNew newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		ReceivedMessageDistributedToListenersNew retVal = new ReceivedMessageDistributedToListenersNew(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
