package util.trace.session;

import util.trace.TraceableInfo;

public class AddressedSentMessageInfo extends AddressedMessageInfo {
	public static final String OTHERS = "others";
	public static final String CLIENT = "client";

	public static final String NON_CALLERS = "nonCallers";
	public static final String CALLER = "caller";

	public static final String ALL = "all";
	public static final String DELIVERY = "Delivery";
	public static final String RELAYED = "Relayed";
	public static final String DIRECT = "Direct";
	boolean isRelayed;
	public AddressedSentMessageInfo(String aMessage, String aProcessName, Object aDataItem,
			String aSourceOrDestination, Boolean anIsRelayed, Object aFinder) {
		super(aMessage,  aProcessName, aDataItem, aSourceOrDestination, aFinder);
		this.isRelayed = anIsRelayed;
	}
	public AddressedSentMessageInfo(String aMessage, Boolean anIsRelayed, AddressedMessageInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
		isRelayed = anIsRelayed;
	}
	public AddressedSentMessageInfo(String aMessage, AddressedSentMessageInfo aThisClassInfo) {
		this(aMessage, aThisClassInfo.isRelayed(), aThisClassInfo);
	}
	public static AddressedSentMessageInfo toTraceable(String aMessage) {
		return new AddressedSentMessageInfo(aMessage, isRelayed(aMessage), AddressedMessageInfo.toTraceable(aMessage));
	}
	
	public Object getData() {
		return data;
	}
	public String getSourceOrDestination() {
		return sourceOrDestination;
	}
	public boolean isRelayed() {
		return isRelayed;
	}
	public static Boolean isRelayed (String aMessage) {
		return getArgs(aMessage, DELIVERY).get(0).equals(RELAYED);
	}
	public static String toString(String aProcessName, Object aDataItem,
			String aSourceOrDestination, 
			boolean anIsRelayed) {
		return toString(aProcessName, aDataItem, aSourceOrDestination) +
				" " + DELIVERY + "(" +
				(anIsRelayed?RELAYED:DIRECT) 
				+ ")";
	}
	
}
