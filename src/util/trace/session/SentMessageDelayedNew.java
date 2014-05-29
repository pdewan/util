package util.trace.session;



public class SentMessageDelayedNew extends DelayedMessageInfo{

	public SentMessageDelayedNew(String aMessage, String aProcessName, Object aDataItem, long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aDelay,  aFinder);
	}
	
	public static SentMessageDelayedNew newCase(String aProcessName,
			Object aDataItem, long aDelay, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		SentMessageDelayedNew retVal = new SentMessageDelayedNew(aMessage, aProcessName, aDataItem, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
