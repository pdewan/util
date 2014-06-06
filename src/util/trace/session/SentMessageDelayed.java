package util.trace.session;



public class SentMessageDelayed extends DelayedMessageInfo{

	public SentMessageDelayed(String aMessage, String aProcessName, Object aDataItem, String aSourceOrDestination,long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay,  aFinder);
	}
	
	public SentMessageDelayed(String aMessage, DelayedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static SentMessageDelayed toTraceable(String aMessage) {
		return new SentMessageDelayed(aMessage, DelayedMessageInfo.toTraceable(aMessage));
	}
	
	
	
	public static SentMessageDelayed newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, long aDelay, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, aDelay);
		SentMessageDelayed retVal = new SentMessageDelayed(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
