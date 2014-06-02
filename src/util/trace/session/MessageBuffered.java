package util.trace.session;

import java.util.List;



public class MessageBuffered extends MessageBufferInfo{
	List buffer;

	public MessageBuffered(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, List aBuffer, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aBuffer, aFinder);
		buffer = aBuffer;
	}	
	
	public static MessageBuffered newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, List aCurrentBuffer, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination, aCurrentBuffer);
		MessageBuffered retVal = new MessageBuffered(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aCurrentBuffer, aFinder);
		retVal.announce();
		return retVal;
	}

}
