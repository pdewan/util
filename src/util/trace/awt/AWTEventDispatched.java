package util.trace.awt;

import java.awt.AWTEvent;

import util.session.CommunicatorSelector;
import util.trace.awt.CommunicatedAWTEventInfo;

public class AWTEventDispatched extends CommunicatedAWTEventInfo{

	public AWTEventDispatched(String aMessage, String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anAWTEvent, aGlobalId,  aDestinationOrSource, aFinder);
	}
	public AWTEventDispatched(String aMessage, CommunicatedAWTEventInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static AWTEventDispatched toTraceable (String aMessage) {
		CommunicatedAWTEventInfo anInfo = CommunicatedAWTEventInfo.toTraceable(aMessage);
		return new AWTEventDispatched(aMessage, anInfo);
	}
	public static AWTEventDispatched newCase(
			String aProcessName,
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anAWTEvent, aGlobalId, aSourceOrDestination);
		AWTEventDispatched retVal = new AWTEventDispatched(aMessage, aProcessName, anAWTEvent, aGlobalId,  aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
	
	public static AWTEventDispatched newCase(
			
			AWTEvent anAWTEvent,  Object aFinder) {
			
		return AWTEventDispatched.newCase(CommunicatorSelector.getProcessName(), anAWTEvent, "", null, aFinder);
	}

}
