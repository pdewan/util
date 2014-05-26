package util.trace.session;

import util.trace.TraceableInfo;

public class MessageInfo extends TraceableInfo {
//	public static final String OTHERS = "others";
//	public static final String ALL = "all";
	Object data;
//	String sourceOrDestination;
//	boolean isRelayed;
	public MessageInfo(String aMessage, Object aDataItem, Object aFinder) {
		super(aMessage, aFinder);
		this.data = aDataItem;
//		this.sourceOrDestination = aSourceOrDestination;
//		this.isRelayed = anIsRelayed;
	}
	public Object getData() {
		return data;
	}
//	public String getSourceOrDestination() {
//		return sourceOrDestination;
//	}
//	public boolean isRelayed() {
//		return isRelayed;
//	}
	public static String toString(Object aDataItem) {
		return aDataItem.toString() ;
	}
	
}
