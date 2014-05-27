package util.trace.session;



public class SentMessageDelayed extends DelayedMessageInfo{
	String destination;
	public SentMessageDelayed(String aMessage, Object aDataItem, long aDelay, Object aFinder) {
		super(aMessage, aDataItem, aDelay, aFinder);
	}
	public String getDestination() {
		return destination;
	}
	public static String toString(String aDestination, long aDelay, Object aDataItem) {
		return  DelayedMessageInfo.toString(aDataItem, aDelay) + "->"  + aDestination;
	}
	public static SentMessageDelayed newCase(String aDestination,
			Object aDataItem, long aDelay, Object aFinder) {			
		String aMessage = toString(aDestination, aDelay, aDataItem);
		SentMessageDelayed retVal = new SentMessageDelayed(aMessage, aDataItem, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
