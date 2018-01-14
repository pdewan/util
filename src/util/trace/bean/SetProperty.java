package util.trace.bean;

import util.trace.TraceableInfo;

public class SetProperty extends TraceableInfo{

	protected String propertyName;
	protected String propertyValue;
	public SetProperty(String aMessage, Object aFinder,
			String aPropertyName, String aPropertyValue) {
		super(aMessage,  aFinder);
	}
	public static SetProperty newCase(Object aSource, String aPropertyName, String aPropertyValue) {    	
		String aMessage = aPropertyName + " = " + aPropertyValue;
		SetProperty retVal = new SetProperty(aMessage, aSource, aPropertyName, aPropertyValue);
    	retVal.announce();
    	return retVal;
	}
}
