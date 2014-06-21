package util.trace.session;



public class MinimumDelaySet extends DelayedMessageInfo{

	public MinimumDelaySet(String aMessage, String aProcessName, Object aDataItem, String aSourceOrDestination,long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay,  aFinder);
	}
	
	public MinimumDelaySet(String aMessage, DelayedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MinimumDelaySet toTraceable(String aMessage) {
		return new MinimumDelaySet(aMessage, DelayedMessageInfo.toTraceable(aMessage));
	}
	
	
	
	public static MinimumDelaySet newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, long aDelay, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, aDelay);
		MinimumDelaySet retVal = new MinimumDelaySet(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
