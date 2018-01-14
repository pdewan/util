package util.trace.bean;

import java.beans.PropertyChangeEvent;

import util.trace.TraceableInfo;




public class NotifiedPropertyChangeEvent extends TraceableInfo{
	PropertyChangeEvent event;

	public NotifiedPropertyChangeEvent(Object aFinder,
			 PropertyChangeEvent anEvent) {
		super("Notified property change event:" , aFinder);
		event = anEvent;
	}
	
	
//	public void init (ClassAdapter aFinder,
//			 PropertyChangeEvent anEvent) {
//		event = anEvent; // this must come before the firing init
//		init(aFinder);
//	}
//	
//	public PropertyChangeEvent getPropertyChangeEvent() {
//		return event;
//	}
//	public ClassAdapter getClassAdapter() {
//		return (ClassAdapter) getObjectAdapter();
//	}
//	public boolean equals (Object anObject) {
//		if (!(anObject instanceof ClassAdapterReceivedPropertyChangeEvent) ) {
//			return super.equals(anObject);
//		}
//		ClassAdapterReceivedPropertyChangeEvent other =  (ClassAdapterReceivedPropertyChangeEvent) anObject;
//		return 
//				getClassAdapter() == other.getClassAdapter() &&
//				event.getPropertyName().equalsIgnoreCase(other.getPropertyChangeEvent().getPropertyName()) &&
//				event.getNewValue().equals(other.getPropertyChangeEvent().getNewValue());
//			
//	}
	public static NotifiedPropertyChangeEvent newCase(Object aFinder,
			PropertyChangeEvent anEvent) {
//    	String aMessage = "Class adapter:" + aFinder + " received property change event:" + anEvent;
    	NotifiedPropertyChangeEvent traceable =
    			new NotifiedPropertyChangeEvent(aFinder, anEvent);
//    	traceable.init(aFinder, anEvent);
    	traceable.announce();
    	return traceable;
	}
	

}
