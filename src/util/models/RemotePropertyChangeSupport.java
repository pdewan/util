/*
 * Created on Jun 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.models;

import java.util.Vector;
import java.beans.PropertyChangeEvent;

import util.misc.PropertyNotificationThread;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class RemotePropertyChangeSupport {
	Vector listeners = new Vector();
	Object listenable;

	public RemotePropertyChangeSupport(Object theListenable) {
		listenable = theListenable;
	}

	public void addRemotePropertyChangeListener(RemotePropertyChangeListener l) {
		if (listeners.contains(l))
			return;
		listeners.add(l);
	}

	public void removeRemotePropertyChangeListener(
			RemotePropertyChangeListener l) {
		listeners.remove(l);
	}

	public void fireRemotePropertyChange(String propertyName, Object oldValue,
			Object newValue) {

		PropertyChangeEvent event = new PropertyChangeEvent(listenable,
				propertyName, oldValue, newValue);
		for (int i = 0; i < listeners.size(); i++) {
			try {
				((RemotePropertyChangeListener) listeners.elementAt(i))
						.remotePropertyChange(event);
			} catch (Exception e) {
				listeners.removeElementAt(i);
			}
		}
	}

	public void asyncFireRemotePropertyChange(String propertyName,
			Object oldValue, Object newValue) {

		PropertyChangeEvent event = new PropertyChangeEvent(listenable,
				propertyName, oldValue, newValue);

		for (int i = 0; i < listeners.size(); i++) {
			try {
				RemotePropertyChangeListener listener = (RemotePropertyChangeListener) listeners
						.elementAt(i);
				(new Thread((new PropertyNotificationThread(listener, event))))
						.start();
			} catch (Exception e) {
				listeners.removeElementAt(i);
			}
		}
	}

}
