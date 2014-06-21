package util.trace.session;



public class LeaveRequestMarshalled extends AddressedMessageInfo{

	public LeaveRequestMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public LeaveRequestMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static LeaveRequestMarshalled toTraceable(String aMessage) {
		return new LeaveRequestMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static LeaveRequestMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		LeaveRequestMarshalled retVal = new LeaveRequestMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
