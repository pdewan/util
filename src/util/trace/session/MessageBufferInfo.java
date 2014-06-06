package util.trace.session;

import java.util.List;



public class MessageBufferInfo extends AddressedMessageInfo{
	List<Object> buffer;
//	int referenceCount;

	public MessageBufferInfo(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, List<Object> aBuffer, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
		buffer = aBuffer;
	}
	
	public MessageBufferInfo(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessageBufferInfo toTraceable(String aMessage) {
		return new MessageBufferInfo(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	public static String toString(String aProcessName, Object aDataItem,
			String aSourceOrDestination/*, int aReferenceCount*/, List<String> aBuffer) {
		return toString(aProcessName, aDataItem, aSourceOrDestination); // + "RC" + "(" + aReferenceCount + ")" + aBuffer;
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
