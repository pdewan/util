package util.trace.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import util.trace.TraceableInfo;




public class AddedPropertyChangeListener extends TraceableInfo{
	PropertyChangeListener listener;
	

	public AddedPropertyChangeListener(String aMessage, Object aFinder,
			PropertyChangeListener aListener) {
		super(aMessage,  aFinder);
		listener = aListener;
	}
	
	

	public static AddedPropertyChangeListener newCase(Object aFinder,
			PropertyChangeEvent anEvent, PropertyChangeListener aPropertyChangeListener) {
    	String aMessage =  aPropertyChangeListener.toString();
    	AddedPropertyChangeListener traceable =
    			new AddedPropertyChangeListener(aMessage, aFinder, aPropertyChangeListener);
//    	traceable.init(aFinder, anEvent);
    	traceable.announce();
    	return traceable;
	}
	

}
