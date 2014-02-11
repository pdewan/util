package util.singlestep;

import util.annotations.ComponentWidth;
import util.annotations.Position;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class AThreadWithSingleStepperAndListBrowser implements ThreadWithSingleStepperAndBrowser {
	Thread thread;
	SingleStepperAndListBrowser singleStepperAndListBrowser;
	public AThreadWithSingleStepperAndListBrowser(Thread aThread, 
			SingleStepperAndListBrowser aSingleStepperAndListBrowser) {
		thread = aThread;
		singleStepperAndListBrowser = aSingleStepperAndListBrowser;
	}
	
	@Position(0)
	@ComponentWidth (300)
	public String getThreadName() {
		try  {
		return thread.getName();
		} catch (Exception e) {
			return "Exception when calling thread.getName";
		}
	}
	
	
	@Visible(false)
	public Thread getThread() {
		return thread;
	}
	@Position(1)
	public SingleStepperAndListBrowser getSingleStepper() {
		return singleStepperAndListBrowser;
	}

}
