package util.misc;

import util.models.PropertyListenerRegisterer;

public interface ClearanceManager extends PropertyListenerRegisterer{
	public void proceed();
	public boolean waitForClearance();
	public String getWaitingThreads();
	

}
