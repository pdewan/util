package util.trace.session;

import util.trace.TraceableInfo;

public class AddressedSentMessageInfo extends AddressedMessageInfo {
	public static final String OTHERS = "others";
	public static final String ALL = "all";
	boolean isRelayed;
	public AddressedSentMessageInfo(String aMessage, Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {
		super(aMessage,  aDataItem, aSourceOrDestination, aFinder);
		this.isRelayed = anIsRelayed;
	}
	public Object getData() {
		return data;
	}
	public String getSourceOrDestination() {
		return sourceOrDestination;
	}
	public boolean isRelayed() {
		return isRelayed;
	}
	public static String toString(Object aDataItem,
			String aSourceOrDestination, 
			boolean anIsRelayed) {
		return toString(aDataItem, aSourceOrDestination) + (anIsRelayed?"Relayed":"Direct");
	}
	
}
