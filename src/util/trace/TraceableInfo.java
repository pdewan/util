package util.trace;

import java.util.Date;



public  class TraceableInfo extends Traceable {
	static boolean printDuplicates = true; // info means this is about steps, which should be duplicated by default
	static boolean printSource = false; // source printed when event class is the info first arg
	static boolean printTraceable = false; // traceable printed when firing class is the info source
	
	
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
			Class sourceClass = (finder instanceof Class)? ((Class) finder):finder.getClass();
			String aFinderMessage = printTraceable? "EvtType(" + this.getClass().getSimpleName() + ") " + aMessage:aMessage;
			String aTraceableMessage = printSource? "EvtSrc(" + sourceClass.getSimpleName() + ") " + aMessage:aMessage;
			Tracer.info(finder, aFinderMessage); // discriminate by event firer
			Tracer.info(this, aTraceableMessage); // discriminate by event
		}
	}
	public static boolean isPrintDuplicates() {
		return printDuplicates;
	}
	
	public static void setPrintDuplicates(boolean newVal) {
		printDuplicates = newVal;
	}
	
	public static TraceableInfo newInfo(String aMessage, Object aFinder) {    	
		TraceableInfo retVal = new TraceableInfo(aMessage, aFinder);
   	    retVal.announce();
    	return retVal;
	}
	public static boolean isPrintSource() {
		return printSource;
	}
	public static void setPrintSource(boolean printSource) {
		TraceableInfo.printSource = printSource;
	}
	public static boolean isPrintTraceable() {
		return printTraceable;
	}
	public static void setPrintTraceable(boolean printTraceable) {
		TraceableInfo.printTraceable = printTraceable;
	}
}
