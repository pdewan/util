package util.trace.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import util.trace.TraceableInfo;




public class NotifiedPropertyChangeEvent extends TraceableInfo{
	PropertyChangeEvent event;
	

	public NotifiedPropertyChangeEvent(String aMessage, Object aFinder,
			 PropertyChangeEvent anEvent, PropertyChangeListener[] sPropertyChangeListeners) {
		super(aMessage,  aFinder);
		event = anEvent;
	}
	
	

	public static NotifiedPropertyChangeEvent newCase(Object aFinder,
			PropertyChangeEvent anEvent, PropertyChangeListener[] sPropertyChangeListeners) {
    	String aMessage =  anEvent.toString() + "->" + Arrays.toString (sPropertyChangeListeners) ;
    	NotifiedPropertyChangeEvent traceable =
    			new NotifiedPropertyChangeEvent(aMessage, aFinder, anEvent, sPropertyChangeListeners);
//    	traceable.init(aFinder, anEvent);
    	traceable.announce();
    	return traceable;
	}
	

}
