package util.trace.session;

import java.util.Date;

import util.trace.TraceableInfo;

public class ProcessInfo extends TraceableInfo {
	protected static boolean longMessage = true;
	protected String processName;
	
	public ProcessInfo(String aMessage, 
			String aProcessName,
			Object aFinder) {
		super(aMessage, aFinder);
		processName = aProcessName;
	}
	public ProcessInfo(String aMessage, 
			String aProcessName,
			Object aFinder,
			long aTimeStamp
			) {
		super(aMessage, aFinder);
		processName = aProcessName;
	}
	public String getProcessName() {
		return processName;
	}
	
	public static String toString (String aProcessName) {
		if (aProcessName == null || (aProcessName.isEmpty()) )
			return "";
		long time = System.currentTimeMillis();
//		Date date = new Date(time);
		return toString(time) +
//		return  " Time(" + time + ", " + toTime(date) + ")" + 
//		" " + "Thread(" + Thread.currentThread().getName() + ")" +
		" " + "Process(" + aProcessName + ")";
		
	}	
}
