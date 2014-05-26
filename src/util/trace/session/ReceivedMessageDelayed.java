package util.trace.session;



public class ReceivedMessageDelayed extends DelayedMessageInfo{

	public ReceivedMessageDelayed(String aMessage, Object aDataItem, long aDelay, Object aFinder) {
		super(aMessage, aDataItem, aDelay, aFinder);
	}
	
	public static ReceivedMessageDelayed newCase(
			Object aDataItem, long aDelay, Object aFinder) {			
		String aMessage = toString(aDataItem, aDelay);
		ReceivedMessageDelayed retVal = new ReceivedMessageDelayed(aMessage, aDataItem, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
