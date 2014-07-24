package util.trace.session;



public class SessionMessageReceived extends MessageReceived{

	public SessionMessageReceived(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public SessionMessageReceived(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static SessionMessageReceived toTraceable(String aMessage) {
		return new SessionMessageReceived(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static SessionMessageReceived newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		SessionMessageReceived retVal = new SessionMessageReceived(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
