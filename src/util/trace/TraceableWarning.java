package util.trace;


public  class TraceableWarning extends Traceable {
	public TraceableWarning(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
//		if (!messages.contains(aMessage))
//		Tracer.warning(aMessage);
	}

	@Override
	protected
	void maybePrintMessage(String aMessage, boolean isDuplicate) {

		if (!isDuplicate) {
			Tracer.warning(aMessage);

		}
	}
}
