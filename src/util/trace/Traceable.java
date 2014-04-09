package util.trace;

import java.util.HashSet;
import java.util.Set;


public abstract  class Traceable extends RuntimeException {
	static Set<String> messages = new HashSet<String>();
	protected Object finder;
	protected long timeStamp;	
	protected boolean display;
	protected boolean wait;
	protected boolean exists;
	abstract protected void maybePrintMessage(String aMessage, boolean isDuplicate);
	public Traceable(String aMessage, Object aFinder) {
		super(aMessage);
		finder = aFinder;
		timeStamp = System.currentTimeMillis();
		exists = messages.contains(aMessage);	
		boolean retVal;
		if (!exists)
		 retVal =messages.add(aMessage);
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
	

}
