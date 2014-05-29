package util.trace.session;



public class ReceivedMessageDelayed extends DelayedMessageInfo{

	public ReceivedMessageDelayed(String aMessage, String aProcessName, Object aDataItem, String aSourceOrDestination,long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay,  aFinder);
	}
	
	public static ReceivedMessageDelayed newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, long aDelay, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		ReceivedMessageDelayed retVal = new ReceivedMessageDelayed(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
