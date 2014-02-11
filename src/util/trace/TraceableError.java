package util.trace;


public  class TraceableError extends Traceable {
	public TraceableError (String aMessage, Object aFinder) {
		super(aMessage, aFinder);
//		Tracer.error(aMessage);
////		TraceableBus.newEvent(this);
	}
	@Override
	protected
	void maybePrintMessage(String aMessage, boolean isDuplicate) {

		if (!isDuplicate) {
			Tracer.error(aMessage);

		}
	}

}
