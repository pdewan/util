package util.awt;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import util.misc.Common;
import util.session.ARelayerCommunicator;
import util.session.Communicator;
import util.trace.Tracer;

public class AnInputQueue extends EventQueue implements DelegateEventQueue {
	static AnInputQueue eventQueue;
	List<EventQueueHandler> eventQueueListeners = new ArrayList();

	public AnInputQueue() {
		if (eventQueue != null)
			throw new RuntimeException(
					"Call ADelegateEventQueue.getDelegateEventQueue() for an instance of this singleton");
		eventQueue = this;
		EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		queue.push(this);
		addEventQueueHandler(new ALocalEventForwarder());
	}

	@Override
	public void addEventQueueHandler(EventQueueHandler listener) {
		if (eventQueueListeners.contains(listener))
			return;
		eventQueueListeners.add(listener);
	}

	public static void addHandler(EventQueueHandler handler) {
		eventQueue.addEventQueueHandler(handler);
	}

	public void removeHandler(EventQueueHandler handler) {
		eventQueue.removeHandler(handler);
	}

	public void clearHandlers(EventQueueHandler handler) {
		eventQueue.clearEventQueuehandlers();
	}

	public void removeEventQueueHandler(EventQueueHandler listener) {
		eventQueueListeners.remove(listener);
	}

	public void dispatchEvent(SerializableEvent event) {
		if (event.getAWTEvent().getSource() != null)
			super.dispatchEvent(event.getAWTEvent());
	}

	public void dispatchEvent(AWTEvent event) {
		super.dispatchEvent(event);
		if (!(event.getSource() instanceof ADelegateFrame)) {
			return;
		}
		// Message.info("event:" + event);
		ASerializableEvent serializedEvent = new ASerializableEvent(event,
				ComponentRegistry.getComponentId((ADelegateFrame) event
						.getSource()));
		for (EventQueueHandler listener : eventQueueListeners) {
			listener.newEvent(serializedEvent);
		}
	}

	public static AnInputQueue getEventQueue() {
		if (eventQueue == null)
			eventQueue = new AnInputQueue();
		return eventQueue;
	}

	@Override
	public void clearEventQueuehandlers() {
		eventQueueListeners.clear();

	}

	static {
		eventQueue = new AnInputQueue();
	}
}
