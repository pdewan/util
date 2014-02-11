package util.trace;


public  class TraceableFatalError extends Traceable {
	public TraceableFatalError (String aMessage, Object aFinder) {
		super(aMessage, aFinder);
//		Tracer.error(aMessage);
////		TraceableBus.newEvent(this);
	}
	@Override
	protected
	void maybePrintMessage(String aMessage, boolean isDuplicate) {

		if (!isDuplicate) {
			Tracer.fatalError(aMessage);

		}
	}

}
