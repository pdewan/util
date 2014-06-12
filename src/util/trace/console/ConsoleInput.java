package util.trace.console;

import util.trace.TraceableInfo;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.ClientJoined;
import util.trace.session.MessageInfo;

public class ConsoleInput extends TraceableInfo{
	String input;
	
	public ConsoleInput(String aMessage, String anInput, Object aFinder) {
		super(aMessage, aFinder);
		input = anInput;
	}
	public ConsoleInput(String aMessage, String anInput) {
		super(aMessage);
		input = anInput;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public static final String INPUT = "Input";
	public static String toString (String anInput) {
		return INPUT + "(" + anInput + ")";
	}
	public static String getInput(String aMessage) {
		return getArgs(aMessage, INPUT).get(0);
	}
	public static ConsoleInput toTraceable(String aMessage) {
		return new ConsoleInput (aMessage, getInput(aMessage));
	}
	public static ConsoleInput newCase (String anInput, Object aFinder) {
		String aMessage = toString (anInput);
		ConsoleInput retVal = new ConsoleInput(aMessage, anInput, aFinder);
		retVal.announce();
		return retVal;
	}

}
