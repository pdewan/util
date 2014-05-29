package util.trace.session;



public class ReceivedMessageDelayedNew extends DelayedMessageInfo{

	public ReceivedMessageDelayedNew(String aMessage, String aProcessName, Object aDataItem, long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aDelay,  aFinder);
	}
	
	public static ReceivedMessageDelayedNew newCase(String aProcessName,
			Object aDataItem, long aDelay, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		ReceivedMessageDelayedNew retVal = new ReceivedMessageDelayedNew(aMessage, aProcessName, aDataItem, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
