package util.trace.session;



public class JoinRequest extends AddressedMessageInfo{

	public JoinRequest(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination,  Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination,  aFinder);
	}
	
	public static JoinRequest newCase(String aProcessName,
			Object aDataItem,
			String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		JoinRequest retVal = new JoinRequest(aMessage, aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
