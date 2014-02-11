package util.trace;



public  class ObjectError extends TraceableError {
	Object targetObject;
	public ObjectError(String aMessage, Object anObject, Object aFinder) {
		super(aMessage, aFinder);	
		targetObject = anObject;
	}	
	public Object getTargetObject() {
		return targetObject;
	}
}
