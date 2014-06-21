package util.trace.session;



public class JoinRequestMarshalled extends AddressedMessageInfo{

	public JoinRequestMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public JoinRequestMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static JoinRequestMarshalled toTraceable(String aMessage) {
		return new JoinRequestMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static JoinRequestMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		JoinRequestMarshalled retVal = new JoinRequestMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
