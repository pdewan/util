package util.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.undo.Listener;

public interface Listenable extends Remote {
	public void addListener(Listener listener) throws RemoteException;

	public void removeListener(Listener listener) throws RemoteException;

	public void notifyListeners() throws RemoteException;

	public void notifyListeners(Object info) throws RemoteException;
	// public void notifyListeners(Object info, Object info2);
}
