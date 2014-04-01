package util.trace;



public  class TraceableInfo extends Traceable {
	boolean printDuplicates = true; // info means this is about steps, which should be duplicated by default
	
	public TraceableInfo (String aMessage) {
		super(aMessage);
		printDuplicates = true; 
//		Tracer.info(aFinder, aMessage);
//		TraceableBus.newEvent(this);
	}
	public TraceableInfo (String aMessage, Object aFinder) {
		super(aMessage, aFinder);
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

		if (printDuplicates || !isDuplicate) {
			Tracer.info(finder, aMessage); // discriminate by event firer
			Tracer.info(this, aMessage); // discriminate by event


		}
	}
	public boolean isPrintDuplicates() {
		return printDuplicates;
	}
	
	public void setPrintDuplicates(boolean printDuplicates) {
		this.printDuplicates = printDuplicates;
	}
	
	public static TraceableInfo newInfo(String aMessage, Object aFinder) {    	
		TraceableInfo retVal = new TraceableInfo(aMessage, aFinder);
   	    retVal.announce();
    	return retVal;
	}
}
