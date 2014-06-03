package util.trace.session;

import java.util.List;



public class MessageBufferReferenceCountDecremented extends MessageBufferInfo{

	public MessageBufferReferenceCountDecremented(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, List aBuffer, /*int aReferenceCount,*/ Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aBuffer, aFinder);
//		buffer = aBuffer;
	}	
	
	public static MessageBufferReferenceCountDecremented newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, List aCurrentBuffer, /*int aReferenceCount,*/ Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, /*aReferenceCount,*/ aCurrentBuffer);
		MessageBufferReferenceCountDecremented retVal = new MessageBufferReferenceCountDecremented(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aCurrentBuffer, /*aReferenceCount,*/ aFinder);
		retVal.announce();
		return retVal;
	}

}
