package util.trace.javac;

import util.singlestep.SingleStepperAndListBrowser;
import util.trace.UncheckedTraceableException;
import util.trace.WaitingForClearance;

public class CompilerNotFound extends UncheckedTraceableException{
	public CompilerNotFound(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public static CompilerNotFound newCase(Object aFinder) {
		String aMessage = "Compiler not found. In eclipse make jdk be the execution environment instead of jre";
		CompilerNotFound retVal = new CompilerNotFound(aMessage, aFinder);
		retVal.announce();
		return retVal;
	}

}
