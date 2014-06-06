package util.trace.session;



public class ReceivedMessageDelayed extends DelayedMessageInfo{

	public ReceivedMessageDelayed(String aMessage, String aProcessName, Object aDataItem, String aSourceOrDestination,long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay,  aFinder);
	}
	
	public ReceivedMessageDelayed(String aMessage, DelayedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static ReceivedMessageDelayed toTraceable(String aMessage) {
		return new ReceivedMessageDelayed(aMessage, DelayedMessageInfo.toTraceable(aMessage));
	}
	
	
	public static ReceivedMessageDelayed newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, long aDelay, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, aDelay);
		ReceivedMessageDelayed retVal = new ReceivedMessageDelayed(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
