package util.trace;


public  class ObjectWarning extends TraceableWarning {
	Object targetObject;
	public ObjectWarning(String aMessage, Object anObject, Object aFinder) {
		super(aMessage, aFinder);	
		targetObject = anObject;
	}	
	public Object getTargetObject() {
		return targetObject;
	}
}
