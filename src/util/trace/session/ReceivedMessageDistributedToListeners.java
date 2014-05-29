package util.trace.session;



public class ReceivedMessageDistributedToListeners extends MessageInfo{

	public ReceivedMessageDistributedToListeners(String aMessage, String aProcessName, Object aDataItem, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aFinder);
	}
	
	public static ReceivedMessageDistributedToListeners newCase(String aProcessName,
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		ReceivedMessageDistributedToListeners retVal = new ReceivedMessageDistributedToListeners(aMessage, aProcessName, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
