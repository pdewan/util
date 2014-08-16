package util.singlestep;

import java.beans.PropertyChangeListener;
import java.util.List;

import util.misc.ClearanceManager;
import util.models.PropertyListenerRegistrar;

public interface SingleStepper extends  PropertyListenerRegistrar {
	
//	public List history() ;
	public boolean preForward() ;
	public void forward();
	public boolean preBack() ;
	public void back() ;
//	public Object getCurrentObject() ;
//	public void addObject(Object anObject);
//	public void clear();
	public void waitForUser();

}
