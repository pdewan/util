package util.trace.session;



public class DelayVariationSet extends DelayedMessageInfo{

	public DelayVariationSet(String aMessage, String aProcessName, Object aDataItem, String aSourceOrDestination,long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay,  aFinder);
	}
	
	public DelayVariationSet(String aMessage, DelayedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static DelayVariationSet toTraceable(String aMessage) {
		return new DelayVariationSet(aMessage, DelayedMessageInfo.toTraceable(aMessage));
	}
	
	
	
	public static DelayVariationSet newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, long aDelay, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, aDelay);
		DelayVariationSet retVal = new DelayVariationSet(aMessage, aProcessName, aDataItem, aSourceOrDestination, aDelay, aFinder);
		retVal.announce();
		return retVal;
	}

}
