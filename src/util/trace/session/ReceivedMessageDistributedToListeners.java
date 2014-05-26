package util.trace.session;



public class ReceivedMessageDistributedToListeners extends MessageInfo{

	public ReceivedMessageDistributedToListeners(String aMessage, Object aDataItem,
			/*String aSourceOrDestination,*/  Object aFinder) {
		super(aMessage, aDataItem, /*aSourceOrDestination,*/  aFinder);
	}
	
	public static ReceivedMessageDistributedToListeners newCase(
			Object aDataItem,
			/*String aSourceOrDestination,*/  Object aFinder) {			
		String aMessage = toString(aDataItem/*, aSourceOrDestination*/);
		ReceivedMessageDistributedToListeners retVal = new ReceivedMessageDistributedToListeners(aMessage, aDataItem/*, aSourceOrDestination*/, aFinder);
		retVal.announce();
		return retVal;
	}

}
