package util.trace.session;

import util.trace.TraceableInfo;

public class MessageForwarded extends MessageInfo {
	
//	Object data;
	public MessageForwarded(String aMessage, String aProcessName, Object aDataItem,
		 Object aFinder) {
		super(aMessage, aProcessName,  aDataItem, aFinder);
//		this.data = aDataItem;
	}
//	public Object getData() {
//		return data;
//	}
	public MessageForwarded(String aMessage,  
			MessageInfo aMessageInfo) {
		super(aMessage, aMessageInfo);
//		this.data = aDataItem;
	}
	
	
	
	public static MessageForwarded toTraceable(String aMessage) {
		MessageInfo aMessageInfo = MessageInfo.toTraceable(aMessage);
		return new MessageForwarded(aMessage,  aMessageInfo);
	}
	
	
	public static MessageForwarded newCase(String aProcessName,
			Object aDataItem,  Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem);
		MessageForwarded retVal = new MessageForwarded(aMessage,  aProcessName, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}
	
	
}
