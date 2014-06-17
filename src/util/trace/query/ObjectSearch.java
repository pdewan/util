package util.trace.query;

import util.annotations.Visible;
import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.console.ConsoleInput;

public class ObjectSearch extends TraceableInfo {
	Object previousObject;
	Object laterObject;
	Object expectedObject;
	public static final String PREVIOUS = "previous";
	public static final String LATER = "later";
	public static final String EXPECTED = "expected";

	public ObjectSearch(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject, Object aFinder) {
		super(aMessage, aFinder);
		previousObject = aPreviousObject;
		laterObject = aLaterObject;
	}
	public ObjectSearch(String aMessage, Object aPreviousObject, Object anExpectedObject, Object aLaterObject) {
		this(aMessage, aPreviousObject, anExpectedObject, aLaterObject, null);
	}
	@Visible(false)
	public Object getExpectedObject() {
		return expectedObject;
	}
	@Visible(false)
	public Object getPreviousObject() {
		return previousObject;
	}
	@Visible(false)
	public Object getLaterObject() {
		return laterObject;
	}
	public static String toString (Object aPreviousObject, Object anExpectedObject, Object aLaterObject) {
		return 
			aPreviousObject == null? "": PREVIOUS + Traceable.NESTED_LEFT_MARKER + (aPreviousObject) + Traceable.NESTED_RIGHT_MARKER
			+ " "	
			+ anExpectedObject == null? "": EXPECTED + Traceable.NESTED_LEFT_MARKER + (anExpectedObject) + Traceable.NESTED_RIGHT_MARKER
			
			+ aLaterObject == null? "": LATER + Traceable.NESTED_LEFT_MARKER + (aLaterObject) + Traceable.NESTED_RIGHT_MARKER;
	}
	
	public static String getPrevious(String aMessage) {
		return getNestedArgs(aMessage, PREVIOUS).get(0);
	}
	public static String getExpected(String aMessage) {
		return getNestedArgs(aMessage, EXPECTED).get(0);
	}
	public static String getLater(String aMessage) {
		return getNestedArgs(aMessage, LATER).get(0);
	}
	
	public static Traceable toTraceable(String aMessage) {
		return new ObjectSearch (aMessage, 
				getPrevious(aMessage),
				getExpected(aMessage),
				getLater(aMessage));
	}
	

}
