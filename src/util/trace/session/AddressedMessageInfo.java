package util.trace.session;

import util.trace.TraceableInfo;

public class AddressedMessageInfo extends MessageInfo {
	
	Object data;
	String sourceOrDestination;
	public AddressedMessageInfo(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination,  Object aFinder) {
		super(aMessage, aProcessName,  aDataItem, aFinder);
		this.data = aDataItem;
		this.sourceOrDestination = aSourceOrDestination;
	}
	public Object getData() {
		return data;
	}
	public String getSourceOrDestination() {
		return sourceOrDestination;
	}
	
	public static String toString(String aProcessName, Object aDataItem,
			String aSourceOrDestination) {
//		return toString(aProcessName) + " " + ((aDataItem == null)?"": "Msg(" + aDataItem + ")") + " Peer(" + aSourceOrDestination + ")"  ;
		return toString(aProcessName, aDataItem) + ", " + "Adr(" + aSourceOrDestination + ")"  ;

	}
	
}
