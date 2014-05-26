package util.trace.session;



public class SentMessageDelayed extends DelayedMessageInfo{

	public SentMessageDelayed(String aMessage, Object aDataItem, long aDelay, Object aFinder) {
		super(aMessage, aDataItem, aDelay, aFinder);
	}
	
	public static SentMessageDelayed newCase(
			Object aDataItem, long aDelay, Object aFinder) {			
		String aMessage = toString(aDataItem, aDelay);
		SentMessageDelayed retVal = new SentMessageDelayed(aMessage, aDataItem, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
