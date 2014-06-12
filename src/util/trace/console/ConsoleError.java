package util.trace.console;

import util.trace.TraceableInfo;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.ClientJoined;
import util.trace.session.MessageInfo;

public class ConsoleError extends TraceableInfo{
	String output;
	
	public ConsoleError(String aMessage, String anOutput, Object aFinder) {
		super(aMessage, aFinder);
		output = anOutput;
	}
	public ConsoleError(String aMessage, String anOutput) {
		super(aMessage);
		output = anOutput;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public static final String INPUT = "Output";
	public static String toString (String anOutput) {
		return INPUT + "(" + anOutput + ")";
	}
	public static String getOutput(String aMessage) {
		return getArgs(aMessage, INPUT).get(0);
	}
	public static ConsoleError toTraceable(String aMessage) {
		return new ConsoleError (aMessage, getOutput(aMessage));
	}
	public static ConsoleError newCase (String anOutput, Object aFinder) {
		String aMessage = toString (anOutput);
		ConsoleError retVal = new ConsoleError(aMessage, anOutput, aFinder);
		retVal.announce();
		return retVal;
	}

}
