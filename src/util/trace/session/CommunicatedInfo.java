package util.trace.session;
import java.awt.AWTEvent;




import util.trace.Traceable;
import util.trace.TraceableInfo;
//public class CommunicatedListEditInfo extends ListEditInfo {
public class CommunicatedInfo extends TraceableInfo {

	String destinationOrSource;
	String processName;
	
	public CommunicatedInfo(String aMessage,
			String aProcessName,
			String aDestinationOrSource,
			Object aFinder) {
		super(aMessage,  aFinder);
//		this.operationName = aName;
//		this.index = anIndex;
//		this.element = anElement;
		processName = aProcessName;
		destinationOrSource = aDestinationOrSource;
	}
	public CommunicatedInfo(String aMessage,
			String aProcessName,
			String aDestinationOrSource,
			Traceable aTraceable) {
		super(aMessage,  aTraceable);
//		this.operationName = aName;
//		this.index = anIndex;
//		this.element = anElement;
		processName = aProcessName;
		destinationOrSource = aDestinationOrSource;
	}
	public CommunicatedInfo(String aProcessName,  String aDestinationOrSource) {
		this("",  aProcessName,  aDestinationOrSource, null);		
	}
	public CommunicatedInfo(String aMessage, String aProcessName, String aDestinationOrSource) {
		super(aMessage);
		processName = aProcessName;
		destinationOrSource = aDestinationOrSource;
	}
	public CommunicatedInfo(String aMessage, CommunicatedInfo aCommunicatedAWTEventInfo) {
		this(aMessage, aCommunicatedAWTEventInfo.getProcessName(), aCommunicatedAWTEventInfo.getDestinationOrSource(), aCommunicatedAWTEventInfo);
	}

	public static CommunicatedInfo toTraceable (String aMessage) {
		Traceable aTraceable = TraceableInfo.toTraceable(aMessage);
		String aProcessName = ProcessInfo.getProcessName(aMessage);
		String anAddress = AddressedMessageInfo.getAddress(aMessage);		
		return new CommunicatedInfo(aMessage, aProcessName, anAddress, aTraceable);
	}
	
	
	public String getDestinationOrSource() {
		return destinationOrSource;
	}
	
	public static String toString(String aProcessName,  String aDestinationOrSource) {
		return ProcessInfo.toString(aProcessName)  + 
//				" Address(" +
				((aDestinationOrSource == null)?"": (
				" " + AddressedMessageInfo.ADDRESS) +
				"(" +
				aDestinationOrSource + ")" );
	}
	public String alternativeToString() {
		return toString(processName,  destinationOrSource);
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public void setDestinationOrSource(String destinationOrSource) {
		this.destinationOrSource = destinationOrSource;
	}
	
}
