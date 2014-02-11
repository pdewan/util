/*
 * Created on Jun 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.misc;

import java.beans.PropertyChangeEvent;

import util.models.RemotePropertyChangeListener;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PropertyNotificationThread implements Runnable {
	RemotePropertyChangeListener listener;
	PropertyChangeEvent evt;

	public PropertyNotificationThread(RemotePropertyChangeListener theListener,
			PropertyChangeEvent theEvent) {
		listener = theListener;
		evt = theEvent;

	}

	public void run() {
		try {
			listener.remotePropertyChange(evt);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
		}

	}

}
