package util.misc;

import util.annotations.ComponentWidth;
import util.annotations.Row;

public class AClearanceManager implements ClearanceManager {
	protected boolean clearance;
	@Row(0)
	@ComponentWidth(100)
//	@Label("Proceed")
	
//	public AClearanceManager() {
//		
//	}
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
				doWait();
				clearance = false;
		}
	}
	
	protected void doWait() {
		try {
			wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
