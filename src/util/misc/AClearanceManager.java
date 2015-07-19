package util.misc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import util.annotations.Column;
import util.annotations.ComponentWidth;
import util.annotations.Row;

public class AClearanceManager implements ClearanceManager {
	protected boolean clearance;
	protected List<String> waitingThreadsList =  new ArrayList();
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	
//	@Label("Proceed")
	
//	public AClearanceManager() {
//		
//	}
	@Row(0) 
	@Column(0)
	@ComponentWidth(75)
	public synchronized void proceed() {
		clearance = true;
		notify();
	}
//	public synchronized void waitForClearance() {
//		if (!clearance) {
//			try {
//				wait();
//				clearance = false;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	public synchronized void waitForClearance() {
		if (!clearance) {
				waitingThreadsList.add(Thread.currentThread().getName());
				propertyChangeSupport.firePropertyChange("WaitingThreads", null, waitingThreadsList.toString());
				doWait();
				clearance = false;
				waitingThreadsList.remove(Thread.currentThread().getName());
				propertyChangeSupport.firePropertyChange("WaitingThreads", null, waitingThreadsList.toString());

		}
	}
	@Row(0) 
	@Column(1)
	@ComponentWidth(150)
	@Override
	public String getWaitingThreads() {
		return waitingThreadsList.toString();
	}
	protected void doWait() {
		try {
			wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	
}
