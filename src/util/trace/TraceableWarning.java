package util.trace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public  class TraceableWarning extends Traceable {
	static Set<Class> doNotPrint = new HashSet();
	public TraceableWarning(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
//		if (!messages.contains(aMessage))
//		Tracer.warning(aMessage);
	}

	@Override
	protected
	void maybePrintMessage(String aMessage, boolean isDuplicate) {

		if (!isDuplicate && !doNotPrint.contains(this.getClass())) {
			Tracer.warning(aMessage);

		}
	}
	public static void doNotWarn(Class aClass ) {
		doNotPrint.add(aClass);
	}
}
