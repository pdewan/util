package util.singlestep;

import util.misc.ClearanceManager;
import util.misc.HashIdentityMap;
import util.misc.IdentityMap;
import util.trace.Traceable;
import util.trace.WaitingForClearance;

public class GlobalThreadSingleStepper {
	static IdentityMap<Thread, SingleStepperAndListBrowser> 
		threadToSingleStepper = new HashIdentityMap();
	public static void waitForClearance(Traceable aTraceable) {		
		SingleStepperAndListBrowser clearanceManager =  getSingleStepper();
		WaitingForClearance.newCase(
				GlobalThreadSingleStepper.class, 
				Thread.currentThread(), 
				clearanceManager);
	}
	
	public static SingleStepperAndListBrowser getSingleStepper() {
		Thread currentThread = Thread.currentThread();
		SingleStepperAndListBrowser retVal = threadToSingleStepper.get(currentThread);
		if (retVal == null) {
			retVal = new ASingleStepperAndListBrowser();
		}
		threadToSingleStepper.put(currentThread, retVal);
		return retVal;
	}
}
