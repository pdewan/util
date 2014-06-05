package util.trace;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// will not make it abstract as we will unparse a string into it
public  class Traceable extends RuntimeException {
	static Set<String> messages = new HashSet<String>();
	protected Object finder;
	protected long timeStamp;	
	protected boolean display;
	protected boolean wait;
	protected boolean exists;
	protected void maybePrintMessage(String aMessage, boolean isDuplicate) {
		
	}
	public Traceable(String aMessage, Object aFinder) {
		super(aMessage);
		init(aMessage, aFinder, System.currentTimeMillis());
//		finder = aFinder;
//		timeStamp = System.currentTimeMillis();
//		exists = messages.contains(aMessage);	
//		boolean retVal;
//		if (!exists)
//		 retVal =messages.add(aMessage);
	}
	public void init (String aMessage, Object aFinder, long aTimeStamp) {
		finder = aFinder;
		timeStamp = aTimeStamp;
		exists = messages.contains(aMessage);	
		boolean retVal;
		if (!exists)
		 retVal =messages.add(aMessage);
	}
	public Traceable(String aMessage, Object aFinder, long aTimeStamp) {
		super(aMessage);
		init(aMessage, aFinder, aTimeStamp);
//		finder = aFinder;
//		timeStamp = System.currentTimeMillis();
//		exists = messages.contains(aMessage);	
//		boolean retVal;
//		if (!exists)
//		 retVal =messages.add(aMessage);
//		maybePrintMessage(aMessage, exists);		
	}
	public static Set<String> getMessages() {
		return messages;
	}
	
	public void announce() {
		maybePrintMessage(getMessage(), exists);

		TraceableBus.newEvent(this);

	}
	
	public Traceable(String aMessage) {	
		super(aMessage);
		// no firing of event, postponed until init
	}
	public void init (Object aFinder) {
		finder = aFinder;
		timeStamp = System.currentTimeMillis();
		TraceableBus.newEvent(this);
		
	}
	public Object getFinder() {
		return finder;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	
//	public boolean getDisplay() {
//		return Tracer.getKeywordDisplayStatus(this) || Tracer.getKeywordDisplayStatus(getFinder());
//	}
	public boolean getDisplay() {
		return display || Tracer.getKeywordDisplayStatus(getFinder());
	}
	
	public void setDisplay(boolean newVal) {
		display = newVal;
	}
//	public boolean getWait() {
//		return Tracer.getKeywordWaitStatus(this) ||  Tracer.getKeywordWaitStatus(getFinder()) ;
//	}
	public boolean getWait() {
		return wait ||  Tracer.getKeywordWaitStatus(getFinder()) ;
	}
	
	public void setWait(boolean newVal) {
		wait = newVal;
	}
	public static String toTime(Date aDate) {
		return aDate.getHours() + ":" + aDate.getMinutes() + ":" + aDate.getSeconds();
	}
	public static String toString (long aTime) {
		
		Date date = new Date(aTime);
		
		return  " Time(" + aTime + ", " + toTime(date) + ")" + 
		" " + "Thread(" + Thread.currentThread().getName() + ")" ;
		
	}
	

}
