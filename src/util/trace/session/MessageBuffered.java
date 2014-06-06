package util.trace.session;

import java.util.List;



public class MessageBuffered extends MessageBufferInfo{

	public MessageBuffered(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, List aBuffer/*, int aReferenceCount*/, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aBuffer, aFinder);
//		buffer = aBuffer;
	}
	
	public MessageBuffered(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessageBuffered toTraceable(String aMessage) {
		return new MessageBuffered(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static MessageBuffered newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, List aCurrentBuffer, /*int aReferenceCount,*/ Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, /*aReferenceCount,*/ aCurrentBuffer);
		MessageBuffered retVal = new MessageBuffered(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aCurrentBuffer, /*aReferenceCount,*/ aFinder);
		retVal.announce();
		return retVal;
	}

}
