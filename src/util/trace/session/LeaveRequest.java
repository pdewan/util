package util.trace.session;



public class LeaveRequest extends AddressedMessageInfo{

	public LeaveRequest(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination,  Object aFinder) {
		super(aMessage, aProcessName, aDataItem, aSourceOrDestination,  aFinder);
	}
	
	public static LeaveRequest newCase(String aProcessName,
			Object aDataItem,
			String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		LeaveRequest retVal = new LeaveRequest(aMessage, aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
