package util.singlestep;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import util.annotations.Column;
import util.annotations.Position;
import util.annotations.Row;
import util.trace.Tracer;

public class AListBrowser implements ListBrowser{
	List history = new ArrayList();
	int currentIndex;
//	boolean waiting;
	
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public AListBrowser() {
	}
	
	public List history() {
		return history;
	}
	public boolean preForward() {
		return currentIndex < history.size() - 1;
	}
	@Row(1)
	@Column(0)
	public void forward() {
		currentIndex++;
		propertyChangeSupport.firePropertyChange("currentObject", null, getCurrentObject());
	}
	public boolean preBack() {
		return currentIndex > 0;
	}
	@Row(1)
	@Column(1)
	public void back() {
		currentIndex--;
		propertyChangeSupport.firePropertyChange("currentObject", null, getCurrentObject());

	}
	public boolean preGetCurrentObject() {
		return history.size() > 0;
	}
	@Row(0)
	public Object getCurrentObject() {
//		if (history.size() == 0) return "";
		return history.get(currentIndex);
	}
	public void addObject(Object anObject) {
		currentIndex = history.size();
		history.add(anObject);
		propertyChangeSupport.firePropertyChange("currentObject", null, getCurrentObject());
	}
	public void clear() {
		history.clear();
	}
	public boolean preDisplayCurrentEvent() {
		return Tracer.getKeywordDisplayStatus(getCurrentObject().getClass()) == null ||
				!Tracer.getKeywordDisplayStatus(getCurrentObject().getClass());
	}
	public boolean preDoNotDisplayCurrentEvent() {
		return Tracer.getKeywordDisplayStatus(getCurrentObject().getClass()) != null &&
				Tracer.getKeywordDisplayStatus(getCurrentObject().getClass());
	}
	public boolean preSingleStepCurrentEvent() {
		return Tracer.getKeywordWaitStatus(getCurrentObject().getClass()) == null ||
				!Tracer.getKeywordWaitStatus(getCurrentObject().getClass());
	}
	public boolean preDoNotSingleStepCurrentEvent() {
		return Tracer.getKeywordWaitStatus(getCurrentObject().getClass()) != null &&
				Tracer.getKeywordWaitStatus(getCurrentObject().getClass());
	}
	
	public void displayCurrentEvent() {
		Tracer.setKeywordDisplayStatus(getCurrentObject().getClass(), true);
	}
	
	public void doNotDisplayCurrentEvent() {
		Tracer.setKeywordDisplayStatus(getCurrentObject().getClass(), false);
	}
	public void singleStepCurrentEvent() {
		Tracer.setKeywordWaitStatus(getCurrentObject().getClass(), true);
	}
	public void doNotSingleStepCurrentEvent() {
		Tracer.setKeywordWaitStatus(getCurrentObject().getClass(), false);
	}
	
//	@Visible(false)
//	public synchronized void waitForUser() {
//		try {
//			waiting = true;
//			propertyChangeSupport.firePropertyChange("currentObject", null, getCurrentObject());
//			wait();
//			waiting = false;
//			propertyChangeSupport.firePropertyChange("currentObject", null, getCurrentObject());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public synchronized void proceed() {
//		try {
//			notify();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public boolean preProceed() {
//		return waiting;
//	}	
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	

}
