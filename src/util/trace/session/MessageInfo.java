package util.trace.session;

import util.trace.TraceableInfo;

public class MessageInfo extends ProcessInfo {

	Object data;

	public MessageInfo(String aMessage, String aProcessName, Object aDataItem, Object aFinder) {
		super(aMessage, aProcessName, aFinder);
		this.data = aDataItem;
	}
	
	public MessageInfo(String aMessage, Object aDataItem, ProcessInfo aProcessInfo) {
		super(aMessage, aProcessInfo);
		this.data = aDataItem;
	}
	
	public MessageInfo(String aMessage, MessageInfo aMessageInfo) {
		this (aMessage, aMessageInfo.getData(), aMessageInfo);
//		super(aMessage, aProcessInfo);
//		this.data = aDataItem;
	}
	
	
	public Object getData() {
		return data;
	}
	
	public static final String MESSAGE = "Msg";
	
	public static String getData(String aMessage) {
		return getArgs(aMessage, MESSAGE).get(0);
	}
	
	public static MessageInfo toTraceable(String aMessage) {
		ProcessInfo aProcessInfo = toTraceable(aMessage);
		return new MessageInfo(aMessage, getData(aMessage), aProcessInfo);
	}

	public static String toString(String aProcessName, Object aDataItem) {
//		return toString(aProcessName) + (aDataItem  != null?  " Msg(" + aDataItem.toString() +")" :"") ;
		return toString(aProcessName) + (aDataItem  != null?  
//				" Msg(" +
				" " + MESSAGE + "(" +
				aDataItem.toString() +")" :"") ;

	}
	
}
