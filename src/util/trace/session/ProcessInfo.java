package util.trace.session;

import util.trace.TraceableInfo;

public class ProcessInfo extends TraceableInfo {
	String processName;
	public ProcessInfo(String aMessage, 
			String aProcessName,
			Object aFinder) {
		super(aMessage, aFinder);
		processName = aProcessName;
	}
	public String getUserName() {
		return processName;
	}
	public static String toString (String aProcessName) {
		return "@" + aProcessName;
	}	
}
