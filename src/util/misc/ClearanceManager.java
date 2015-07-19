package util.misc;

import util.models.PropertyListenerRegistrar;

public interface ClearanceManager extends PropertyListenerRegistrar{
	public void proceed();
	public void waitForClearance();
	public String getWaitingThreads();
	

}
