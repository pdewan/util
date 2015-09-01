package util.trace.javac;

import util.singlestep.SingleStepperAndListBrowser;
import util.trace.UncheckedTraceableException;
import util.trace.WaitingForClearance;

public class CompilerNotFound extends UncheckedTraceableException{
	public CompilerNotFound(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public static CompilerNotFound newCase(Object aFinder) {
		String home = System.getProperty("java.home");
//		System.out.println("Java Home =" + home);
		String aMessage = "Compiler not found. In eclipse make jdk be the execution environment instead of jre\n Your java home is:" + home;
		CompilerNotFound retVal = new CompilerNotFound(aMessage, aFinder);
		retVal.announce();
		return retVal;
	}

}
