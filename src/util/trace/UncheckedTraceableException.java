package util.trace;

import java.util.Date;

public class UncheckedTraceableException extends TraceableError {

	public UncheckedTraceableException(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}
	public UncheckedTraceableException(String aMessage) {
		this(aMessage, UncheckedTraceableException.class);
	}
	public UncheckedTraceableException() {
		this("", UncheckedTraceableException.class);
	}
	
	protected
	void maybePrintMessage(String aMessage, boolean isDuplicate) {
		// do not print as the catcher will probably do so

//		if (!isDuplicate) {
//			Tracer.error(aMessage);
//
//		}
	}

	

}
