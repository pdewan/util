package util.trace;

import java.util.HashSet;
import java.util.Set;

public class TraceableBus {
	static Set<TraceableListener> listeners = new HashSet();
	static Object synchronizer = new Object();
	public static void newEvent(Exception aTraceable) {
		notifyTraceableListeners(aTraceable);
	}
	// got a concurrent modification exception
	public static void addTraceableListener(TraceableListener aListener) {
		synchronized (synchronizer) {
			listeners.add(aListener);
		}
	}
	public static void removeTraceableListener(TraceableListener aListener) {
		synchronized (synchronizer) {
			listeners.remove(aListener);
		}
	}
	// so this can result in a deadlock if we make new event wait in latecomer
	// cannot have this do notify and wait.
	// may need to fix that if we make use of this facility and create a copy
	// of the listener list
	static void notifyTraceableListeners(Exception aTraceable) {	
		Set<TraceableListener> copyListeners;
		synchronized (synchronizer) {
			copyListeners = new HashSet(listeners);
		}
		for (TraceableListener aTraceableBusListener:copyListeners) {
			aTraceableBusListener.newEvent(aTraceable);
		}		
	}
}
