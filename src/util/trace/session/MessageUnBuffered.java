package util.trace.session;

import java.util.List;



public class MessageUnBuffered extends MessageBufferInfo{

	public MessageUnBuffered(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, List aBuffer, /*int aReferenceCount,*/ Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aBuffer, aFinder);
//		buffer = aBuffer;
	}	
	
	public MessageUnBuffered(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static MessageUnBuffered toTraceable(String aMessage) {
		return new MessageUnBuffered(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static MessageUnBuffered newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, List aCurrentBuffer/*, int aReferenceCount*/, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, /*aReferenceCount,*/ aCurrentBuffer);
		MessageUnBuffered retVal = new MessageUnBuffered(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aCurrentBuffer, /*aReferenceCount,*/ aFinder);
		retVal.announce();
		return retVal;
	}

}
