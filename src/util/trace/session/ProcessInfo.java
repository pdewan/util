package util.trace.session;

import java.util.Date;

import util.trace.Traceable;
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
	public ProcessInfo(String aMessage, String aProcessName,
			Traceable aTraceable
//			String aProcessName,			
//			Long aTimeStamp,
//			String aThreadName,
//			Object aFinder
			) {
		super(aMessage, aTraceable);
		processName = aProcessName;
	}
	public ProcessInfo(String aMessage, 
			ProcessInfo aTraceable
//			String aProcessName,			
//			Long aTimeStamp,
//			String aThreadName,
//			Object aFinder
			) {
		this (aMessage, aTraceable.getProcessName(), aTraceable);
		
	}
	public String getProcessName() {
		return processName;
	}
	
	public static ProcessInfo toTraceable(String aMessage) {
		try {
		Traceable aTraceable = Traceable.toTraceable(aMessage);
		String aProcessName = getProcessName(aMessage);
		return new ProcessInfo(aMessage, aProcessName, aTraceable);
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static String getProcessName(String aMessage) {
		return getArgs(aMessage, PROCESS_NAME).get(0);
	}
	
	public static final String PROCESS_NAME = "Process";
	
	public static String toString (String aProcessName) {
		if (aProcessName == null || (aProcessName.isEmpty()) )
			return "";
		long time = System.currentTimeMillis();
//		Date date = new Date(time);
		return toString(time) +
//		return  " Time(" + time + ", " + toTime(date) + ")" + 
//		" " + "Thread(" + Thread.currentThread().getName() + ")" +
//		" " + "Process(" + aProcessName + ")";
		" " + PROCESS_NAME + "(" + aProcessName + ")";
		
	}	
}
