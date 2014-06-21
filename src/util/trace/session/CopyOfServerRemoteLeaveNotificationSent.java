package util.trace.session;



public class CopyOfServerRemoteLeaveNotificationSent extends AddressedMessageInfo{

	public CopyOfServerRemoteLeaveNotificationSent(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public CopyOfServerRemoteLeaveNotificationSent(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static CopyOfServerRemoteLeaveNotificationSent toTraceable(String aMessage) {
		return new CopyOfServerRemoteLeaveNotificationSent(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static CopyOfServerRemoteLeaveNotificationSent newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		CopyOfServerRemoteLeaveNotificationSent retVal = new CopyOfServerRemoteLeaveNotificationSent(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
