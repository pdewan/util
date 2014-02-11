/*
 * Created on Jun 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.models;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePropertyChangeListener /* extends Remote*/ {
	public void remotePropertyChange(PropertyChangeEvent evt)
			throws RemoteException;

}
