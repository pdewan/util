package util.trace.session;

import java.util.List;



public class MessageUnBuffered extends MessageBufferInfo{
	List<Object> buffer;

	public MessageUnBuffered(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, List aBuffer, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aBuffer, aFinder);
		buffer = aBuffer;
	}	
	
	public static MessageUnBuffered newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, List aCurrentBuffer, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		MessageUnBuffered retVal = new MessageUnBuffered(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aCurrentBuffer, aFinder);
		retVal.announce();
		return retVal;
	}

}
