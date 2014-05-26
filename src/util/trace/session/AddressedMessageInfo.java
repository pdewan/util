package util.trace.session;

import util.trace.TraceableInfo;

public class AddressedMessageInfo extends MessageInfo {
	
	Object data;
	String sourceOrDestination;
	public AddressedMessageInfo(String aMessage, Object aDataItem,
			String aSourceOrDestination,  Object aFinder) {
		super(aMessage,  aDataItem, aFinder);
		this.data = aDataItem;
		this.sourceOrDestination = aSourceOrDestination;
	}
	public Object getData() {
		return data;
	}
	public String getSourceOrDestination() {
		return sourceOrDestination;
	}
	
	public static String toString(Object aDataItem,
			String aSourceOrDestination) {
		return aDataItem + "[" + aSourceOrDestination + "]"  ;
	}
	
}
