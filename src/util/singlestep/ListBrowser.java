package util.singlestep;

import java.beans.PropertyChangeListener;
import java.util.List;

import util.misc.ClearanceManager;
import util.models.PropertyListenerRegisterer;

public interface ListBrowser  extends  PropertyListenerRegisterer  {
	
	public List history() ;
//	public boolean preNext() ;
//	public void next();
//	public boolean prePrevious() ;
//	public void previous() ;
	public Object getCurrentObject() ;
	public void addObject(Object anObject);
	public void clear();
//	public void waitForUser();

}
