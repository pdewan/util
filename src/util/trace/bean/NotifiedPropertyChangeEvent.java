package util.trace.bean;

import java.beans.PropertyChangeEvent;

import util.trace.TraceableInfo;




public class NotifiedPropertyChangeEvent extends TraceableInfo{
	PropertyChangeEvent event;

	public NotifiedPropertyChangeEvent(String aMessage, Object aFinder,
			 PropertyChangeEvent anEvent) {
		super(aMessage,  aFinder);
		event = anEvent;
	}
	
	

	public static NotifiedPropertyChangeEvent newCase(Object aFinder,
			PropertyChangeEvent anEvent) {
    	String aMessage =  anEvent.toString();
    	NotifiedPropertyChangeEvent traceable =
    			new NotifiedPropertyChangeEvent(aMessage, aFinder, anEvent);
//    	traceable.init(aFinder, anEvent);
    	traceable.announce();
    	return traceable;
	}
	

}
