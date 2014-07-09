package util.trace.session;


// same as clientjoininitiated
public class JoinRequest extends AddressedMessageInfo{

	public JoinRequest(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination,  Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination,  aFinder);
	}
	
	public JoinRequest(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static JoinRequest toTraceable(String aMessage) {
		return new JoinRequest(aMessage, AddressedMessageInfo.toTraceable(aMessage));
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
