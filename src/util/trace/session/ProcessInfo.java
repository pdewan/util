package util.trace.session;

import java.util.Date;

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
	public static String toTime(Date aDate) {
		return aDate.getHours() + ":" + aDate.getMinutes() + ":" + aDate.getSeconds();
	}
	public static String toString (String aProcessName) {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		
		return "(" + time + "," + toTime(date) + ")" + "@" + aProcessName;
	}	
}
