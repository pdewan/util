package util.singlestep;

public interface ThreadWithSingleStepperAndBrowser {
	public Thread getThread();
	public String getThreadName();
	public SingleStepperAndListBrowser getSingleStepper();

}
