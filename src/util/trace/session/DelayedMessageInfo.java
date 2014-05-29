package util.trace.session;

public class DelayedMessageInfo extends MessageInfo {
	long delay;

	public DelayedMessageInfo(String aMessage, String aProcessName, Object aDataItem, long aDelay, Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aFinder);
	}

	public long getDelay() {
		return delay;
	}

	public static String toString(String aProcessName, Object aDataItem, long delay) {
		return toString (aProcessName, aDataItem) +  delay + "ms";
	}
	
}
