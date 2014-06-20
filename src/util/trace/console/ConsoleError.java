package util.trace.console;

import util.trace.TraceableInfo;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.ServerClientJoined;
import util.trace.session.MessageInfo;

public class ConsoleError extends TraceableInfo{
	String error;
	
	public ConsoleError(String aMessage, String anError, Object aFinder) {
		super(aMessage, aFinder);
		error = anError;
	}
	public ConsoleError(String aMessage, String anError) {
		super(aMessage);
		error = anError;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public static final String ERROR = "Error";
	public static String toString (String anError) {
		return toString(System.currentTimeMillis()) + " " + ERROR + "(" + anError + ")";
	}
	public static String getError(String aMessage) {
		return getArgs(aMessage, ERROR).get(0);
	}
	public static ConsoleError toTraceable(String aMessage) {
		return new ConsoleError (aMessage, getError(aMessage));
	}
	public static ConsoleError newCase (String anError, Object aFinder) {
		String aMessage = toString (anError);
		ConsoleError retVal = new ConsoleError(aMessage, anError, aFinder);
		retVal.announce();
		return retVal;
	}

}
