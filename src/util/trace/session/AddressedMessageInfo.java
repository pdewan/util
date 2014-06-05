package util.trace.session;

import util.trace.TraceableInfo;

public class AddressedMessageInfo extends MessageInfo {
	
//	Object data;
	String sourceOrDestination;
	public AddressedMessageInfo(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination,  Object aFinder) {
		super(aMessage, aProcessName,  aDataItem, aFinder);
//		this.data = aDataItem;
		this.sourceOrDestination = aSourceOrDestination;
	}
//	public Object getData() {
//		return data;
//	}
	public AddressedMessageInfo(String aMessage,  
			String aSourceOrDestination,  MessageInfo aMessageInfo) {
		super(aMessage, aMessageInfo);
//		this.data = aDataItem;
		this.sourceOrDestination = aSourceOrDestination;
	}
	
	public AddressedMessageInfo(String aMessage,  
			AddressedMessageInfo anAddressedMessageInfo) {
		this(aMessage, anAddressedMessageInfo.getSourceOrDestination(), anAddressedMessageInfo);
//		
	}
	
	
	public String getSourceOrDestination() {
		return sourceOrDestination;
	}
	
	public static final String ADDRESS = "Address";
	
	public static String getAddress(String aMessage) {
		return getArgs(aMessage, ADDRESS).get(0);
	}
	
	public static AddressedMessageInfo toTraceable(String aMessage) {
		MessageInfo aMessageInfo = MessageInfo.toTraceable(aMessage);
		return new AddressedMessageInfo(aMessage, getAddress(aMessage), aMessageInfo);
	}
	
	
	
	public static String toString(String aProcessName, Object aDataItem,
			String aSourceOrDestination) {
//		return toString(aProcessName) + " " + ((aDataItem == null)?"": "Msg(" + aDataItem + ")") + " Peer(" + aSourceOrDestination + ")"  ;
		return toString(aProcessName, aDataItem) + " " + 
		ADDRESS + "(" +
//		"Address(" + 
		aSourceOrDestination + ")"  ;

	}
	
}
