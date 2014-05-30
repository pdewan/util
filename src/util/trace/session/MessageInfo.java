package util.trace.session;

import util.trace.TraceableInfo;

public class MessageInfo extends ProcessInfo {

	Object data;

	public MessageInfo(String aProcessName, String aMessage, Object aDataItem, Object aFinder) {
		super(aProcessName, aMessage, aFinder);
		this.data = aDataItem;

	}
	public Object getData() {
		return data;
	}

	public static String toString(String aProcessName, Object aDataItem) {
		return toString(aProcessName) + " " + (aDataItem  != null? aDataItem.toString():"No Data") ;
	}
	
}
