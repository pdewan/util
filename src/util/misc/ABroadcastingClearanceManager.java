package util.misc;

import util.annotations.ComponentWidth;
import util.annotations.Row;

public class ABroadcastingClearanceManager extends AClearanceManager 
					implements BroadcastingClearanceManager {
	public ABroadcastingClearanceManager() {
		
	}

	@Row(1)
	@ComponentWidth(100)
	public synchronized void proceedAll() {
		clearance = true;
		notifyAll();		
	}
}
