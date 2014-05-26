package util.trace.session;

public class DelayedMessageInfo extends MessageInfo {
	long delay;

	public DelayedMessageInfo(String aMessage, Object aDataItem, long aDelay, Object aFinder) {
		super(aMessage, aDataItem, aFinder);
	}

	public long getDelay() {
		return delay;
	}

	public static String toString(Object aDataItem, long delay) {
		return toString (aDataItem) + "@" + delay;
	}
	
}
