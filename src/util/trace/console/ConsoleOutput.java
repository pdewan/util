package util.trace.console;

import java.util.Arrays;
import java.util.List;

import util.trace.TraceableInfo;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.ServerClientJoined;
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
	protected void setEqualPropertiesList() {
		super.setEqualPropertiesList();
//		consoleEqualPropertiesArray = new String[]{"Output"};
		 String[] consoleEqualPropertiesArray = {"Output"};

		List<String> aConsoleList = Arrays.asList(consoleEqualPropertiesArray);
		equalPropertiesList.addAll(aConsoleList);

		
//		equalPropertiesList = Arrays.asList(equalPropertiesArray);

	}
	public void setOutput(String output) {
		this.output = output;
	}
	public static final String OUTPUT = "Output";
	public static String toString (String anOutput) {
		return  toString(System.currentTimeMillis()) + " " + OUTPUT + "(" + anOutput + ")";
	}
	public static String getOutput(String aMessage) {
//		return getArgs(aMessage, OUTPUT).get(0);
		List<String> args = getArgs(aMessage, OUTPUT);
		if (args.size() == 0) return "";
		else return args.get(0);
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
