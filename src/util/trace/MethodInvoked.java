package util.trace;

import util.misc.Common;


public class MethodInvoked extends ObjectInfo {	
	String className;

	String methodName;
	String signature;
	Object[] argList;
	
	
	public MethodInvoked(String aMessage, String aClassName, String aMethodName, String aSignature, Object[] anArgList, Object aFinder) {
		super(aMessage, aFinder, aFinder);
		className = aClassName;
		methodName = aMethodName;
		signature = aSignature;
		argList = anArgList;
		
	}	
	public static MethodInvoked newCase(String aClassName, String aMethodName, String aSignature, Object[] argList, Object aFinder) {
//		System.out.println(aClassName + aMethodName + aSignature + argList + aFinder);
//		String aMessage = "Generating graphics editor for:" + anObject;
//		DrawingEditorGenerationStarted retVal = new DrawingEditorGenerationStarted(aMessage, anObject, aFinder);
//		retVal.announce();
//		return retVal;
		
		String aMessage = aClassName + "." + aMethodName + " invoked with args:" + Common.toString(argList);
		MethodInvoked retVal = new MethodInvoked(aMessage, aClassName, aMethodName, aSignature, argList, aFinder);
		retVal.announce();
		return retVal;
	}
	public Object[] getArgList() {
		return argList;
	}
	
	public String getClassName() {
		return className;
	}
	public String getMethodName() {
		return methodName;
	}
	public String getSignature() {
		return signature;
	}
//	public static MethodInvoked newCase(String aClassName, String aMethodName, String aSignature,  Object aFinder) {
//		System.out.println(aClassName + aMethodName + aSignature + aFinder);
////		String aMessage = "Generating graphics editor for:" + anObject;
////		DrawingEditorGenerationStarted retVal = new DrawingEditorGenerationStarted(aMessage, anObject, aFinder);
////		retVal.announce();
////		return retVal;
//		return null;
//	}
//	public static void test(String aClassName, String aMethodName, String aSignature, Object aFinder,  Object[] argList) {
//		String aMessage = "Generating graphics editor for:" + anObject;
//		DrawingEditorGenerationStarted retVal = new DrawingEditorGenerationStarted(aMessage, anObject, aFinder);
//		retVal.announce();
//		return retVal;
//		newCase("foo", "bar", "foobar", null, argList);
//	}
	
	
	public static void main(String[] args) {
		MethodInvoked newVal = MethodInvoked.newCase("String", "split", "String split(String)", new Object[]{"foo"}, null);
		System.out.println(newVal.getSignature());
		
	}
	
	

}
