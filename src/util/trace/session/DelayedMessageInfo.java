package util.trace.session;

public class DelayedMessageInfo extends AddressedMessageInfo {
	long delay;

	public DelayedMessageInfo(String aMessage, String aProcessName, Object aDataItem, String aSourceOrDestination, long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination, aFinder);
	}

	public long getDelay() {
		return delay;
	}

	public static String toString(String aProcessName, Object aDataItem, String aSourceOrDestination, long delay) {
		return toString (aProcessName, aDataItem, aSourceOrDestination) +  delay + "ms";
	}
	
}
