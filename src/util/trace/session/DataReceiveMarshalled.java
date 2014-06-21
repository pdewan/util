package util.trace.session;



public class DataReceiveMarshalled extends AddressedMessageInfo{

	public DataReceiveMarshalled(String aMessage, String aProcessName,  Object aDataItem, String aSourceOrDestination, Object aFinder) {
		super(aMessage, aProcessName, aDataItem,  aSourceOrDestination, aFinder);
	}
	
	public DataReceiveMarshalled(String aMessage, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	
	public static DataReceiveMarshalled toTraceable(String aMessage) {
		return new DataReceiveMarshalled(aMessage, AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public static DataReceiveMarshalled newCase(String aProcessName,
			Object aDataItem, String aSourceOrDestination, Object aFinder) {			
		String aMessage = toString(aProcessName, aDataItem, aSourceOrDestination);
		DataReceiveMarshalled retVal = new DataReceiveMarshalled(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
