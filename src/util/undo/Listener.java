package util.undo;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.models.Listenable;

public interface Listener extends Remote {
	public void update(Listenable listenable, Object info)
			throws RemoteException;
	// public void update (Listenable listenable, Object info, Object info2);
}