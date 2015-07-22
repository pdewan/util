package util.misc;

import util.models.PropertyListenerRegisterer;

public interface ClearanceManager extends PropertyListenerRegisterer{
	public void proceed();
	public void waitForClearance();
	public String getWaitingThreads();
	

}
