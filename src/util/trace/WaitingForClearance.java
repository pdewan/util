package util.trace;

import util.misc.ClearanceManager;
import util.singlestep.SingleStepperAndListBrowser;

public class WaitingForClearance extends TraceableInfo{
	Thread waiter;
	SingleStepperAndListBrowser clearanceManager;
	public WaitingForClearance (Object aFinder, Thread aWaiter, 
			SingleStepperAndListBrowser aClearanceManager) {
		super(aWaiter.getName() + " about to wait for:" + aClearanceManager, aFinder);
		waiter = aWaiter;
		clearanceManager = aClearanceManager;
	}
	public Thread getWaiter() {
		return waiter;
	}
	
	public SingleStepperAndListBrowser getClearanceManager() {
		return clearanceManager;
	}
	public static WaitingForClearance newCase(Object aFinder,
			Thread aWaiter, 
			SingleStepperAndListBrowser aClearanceManager) {
//		String aMessage = aWaiter.getName() + " about to wait for:" + aClearanceManager;
		WaitingForClearance retVal = new WaitingForClearance(aFinder, aWaiter, aClearanceManager);
		retVal.announce();
		return retVal;
	}
	

}
