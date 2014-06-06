package util.trace.session;

public class DelayedMessageInfo extends AddressedMessageInfo {
	long delay;

	public DelayedMessageInfo(String aMessage, String aProcessName, Object aDataItem, String aSourceOrDestination, long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, aFinder);
		delay = aDelay;
	}
	
	public DelayedMessageInfo(String aMessage,  long aDelay, AddressedMessageInfo anAddressedMessageInfo) {
		super(aMessage, anAddressedMessageInfo);
		delay = aDelay;
	}

	public DelayedMessageInfo(String aMessage,  DelayedMessageInfo aDelayedMessageInfo) {
		this (aMessage, aDelayedMessageInfo.getDelay(), aDelayedMessageInfo);
	}
	public long getDelay() {
		return delay;
	}
	public static DelayedMessageInfo toTraceable(String aMessage) {
		AddressedMessageInfo anAddressedMessageInfo = AddressedMessageInfo.toTraceable(aMessage);
		return new DelayedMessageInfo(aMessage, getDelay(aMessage), anAddressedMessageInfo);
		
	}
	
	public static final String DELAY = "Delay";
	public static long getDelay(String aMessage) {
		return Long.parseLong(getArgs(aMessage, DELAY).get(0));
	}
	public static String toString(String aProcessName, Object aDataItem, String aSourceOrDestination, long delay) {
		return toString (aProcessName, aDataItem, aSourceOrDestination) +  
				", "
				+ DELAY +
				"(" + delay + ")";
	}
	
}
