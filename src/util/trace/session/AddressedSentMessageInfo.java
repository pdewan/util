package util.trace.session;

import util.trace.TraceableInfo;

public class AddressedSentMessageInfo extends AddressedMessageInfo {
	public static final String OTHERS = "others";
	public static final String ALL = "all";
	boolean isRelayed;
	public AddressedSentMessageInfo(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {
		super(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
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
	public static String toString(String aProcessName, Object aDataItem,
			String aSourceOrDestination, 
			boolean anIsRelayed) {
		return toString(aProcessName, aDataItem, aSourceOrDestination) + (anIsRelayed?"Relayed":"Direct");
	}
	
}
