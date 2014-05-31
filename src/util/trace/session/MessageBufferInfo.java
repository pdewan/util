package util.trace.session;

import java.util.List;



public class MessageBufferInfo extends AddressedMessageInfo{
	List<Object> buffer;

	public MessageBufferInfo(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, List<Object> aBuffer, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
		buffer = aBuffer;
	}
	
	public static String toString(String aProcessName, Object aDataItem,
			String aSourceOrDestination, List<String> aBuffer) {
		return toString(aProcessName, aDataItem, aSourceOrDestination) + aBuffer;
	}
	
	public List<Object> getBuffer() {
		return buffer;
	}
	
	public static MessageBufferInfo newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, List<Object> aCurrentBuffer, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageBufferInfo retVal = new MessageBufferInfo(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aCurrentBuffer, aFinder);
		retVal.announce();
		return retVal;
	}

}
