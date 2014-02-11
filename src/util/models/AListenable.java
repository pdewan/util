package util.models;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.Enumeration;

import util.misc.UnicastRemoteObjectForChosenPort;
import util.undo.Listener;

public class AListenable /*extends UnicastRemoteObjectForChosenPort*/ implements
		Listenable {
	public AListenable() {

	}

	private transient Vector listenerList = new Vector();

	public void addListener(Listener listener) {
		listenerList.addElement(listener);
	}

	public void removeListener(Listener listener) {
		listenerList.removeElement(listener);
	}

	public void notifyListeners(Object info) {
		try {
			for (Enumeration elements = listenerList.elements(); elements
					.hasMoreElements();) {
				Listener listener = (Listener) elements.nextElement();
				listener.update(this, info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void notifyListeners() {
		// maybe this was false
		notifyListeners(null);
	}
	
	public void initSerializedObject() {
		listenerList = new Vector();
	}

	public Object clone() {
		AListenable listenableClone = null;
		try {
			listenableClone = (AListenable) super.clone();
			listenableClone.listenerList = new Vector();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return (listenableClone);
		}
	}

}
