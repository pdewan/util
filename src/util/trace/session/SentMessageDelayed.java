package util.trace.session;



public class SentMessageDelayed extends MessageInfo{

	public SentMessageDelayed(String aMessage, Object aDataItem, Object aFinder) {
		super(aMessage, aDataItem, aFinder);
	}
	
	public static SentMessageDelayed newCase(
			Object aDataItem, Object aFinder) {			
		String aMessage = toString(aDataItem);
		SentMessageDelayed retVal = new SentMessageDelayed(aMessage, aDataItem, aFinder);
		retVal.announce();
		return retVal;
	}

}
