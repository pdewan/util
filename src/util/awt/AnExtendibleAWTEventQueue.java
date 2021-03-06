package util.awt;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.InvocationEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.models.Vetoer;
import util.trace.awt.AWTEventDispatched;


public class AnExtendibleAWTEventQueue extends EventQueue implements ExtendibleAWTEventQueue {
	static AnExtendibleAWTEventQueue eventQueue; // serevres as a factory class
	CommunicatedAWTEventSupport communicatedEventSupport = new ACommunicatedAWTEventSupport();
	VetoableChangeSupport vetoChangeSupport = new VetoableChangeSupport(this);

	List<AWTEventQueueHandler> eventQueueListeners = new ArrayList();
//	List<Vetoer<AWTEvent>> vetoers = new ArrayList();

	public AnExtendibleAWTEventQueue() {
		if (eventQueue != null)
			throw new RuntimeException(
					"Call ADelegateEventQueue.getDelegateEventQueue() for an instance of this singleton");
		eventQueue = this;
		EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		queue.push(this);
	}



	@Override
	public void addEventQueueHandler(AWTEventQueueHandler listener) {
		if (eventQueueListeners.contains(listener))
			return;
		eventQueueListeners.add(listener);
	}

	public static void addHandler(AWTEventQueueHandler handler) {
		eventQueue.addEventQueueHandler(handler);
	}

	public void removeHandler(AWTEventQueueHandler handler) {
		eventQueue.removeHandler(handler);
	}

	public void clearHandlers(AWTEventQueueHandler handler) {
		eventQueue.clearEventQueuehandlers();
	}

	public void removeEventQueueHandler(AWTEventQueueHandler listener) {
		eventQueueListeners.remove(listener);
	}


	@Override
	public void dispatchReceivedEvent(AWTEvent anEvent) {
	
		if (anEvent != null)
			super.dispatchEvent(anEvent);

	}

	Set sources = new HashSet();

	boolean isRelevantMouseEvent(AWTEvent anEvent) {
		return AWTMisc.isMouseClickedEvent(anEvent) ||
				AWTMisc.isMousePressedEvent(anEvent) ||
				AWTMisc.isMouseReleasedEvent(anEvent) ||
				AWTMisc.isMouseDraggedEvent(anEvent);
	}
	
	public void dispatchEvent(AWTEvent anEvent) {
		AWTEvent sentEvent = communicatedEventSupport.toSentEvent(anEvent);

		if ( sentEvent != null && // do not control event if it is not shared
				(anEvent instanceof MouseEvent && 
				
				isRelevantMouseEvent(anEvent)) || anEvent instanceof KeyEvent)
		try {
		vetoChangeSupport.fireVetoableChange(WINDOW, null, anEvent);
		} catch (PropertyVetoException e) {
			System.err.println("Not Dispatching event:" + anEvent);

			return;
		}
//		System.out.println("Dispatching event:" + anEvent);
		AWTEventDispatched.newCase(anEvent,  this);
		super.dispatchEvent(anEvent);

//		AWTEvent sentEvent = communicatedEventSupport.toSentEvent(anEvent);

		// System.out.println("Dispatching:" + anEvent);
		// System.out.println("Sent event:" + sentEvent);
		
		

		if (sentEvent != null) {
//			int anId = 0;

			if (anEvent.getSource() instanceof Component) { // event is either
															// a component
															// or instanceof
															// WToolkit, a
															// non public
															// class you
															// cannot seem
															// to name


				for (AWTEventQueueHandler listener : eventQueueListeners) {

					listener.newEvent(sentEvent);
				}
			}
		}
	

	}

	
	public static AnExtendibleAWTEventQueue getEventQueue() {
		if (eventQueue == null)
			eventQueue = new AnExtendibleAWTEventQueue();
		return eventQueue;
	}

	public static void useAsEventQueue() {
		getEventQueue();
	}

	@Override
	public void clearEventQueuehandlers() {
		eventQueueListeners.clear();

	}

	public CommunicatedAWTEventSupport getCommunicatedEventSupport() {
		return communicatedEventSupport;
	}

	public void setCommunicatedEventSupport(
			CommunicatedAWTEventSupport communicatedEventSupport) {
		this.communicatedEventSupport = communicatedEventSupport;
	}
//	public void addVetoer(Vetoer theVetoer) {
//		vetoers.add(theVetoer);		
//	}
//	public void removeVetoer(Vetoer theVetoer) {
//		vetoers.remove(theVetoer);		
//	}
	
//	protected boolean vetoed(AWTEvent theValue) {
//		for (Vetoer vetoer:vetoers) {
//			if (vetoer.veto(theValue)) return true;
//		}
//		return false;
//	}

	static {
		eventQueue = new AnExtendibleAWTEventQueue();
	}

	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		vetoChangeSupport.addVetoableChangeListener(listener);
	}



	@Override
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		vetoChangeSupport.removeVetoableChangeListener(listener);		
	}
}
