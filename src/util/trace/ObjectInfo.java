package util.trace;



public  class ObjectInfo extends TraceableInfo {
	Object targetObject;
	public ObjectInfo(String aMessage, Object anObject, Object aFinder) {
		super(aMessage, aFinder);	
		targetObject = anObject;
	}	
	public Object getTargetObject() {
		return targetObject;
	}
}
