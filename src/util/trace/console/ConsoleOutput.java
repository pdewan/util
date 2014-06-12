package util.trace.console;

import util.trace.TraceableInfo;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.ClientJoined;
import util.trace.session.MessageInfo;

public class ConsoleOutput extends TraceableInfo{
	String output;
	
	public ConsoleOutput(String aMessage, String anOutput, Object aFinder) {
		super(aMessage, aFinder);
		output = anOutput;
	}
	public ConsoleOutput(String aMessage, String anOutput) {
		super(aMessage);
		output = anOutput;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public static final String OUTPUT = "Output";
	public static String toString (String anOutput) {
		return OUTPUT + "(" + anOutput + ")";
	}
	public static String getOutput(String aMessage) {
		return getArgs(aMessage, OUTPUT).get(0);
	}
	public static ConsoleOutput toTraceable(String aMessage) {
		return new ConsoleOutput (aMessage, getOutput(aMessage));
	}
	public static ConsoleOutput newCase (String anOutput, Object aFinder) {
		String aMessage = toString (anOutput);
		ConsoleOutput retVal = new ConsoleOutput(aMessage, anOutput, aFinder);
		retVal.announce();
		return retVal;
	}

}
