package util.trace.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import util.trace.TraceableInfo;




public class RemovedPropertyChangeListener extends TraceableInfo{
	PropertyChangeListener listener;
	

	public RemovedPropertyChangeListener(String aMessage, Object aFinder,
			PropertyChangeListener aListener) {
		super(aMessage,  aFinder);
		listener = aListener;
	}
	
	

	public static RemovedPropertyChangeListener newCase(Object aFinder,
			PropertyChangeListener aPropertyChangeListener) {
    	String aMessage =  aPropertyChangeListener.toString();
    	RemovedPropertyChangeListener traceable =
    			new RemovedPropertyChangeListener(aMessage, aFinder, aPropertyChangeListener);
//    	traceable.init(aFinder, anEvent);
    	traceable.announce();
    	return traceable;
	}
	

}
