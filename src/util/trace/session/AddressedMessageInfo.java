package util.trace.session;

import util.trace.TraceableInfo;

public class AddressedMessageInfo extends MessageInfo {
	public static final String OTHERS = "others";
	public static final String ALL = "all";
	Object data;
	String sourceOrDestination;
	boolean isRelayed;
	public AddressedMessageInfo(String aMessage, Object aDataItem,
			String aSourceOrDestination, boolean anIsRelayed, Object aFinder) {
		super(aMessage,  aDataItem, aFinder);
		this.data = aDataItem;
		this.sourceOrDestination = aSourceOrDestination;
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
		return aDataItem + "[" + aSourceOrDestination + "]" + (anIsRelayed?"Relayed":"Direct");
	}
	
}
