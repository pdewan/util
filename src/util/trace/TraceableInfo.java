package util.trace;



public  class TraceableInfo extends Traceable {
	public TraceableInfo (String aMessage, Object aFinder) {
		super(aMessage, aFinder);
//		Tracer.info(aFinder, aMessage);
//		TraceableBus.newEvent(this);
	}
	public TraceableInfo (String aMessage) {
		super(aMessage);
//		Tracer.info(aFinder, aMessage);
//		TraceableBus.newEvent(this);
	}
	public void init (Object aFinder) {
		super.init(aFinder);
		Tracer.info(aFinder, getMessage());

//		TraceableBus.newEvent(this);
		
	}
	@Override
	protected
	void maybePrintMessage(String aMessage, boolean isDuplicate) {

		if (!isDuplicate) {
			Tracer.info(finder, aMessage);

		}
	}
	public static TraceableInfo newInfo(String aMessage, Object aFinder) {    	
		TraceableInfo retVal = new TraceableInfo(aMessage, aFinder);
   	    retVal.announce();
    	return retVal;
	}
}
