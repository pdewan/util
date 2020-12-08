package util.introspect;

import java.beans.MethodDescriptor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;

import util.models.HashtableListener;
import util.models.RemotePropertyChangeListener;
import util.models.VectorListener;
import util.trace.Tracer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.Vector;
import java.util.Hashtable;

import javax.swing.JTable;
import javax.swing.JTree;


public class JavaIntrospectUtility {
	public static String INIT_SERIALIZED_OBJECT = "initSerializedObject";
	public static String VIEW_GETTER = "view";
	public static String VIEW_REFRESHER = "refreshView";
	// public static Vector dynamicMethodsVector =
	// uiGenerator.arrayToVector(DynamicMethods.dynamicMethods);

	static Object[] emptyArgs = {};
	static Class[] emptyArgTypes = {};

	public static boolean isVolatile(Method method) {
		return (method.getReturnType() == Void.TYPE && Modifier
				.isVolatile(method.getModifiers())); /*
													 * &&
													 * method.getName().toLowerCase
													 * ().lastIndexOf("animate")
													 * != -1);
													 */
	}

	// public static boolean isAsynchronous(Method method) {
	// //return isAsynchronous
	// (uiMethodInvocationManager.virtualMethod(method));
	// return (isVoid(method) &&
	// Modifier.isSynchronized(method.getModifiers()) );
	// }

	public static final String PRE = "pre";
	public static final String VALIDATE = PRE;

	public static String getTypeVariablesString(TypeVariable[] vars) {
		String retVal = "Of";
		for (int i = 0; i < vars.length; i++) {
			if (i == 0)
				retVal = "Of";
			retVal += vars[i].getName();
			if (i != vars.length - 1)
				retVal += "And";
		}
		return retVal;
	}
	public static boolean equalsHeader (Method method1, Method method2 ) {
		return method1 != null && method2 != null && 
		        method1.getName() == method2.getName() &&
				equalsSignature (method1, method2);
	}
	
	public static boolean equalsSignature (Method method1, Method method2 ) {
		Class[] pType1 = method1.getParameterTypes();
		Class rType1 = method1.getReturnType();
		Class[] pType2 = method2.getParameterTypes();
		Class rType2 = method2.getReturnType();
		if (pType1.length != pType2.length) return false;
		if (rType1 != rType2) return false;
		for (int i = 0; i < pType1.length; i++) 
			if (pType1[i] != pType2[i])
				return false;
		return true;
	}
	

	// public static String getParameterSignature(Class[] params) {
	// String retVal = "";
	// for (int i = 0; i < params.length; i++) {
	// Class nextClass = params[i];
	// //TypeVariable[] vars = nextClass.getTypeParameters();
	// if (nextClass.isArray()) {
	// Class componentType = nextClass.getComponentType();
	// if (componentType != null)
	// retVal += componentType.getSimpleName() + "Array";
	// } else
	// retVal += nextClass.getSimpleName() /*+ getTypeVariablesString(vars)*/;
	// /*
	// Class componentType = nextClass.getComponentType();
	// if (componentType != null)
	// retVal += "Of" + componentType.getSimpleName();
	// */
	// }
	// return retVal;
	// }
	// public static String nameWithSignature(Method m) {
	// return m.getName() + getParameterSignature(m);
	// }
	// public static String getParameterSignature(Method m) {
	// return getParameterSignature(m.getParameterTypes());
	// }
	//
	// public static boolean isPreEfficient(Method method, Method candidate) {
	// return candidate.getParameterTypes().length == 0 &&
	// isBoolean(candidate) &&
	// //("pre" + method.getName()).toLowerCase().equals(
	// ((PRE + method.getName()).toLowerCase().equals(
	// candidate.getName().toLowerCase()) ||
	// (PRE + method.getName() +
	// getParameterSignature(method)).toLowerCase().equals(
	// candidate.getName().toLowerCase()));
	//
	// }
	// static Map<Method, Method> preToMethod = new HashMap();
	// static Set<Method> falsePre = new HashSet();
	//
	// public static boolean isPre(Method method, Method candidate) {
	// if (!isBoolean(candidate) ||
	// !candidate.getName().toLowerCase().startsWith(PRE) ) {
	// //falsePre.add(candidate);
	// return false;
	// }
	// if (preToMethod.get(candidate) == method)
	// return true;
	// if (falsePre.contains(candidate))
	// return false;
	//
	//
	//
	// if (!(
	// (PRE + method.getName()).equalsIgnoreCase(candidate.getName()) ||
	// (PRE + method.getName() +
	// getParameterSignature(method)).equalsIgnoreCase(candidate.getName()))) {
	// //falsePre.add(candidate);
	// //Message.info(candidate +
	// " begins with a pre but its suffix does not match any other method in its class");
	//
	//
	// return false;
	// }
	//
	// /*
	// if (!isBoolean(candidate)) {
	// bus.uigen.Message.warning(candidate +
	// " not recognized as an enabling method as it does not return a boolean");
	// return false;
	// }
	// */
	// if (candidate.getParameterTypes().length == 0 ) {
	// preToMethod.put(candidate, method);
	// return true;
	// }
	// falsePre.add(candidate);
	// if (isValidate(method, candidate)) {
	//
	// Message.info(candidate +
	// " considered a validate rather than enabling method because it takes arguments");
	// return false;
	// } else {
	//
	// Message.warning(candidate + " not recognized as an enabling method of + "
	// + method + " because it takes arguments");
	// return false;
	// }
	//
	// /*
	// return candidate.getParameterTypes().length == 0 &&
	// isBoolean(candidate) &&
	// //("pre" + method.getName()).toLowerCase().equals(
	// ((PRE + method.getName()).toLowerCase().equals(
	// candidate.getName().toLowerCase()) ||
	// (PRE + method.getName() +
	// getParameterSignature(method)).toLowerCase().equals(
	// candidate.getName().toLowerCase()));
	// */
	//
	// }
	// static Map<Method, Method> validateToMethod = new HashMap();
	// static Set<Method> falseValidate = new HashSet();
	// public static boolean isValidateEfficient(Method method, Method
	// candidate) {
	// return
	// isBoolean(candidate) &&
	// (VALIDATE + method.getName()).toLowerCase().equals(
	// candidate.getName().toLowerCase()) &&
	// assignableFrom(candidate.getParameterTypes(), method.getParameterTypes())
	// ;
	// }
	// public static boolean isValidate(Method method, Method candidate) {
	// if (!isBoolean(candidate) ||
	// !candidate.getName().toLowerCase().startsWith(VALIDATE) )
	// return false;
	// if (validateToMethod.get(candidate) == method)
	// return true;
	// if (falseValidate.contains(candidate))
	// return false;
	//
	// if (!(VALIDATE + method.getName()).equalsIgnoreCase(
	// candidate.getName())) {
	// return false;
	// }
	// if (assignableFrom(candidate.getParameterTypes(),
	// method.getParameterTypes())) {
	// validateToMethod.put(candidate, method);
	// return true;
	// }
	// if (isPre(method, candidate))
	// return false;
	// falseValidate.add(candidate);
	// Message.warning(candidate + " not recognized as validate method of " +
	// method + " as their parameters do not match");
	// return false;
	//
	// }
	// public static boolean hasPre (Class c) {
	// Method[] methods = c.getMethods();
	// for (int i = 0; i < methods.length; i++) {
	// if (getPre(methods[i], c) != null)
	// return true;
	// }
	// return false;
	// }
	/*
	 * public static boolean assignableFrom (VirtualMethod method1,
	 * VirtualMethod method2) { return assignableFrom
	 * (method1.getParameterTypes(), method2.getParameterTypes()); }
	 */
	// public static boolean assignableFrom (Class[] from, Class[] to ) {
	//
	// if (from.length != to.length) return false;
	// for (int i = 0; i < from.length; i++)
	// if (!from[i].isAssignableFrom(to[i]))
	// return false;
	// return true;
	// }
	// public static boolean equalSignature (Method method1, Method method2 ) {
	// Class[] pType1 = method1.getParameterTypes();
	// Class rType1 = method1.getReturnType();
	// Class[] pType2 = method2.getParameterTypes();
	// Class rType2 = method2.getReturnType();
	// if (pType1.length != pType2.length) return false;
	// if (rType1 != rType2) return false;
	// for (int i = 0; i < pType1.length; i++)
	// if (pType1[i] != pType2[i])
	// return false;
	// return true;
	// }
	//
	public static boolean isUndo(String method, String candidate) {
		return candidate.toLowerCase().equals("undo" + method.toLowerCase());
	}

	// public static Method getUndo (Object parentObject, Method method) {
	// Method[] methods = RemoteSelector.getClass(parentObject).getMethods();
	// for (int i = 0; i < methods.length; i++) {
	// Method candidate = methods[i];
	// if (isUndo(method.getName(), candidate.getName()) &&
	// equalSignature(method, candidate))
	// return candidate;
	//
	// }
	// return null;
	//
	// }

	public static boolean isPre(Method method, Method candidate) {

		// return isPre (uiMethodInvocationManager.virtualMethod(method),
		// uiMethodInvocationManager.virtualMethod(candidate));
		// we need efficiency here
		return candidate.getParameterTypes().length == 0
				&& candidate.getReturnType() == Boolean.TYPE
				&& ("pre" + method.getName()).toLowerCase().equals(
						candidate.getName().toLowerCase());
	}

	// public static boolean isPre(Method candidate) {
	// return //candidate.getParameterTypes().length == 0 &&
	// isBoolean(candidate) &&
	// candidate.getName().startsWith("pre");
	// }
	public static boolean isPre(Method candidate) {
		// return isPre (uiMethodInvocationManager.virtualMethod(candidate));
		return candidate.getParameterTypes().length == 0
				&& candidate.getReturnType() == Boolean.TYPE
				&& candidate.getName().startsWith("pre");
	}

	public static Method getPre(Method method, Class c) {
		if (method == null || c == null)
			return null;
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isPre(method, methods[i]))
				return methods[i];
		return null;

	}

	// public static Method getPre(Method method, Class c) {
	// if (method == null || c == null) return null;
	// if (method.isDynamicCommand()) { // dynamic method
	// return DynamicMethods.getDynamicCommandPre(c);
	// }
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isPre(method, methods[i])) return methods[i];
	// return null;
	//
	// }
	// public static Method getValidate(Method method, Class c) {
	// if (method == null || c == null) return null;
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isValidate(method, methods[i])) return methods[i];
	// return null;
	//
	// }

	public static boolean isGetter(Method method) {
		// return isGetter (uiMethodInvocationManager.virtualMethod(method));

		String name = method.getName();
		if (name.startsWith("get") && !name.equals("get")) {
			if (method.getParameterTypes().length == 0
					&& !method.getReturnType().equals(Void.TYPE))
				return true;
			else
				return false;
		}
		return false;

	}

	// public static boolean isGetter(Method method) {
	// String name = method.getName();
	// if (name.startsWith("get") && !name.equals("get")) {
	// if (method.getParameterTypes().length == 0 &&
	// !method.getReturnType().equals(Void.TYPE))
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	public static boolean isGetter(Method method, String propertyName) {
		String name = method.getName();
		if (name.equals("get" + propertyName)
				|| name.equals("is" + propertyName)) {
			if (method.getParameterTypes().length == 0
					&& !method.getReturnType().equals(Void.TYPE))
				return true;
			else
				return false;
		}
		return false;
	}

	// public static boolean isStaticGetter(Method method) {
	// if (Modifier.isStatic(method.getModifiers()))
	// return isGetter(method);
	// else
	// return false;
	// }
	// public static boolean isStaticSetter(Method method) {
	// if (Modifier.isStatic(method.getModifiers()))
	// return isGetter(method);
	// else
	// return false;
	// }
	//
	// public static boolean isIncrementer(Method method) {
	// String name = method.getName();
	// if (name.toLowerCase().equals("increment") &&
	// method.getParameterTypes().length == 0)
	// return true;
	// else
	// return false;
	//
	// }
	// public static boolean isDecrementer(Method method) {
	// String name = method.getName();
	// if (name.toLowerCase().equals("decrement") &&
	// method.getParameterTypes().length == 0)
	// return true;
	// else
	// return false;
	//
	// }
	// public static boolean isAssignableFrom (Class to, Class from) {
	// return to.isAssignableFrom(from) ||
	// (DefaultRegistry.getWrapper(to) != null &&
	// DefaultRegistry.getWrapper(to).isAssignableFrom(from));
	// }
	// public static boolean isChildIncrementer(Method method, Class childType)
	// {
	//
	// String name = method.getName();
	// if (name.toLowerCase().equals("increment") &&
	// method.getParameterTypes().length == 1 &&
	// //method.getParameterTypes()[0].isAssignableFrom( childType))
	// isAssignableFrom(method.getParameterTypes()[0], childType))
	// return true;
	// else
	// return false;
	//
	// }
	// public static boolean isChildIncrementer(Method method, String p) {
	//
	// String name = method.getName();
	// if (name.toLowerCase().equals("increment" + p.toLowerCase()) &&
	// method.getParameterTypes().length == 0)
	// //&&
	// //method.getParameterTypes()[0].isAssignableFrom( childType))
	// //isAssignableFrom(method.getParameterTypes()[0], childType))
	// return true;
	// else
	// return false;
	//
	// }
	// public static boolean isChildDecrementer(Method method, Class childType)
	// {
	// String name = method.getName();
	// if (name.toLowerCase().equals("decrement") &&
	// method.getParameterTypes().length == 1 &&
	// //method.getParameterTypes()[0].isAssignableFrom( childType))
	// isAssignableFrom(method.getParameterTypes()[0], childType))
	// return true;
	// else
	// return false;
	//
	// }
	// public static boolean isChildDecrementer(Method method, String p) {
	//
	// String name = method.getName();
	// if (name.toLowerCase().equals("decrement" + p.toLowerCase()) &&
	// method.getParameterTypes().length == 0)
	// //&&
	// //method.getParameterTypes()[0].isAssignableFrom( childType))
	// //isAssignableFrom(method.getParameterTypes()[0], childType))
	// return true;
	// else
	// return false;
	//
	// }

	public static String getPropertyName(Method method) {
		// return getPropertyName
		// (uiMethodInvocationManager.virtualMethod(method));

		String name = method.getName();
		return name.substring(3, name.length());

	}

	// public static String getPropertyName(Method method) {
	// String name = method.getName();
	// return name.substring(3, name.length());
	// }

	// //if class implements the get<PropName>Alternatives method then this
	// method returns those string alternatives
	// public static String[] getPropertyAlternatives(Object realObject, String
	// _propertyName) {
	// //dunno if object or class is best to pass in
	// //property name should have 1st letter alread capitalized.
	// try {
	// //System.out.println("PropertyName is " + _propertyName);
	// //String propertyName = _propertyName.toLowerCase();
	// String propertyName = _propertyName;
	//
	// //System.exit(0);
	//
	//
	// String methodName = propertyName+"Alternatives";
	// System.out.println("uibean looking for " + methodName);
	// Method[] methods = RemoteSelector.getClass(realObject).getMethods();
	// boolean foundMethod = false;
	// int i = 0;
	// while (!foundMethod && (i < methods.length)) {
	// System.out.println("..." + methods[i].getName());
	// if (methods[i].getName().equals(methodName)) { //if found the method
	// foundMethod = true;
	// System.out.println("uibean found method and returning array");
	// // System.exit(0);
	// return ((String[])methods[i].invoke(realObject,null)); //return the
	// vector of alternatives
	// }
	// else
	// i++;
	// }//end while
	// return null;
	// }//end try
	//
	// catch (Exception er) {System.out.println("uibean getPropAlt exception");
	// return null;
	// }
	//
	// }//end getPAlt

	// //if the class declares any constants/finals of a property and its type
	// then these can be alternatives in the
	// //JComboBox e.g. MALESEX = 2; FEMALESEX = 1; and getSex(...) is
	// implemented then Sex should be filled w/ those constants
	// public static Vector getPropertyTypeFinals(Object realObject, String
	// _propertyName, Class propertyClass) {
	//
	//
	// //System.out.println("Getting finals for " + _propertyName +
	// "  of type  " + propertyClass.getName() + " " + Modifier.FINAL);
	// Vector matchedFields = new Vector();
	// try {
	// FieldProxy[] fields = RemoteSelector.getClass(realObject).getFields();
	// System.out.println("Getting finals for" + _propertyName +
	// "with field count-" + fields.length);
	// int i = 0;
	// while (i < fields.length) {
	// // System.out.println("!!!working on: "+ fields[i].getName() + " " +
	// fields[i].getModifiers() + " "
	// +primitiveClassList.getWrapperType(fields[i].getType()).getName());
	// if (fields[i].getModifiers() == Modifier.FINAL + Modifier.PUBLIC) { //if
	// the field is final then now check name
	// //add 1 because of the public
	// if (fields[i].getName().endsWith(_propertyName.toUpperCase())) { //if it
	// ends with the caps of propName
	// if
	// (primitiveClassList.getWrapperType(fields[i].getType()).getName().equals(propertyClass.getName()))
	// {//if they are the same type
	// matchedFields.addElement(fields[i]); //add it to the vector
	// //System.out.println("!!!finals added: "+ fields[i].getName());
	//
	// }
	// }
	// }
	// i++;
	// }//endwhile
	// }
	//
	// catch (Exception ex) {System.out.println(ex.toString());}
	//
	// //System.out.println("found final # " + matchedFields.size());
	// return matchedFields;
	//
	// }

	// public static Enumeration getAllPropertyNames(Object realObject) {
	// Hashtable propHash = new Hashtable();
	// //Method[] methods = realObject.getClass().getMethods();
	// Method[] methods = getMethods(RemoteSelector.getClass(realObject));
	//
	// for (int i=0; i < methods.length; i++) {//for each method
	// if (isSimplePropertyMethod(methods[i])) { //if it's a property method
	//
	// String aProperty = methods[i].getName().substring(3); //take the 3 letter
	// set/get off
	// propHash.put(aProperty,aProperty); //hash it ...assume get*sets will
	// overwrite eachother
	// }
	// }
	//
	// if (!propHash.isEmpty())
	// return propHash.elements();
	// else
	// return null;
	//
	// }//end getPNam
	//
	// public static boolean isSetter(Method method) {
	// String name = method.getName();
	// if (name.startsWith("set")) {
	// if (method.getParameterTypes().length == 1 &&
	// method.getReturnType().equals(method.getDeclaringClass().voidType()))
	// return true;
	// else
	// return false;
	// }
	// else
	// return false;
	//
	// }
	// public static boolean isGeneralizedSetter(Method method) {
	// String name = method.getName();
	// return (name.startsWith("set")) ;
	// }
	// public static boolean isGeneralizedGetter(Method method) {
	// String name = method.getName();
	// return (name.startsWith("get"));
	// }

	public static boolean isSetter(Method method) {
		// return isSetter(uiMethodInvocationManager.virtualMethod(method));

		String name = method.getName();
		if (name.startsWith("set")) {
			if (method.getParameterTypes().length == 1
					&& method.getReturnType().equals(Void.TYPE))
				return true;
			else
				return false;
		} else
			return false;

	}

	// public static boolean isComplexSetter(Method method) {
	// if (isSetter(method)) {
	// if (uiGenerator.isPrimitiveClass(method.getParameterTypes()[0]))
	// return false;
	// else
	// return true;
	// }
	// return false;
	// }
	//
	// public static boolean isSimplePropertyMethod(Method method) {
	// String name = method.getName();
	// if (name.startsWith("get") && !name.equals("get")) {
	// if (method.getParameterTypes().length == 0 &&
	// !method.getReturnType().equals(Void.TYPE))
	// return true;
	// else
	// return false;
	// }
	// else if (name.startsWith("set")) {
	// if (method.getParameterTypes().length == 1 &&
	// method.getReturnType().equals(Void.TYPE))
	// return true;
	// else
	// return false;
	// }
	// else
	// return false;
	// }
	public static boolean isSimplePropertyMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("get") && !name.equals("get")) {
			if (method.getParameterTypes().length == 0
					&& !method.getReturnType().equals(Void.TYPE))
				return true;
			else
				return false;
		} else if (name.startsWith("set")) {
			if (method.getParameterTypes().length == 1
					&& method.getReturnType().equals(Void.TYPE))
				return true;
			else
				return false;
		} else
			return false;
	}

	// public static boolean isBooleanPropertyMethod(Method method) {
	// Class c = method.getDeclaringClass();
	// if (isVector(method.getDeclaringClass()) &&
	// method.getName().equals("isEmpty"))
	// return false;
	// String name = method.getName();
	// if (name.startsWith("is") || name.startsWith("get")) {
	// if (method.getParameterTypes().length == 0 &&
	// //method.getReturnType().equals(Boolean.TYPE))
	// method.getReturnType().equals(Boolean.TYPE))
	// return true;
	// else
	// return false;
	// }
	// else if (name.startsWith("set")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 1 &&
	// //params[0].equals(Boolean.TYPE) &&
	// params[0].equals(Boolean.TYPE) &&
	// method.getReturnType().equals(c.voidType()))
	// return true;
	// else
	// return false;
	// }
	// else
	// return false;
	// }
	public static boolean isBooleanPropertyMethod(Method method) {
		if (isVector(method.getDeclaringClass())
				&& method.getName().equals("isEmpty"))
			return false;
		String name = method.getName();
		if (name.startsWith("is") || name.startsWith("get")) {
			if (method.getParameterTypes().length == 0
					&& method.getReturnType().equals(Boolean.TYPE))
				return true;
			else
				return false;
		} else if (name.startsWith("set")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(Boolean.TYPE)
					&& method.getReturnType().equals(Void.TYPE))
				return true;
			else
				return false;
		} else
			return false;
	}

	// public static boolean isIndexedPropertyMethod(Method method) {
	// Class c = method.getDeclaringClass();
	// String name = method.getName();
	// if (name.startsWith("get")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 1 &&
	// params[0].equals(c.integerType()) &&
	// !method.getReturnType().equals(c.voidType()))
	// return true;
	// else
	// return false;
	// }
	// else if (name.startsWith("set")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 2 &&
	// params[0].equals(c.integerType()))
	// return true;
	// else
	// return false;
	// }
	// else
	// return false;
	// }
	public static boolean isIndexedPropertyMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("get")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(Integer.TYPE)
					&& !method.getReturnType().equals(Void.TYPE))
				return true;
			else
				return false;
		} else if (name.startsWith("set")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 2 && params[0].equals(Integer.TYPE))
				return true;
			else
				return false;
		} else
			return false;
	}

	// public static boolean isBeanPropertyMethod(Method method) {
	// return (isSimplePropertyMethod(method) ||
	// isBooleanPropertyMethod(method) ||
	// isIndexedPropertyMethod(method));
	// }
	public static boolean isBeanPropertyMethod(Method method) {
		return (isSimplePropertyMethod(method)
				|| isBooleanPropertyMethod(method) || isIndexedPropertyMethod(method));
	}

	/*
	 * public static util.Explanation explanationClass() { return
	 * ClassSelector.Class(util.Explanation.class); } public static Class
	 * htmlDocumentationClass() { return
	 * ClassSelector.Class(util.HTMLDocumentation.class); } public static Class
	 * keywordsClass() { return ClassSelector.Class(util.Keywords.class); }
	 */
	// public static Class vectorClass() {
	// return RemoteSelector.Class(Vector.class);
	// }
	// public static Class hashtableClass() {
	// return RemoteSelector.Class(Hashtable.class);
	// }
	// public static Class colorClass() {
	// return RemoteSelector.Class(java.awt.Color.class);
	// }
	// public static Class pointClass() {
	// return RemoteSelector.Class(java.awt.Point.class);
	// }
	// public static Class remoteShapeClass() {
	// return RemoteSelector.Class(RemoteShape.class);
	// }
	// public static Class remotePointClass() {
	// return RemoteSelector.Class(RemotePoint.class);
	// }
	// public static Class remoteLineClass() {
	// return RemoteSelector.Class(RemoteLine.class);
	// }
	// public static Class remoteTextClass() {
	// return RemoteSelector.Class(RemoteText.class);
	// }
	// public static Class remoteRectangleClass() {
	// return RemoteSelector.Class(RemoteRectangle.class);
	// }
	// public static Class remoteOvalClass() {
	// return RemoteSelector.Class(RemoteOval.class);
	// }
	// public static Class pointModelClass() {
	// return RemoteSelector.Class(PointModel.class);
	// }
	//
	// public static Class labelModelClass() {
	// return RemoteSelector.Class(LabelModel.class);
	// }
	// public static Class arcModelClass() {
	// return RemoteSelector.Class(ArcModel.class);
	// }
	// public static Class curveModelClass() {
	// return RemoteSelector.Class(CurveModel.class);
	// }
	// public static Class rectangleModelClass() {
	// return RemoteSelector.Class(RectangleModel.class);
	// }
	// public static Class ovalModelClass() {
	// return RemoteSelector.Class(OvalModel.class);
	// }
	// public static Class lineModelClass() {
	// return RemoteSelector.Class(LineModel.class);
	// }
	// public static Class textModelClass() {
	// return RemoteSelector.Class(TextModel.class);
	// }
	//
	public static boolean isIndexOfMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("indexOf")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 &&
			// params[0].equals(Object.class) &&
					Integer.TYPE.isAssignableFrom(method.getReturnType()))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isElementAtMethod(Method method, int numIndices) {
		String name = method.getName();
		if (name.startsWith("elementAt")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(Integer.TYPE)
					&& (Object.class).isAssignableFrom(method.getReturnType()))
				return true;
			else
				return false;
		}
		return false;
	}

	/*
	 * public static boolean isIndexOfMethod(VirtualMethod method, int
	 * numIndices) { String name = method.getName(); if
	 * (name.startsWith("indexOf")) { Class[] params =
	 * method.getParameterTypes(); if (params.length == 1 &&
	 * //params[0].equals(Integer.TYPE) && method.getReturnType() ==
	 * Integer.TYPE) return true; else return false; } return false; }
	 */
	// public static boolean isElementAtMethod(Method method, int numIndices) {
	// String name = method.getName();
	// //if (name.startsWith("elementAt")) {
	// if (name.startsWith("insert")) return false;
	// if (name.startsWith("remove")) return false;
	// if (isInsertElementAtMethod(method)) return false;
	// if (isRemoveElementMethod(method)) return false;
	// if (name.endsWith("At")) {
	// Class[] params = method.getParameterTypes();
	// if (checkIndices (params, numIndices) &&
	// (Object.class).isAssignableFrom( method.getReturnType()))
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	// public static boolean isElementAtMethod(Method method) {
	// return isElementAtMethod(method, 1);
	//
	// }
	// public static boolean checkIndices (Class[] params, int numIndices) {
	// if (params.length != numIndices) return false;
	// for (int i = 0; i < numIndices; i++) {
	// if (!params[i].equals(params[i].integerType())) return false;
	// }
	// return true;
	// }
	public static boolean isElementAtMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("elementAt")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(Integer.TYPE)
					&& (Object.class).isAssignableFrom(method.getReturnType()))
				return true;
			else
				return false;
		}
		return false;
	}
	public static boolean isGetMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("get")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(Integer.TYPE)
					&& (Object.class).isAssignableFrom(method.getReturnType()))
				return true;
			else
				return false;
		}
		return false;
	}

	// public static boolean isSetElementAtMethod(Method method) {
	// String name = method.getName();
	// if (name.startsWith("setElementAt")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 2 &&
	// Object.class.isAssignableFrom(params[0]) &&
	// params[1].equals(Integer.TYPE) )
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	public static boolean isSetElementAtMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("setElementAt")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 2 && Object.class.isAssignableFrom(params[0])
					&& params[1].equals(Integer.TYPE))
				return true;
			else
				return false;
		}
		return false;
	}

	// public static boolean isAddElementMethod(Method method) {
	//
	// String name = method.getName();
	// //if (name.startsWith("addElement")) {
	// if (name.startsWith("add")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 1 &&
	// Object.class.isAssignableFrom(params[0]))
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	// public static Class getAddElementClass(Method method) {
	//
	// String name = method.getName();
	// //if (name.startsWith("addElement")) {
	// if (name.startsWith("add")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 1 &&
	// Object.class.isAssignableFrom(params[0]))
	// return params[0];
	// else
	// return null;
	// }
	// return null;
	// }
	// public static Class getVectorElementClass(Class vectorClass) {
	// if (vectorClass == vectorClass())
	// return vectorClass.objectClass();
	// else return getAddElementClass(getAddElementMethod(vectorClass));
	// }
	//
	//
	public static boolean isInsertElementAtMethod(Method method) {
		String name = method.getName();
		// if (name.startsWith("insertElementAt")) {
		if (name.startsWith("insert") && name.endsWith("At")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 2 && Object.class.isAssignableFrom(params[0])
					&& params[1].equals(Integer.TYPE))
				return true;
			else
				return false;
		}
		return false;
	}

	//
	// public static boolean isRemoveElementMethod(Method method) {
	// String name = method.getName();
	// if (name.startsWith("removeElement")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 1 &&
	// Object.class.isAssignableFrom(params[0]))
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	public static boolean isRemoveElementMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("removeElement")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && Object.class.isAssignableFrom(params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	// public static boolean isRemoveElementAtMethod(Method method) {
	// String name = method.getName();
	// //if (name.startsWith("removeElementAt")) {
	// if (name.startsWith("remove") && name.endsWith("At")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 1 &&
	// params[0].equals(Integer.TYPE) )
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	public static boolean isRemoveElementAtMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("removeElementAt")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(Integer.TYPE))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isPutMethod(Method method) {

		String name = method.getName();
		if (name.startsWith("put")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 2 && Object.class.isAssignableFrom(params[0])
					&& Object.class.isAssignableFrom(params[1]))
				return true;
			else
				return false;
		}
		return false;
	}

	// public static boolean isPutMethod(Method method) {
	//
	// String name = method.getName();
	// if (name.startsWith("put")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 2 &&
	// Object.class.isAssignableFrom(params[0]) &&
	// Object.class.isAssignableFrom(params[1]))
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	public static boolean isRemoveMethod(Method method) {

		String name = method.getName();
		if (name.startsWith("remove")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && Object.class.isAssignableFrom(params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isTableGetMethod(Method method) {
		// return isGetMethod (uiMethodInvocationManager.virtualMethod(method));

		String name = method.getName();
		if (name.equals("get")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& (Object.class).isAssignableFrom(method.getReturnType()))
				return true;
			else
				return false;
		}
		return false;

	}

	// public static boolean isGetMethod(Method method) {
	// String name = method.getName();
	// if (name.equals("get")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 1 &&
	// (Object.class).isAssignableFrom( method.getReturnType()))
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }

	// public static boolean isIsEmptyMethod(Method method) {
	// String name = method.getName();
	// if (name.toLowerCase().equals("isempty")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 0)
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	// public static boolean isSizeMethod(Method method) {
	// String name = method.getName();
	// if (name.startsWith("size")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 0 &&
	// method.getReturnType().equals(Integer.TYPE))
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	public static boolean isSizeMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("size")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 0
					&& method.getReturnType().equals(Integer.TYPE))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isEnumerationGetter(Method method) {

		String name = method.getName();
		/*
		 * if (name.startsWith("elements")) {
		 */

		Class[] params = method.getParameterTypes();
		if (params.length == 0 &&
		// method.getReturnType().isAssignableFrom(Enumeration.class)) {
				(Enum.class).isAssignableFrom(method.getReturnType())) {
			// System.out.println(method.getReturnType().getName());
			return true;
		} else
			return false;
		/*
		 * } return false;
		 */

	}

	// public static boolean isElementsMethod(Method method) {
	//
	// String name = method.getName();
	//
	// if (name.startsWith("elements")) {
	//
	// Class[] params = method.getParameterTypes();
	// if (params.length == 0 &&
	// //method.getReturnType().isAssignableFrom(Enumeration.class)) {
	// (method.getDeclaringClass().enumerationClass()).isAssignableFrom(method.getReturnType()))
	// {
	// //System.out.println(method.getReturnType().getName());
	// return true;
	// } else
	// return false;
	//
	// }
	// return false;
	//
	//
	// }
	public static boolean isElementsMethod(Method method) {

		String name = method.getName();

		if (name.startsWith("elements")) {

			Class[] params = method.getParameterTypes();
			if (params.length == 0 &&
			// method.getReturnType().isAssignableFrom(Enumeration.class)) {
					(Enumeration.class)
							.isAssignableFrom(method.getReturnType())) {
				// System.out.println(method.getReturnType().getName());
				return true;
			} else
				return false;

		}
		return false;

	}

	// public static boolean isVariablePropertyMethod (Method method) {
	// return JavaIntrospectUtility.isElementAtMethod(method) ||
	// JavaIntrospectUtility.isElementsMethod(method) ||
	// JavaIntrospectUtility.isIndexedPropertyMethod(method) ||
	// JavaIntrospectUtility.isKeysMethod(method) ||
	// JavaIntrospectUtility.isSizeMethod(method) ||
	// JavaIntrospectUtility.isSetElementAtMethod(method) ||
	// JavaIntrospectUtility.isRemoveElementAtMethod(method) ||
	// JavaIntrospectUtility.isRemoveElementMethod(method) ||
	// JavaIntrospectUtility.isPre(method);
	//
	// }
	public static boolean isVariablePropertyMethod(Method method) {
		return JavaIntrospectUtility.isElementAtMethod(method)
				|| JavaIntrospectUtility.isElementsMethod(method)
				|| JavaIntrospectUtility.isIndexedPropertyMethod(method)
				|| JavaIntrospectUtility.isKeysMethod(method)
				|| JavaIntrospectUtility.isSizeMethod(method)
				|| JavaIntrospectUtility.isSetElementAtMethod(method)
				|| JavaIntrospectUtility.isRemoveElementAtMethod(method)
				|| JavaIntrospectUtility.isRemoveElementMethod(method)
				|| JavaIntrospectUtility.isPre(method);

	}

	// public static boolean isKeysMethod(Method method) {
	//
	// String name = method.getName();
	// if (name.startsWith("keys")) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 0 &&
	// //method.getReturnType().isAssignableFrom(Enumeration.class)) {
	// (method.getDeclaringClass().enumerationClass()).isAssignableFrom(method.getReturnType()))
	// {
	// //System.out.println(method.getReturnType().getName());
	// return true;
	// } else
	// return false;
	// }
	// return false;
	// }
	// public static boolean isChecker (Method method) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 0 &&
	// //method.getReturnType().isAssignableFrom(Enumeration.class)) {
	// (method.getDeclaringClass().booleanType()).isAssignableFrom(method.getReturnType()))
	// {
	// //System.out.println(method.getReturnType().getName());
	// return true;
	// } else
	// return false;
	//
	//
	// }
	// public static boolean isAssignableFrom (Class[] to, Class[] from) {
	// if (to.length != from.length) return false;
	// for (int i= 0; i < to.length; i++)
	// if (!to[i].isAssignableFrom(from[i])) return false;
	// return true;
	//
	//
	// }
	// public static boolean isChecker (Method method, Class[] arguments) {
	// Class[] params = method.getParameterTypes();
	// if (isAssignableFrom(params, arguments) &&
	// //method.getReturnType().isAssignableFrom(Enumeration.class)) {
	// (method.getDeclaringClass().booleanType()).isAssignableFrom(method.getReturnType()))
	// {
	// //System.out.println(method.getReturnType().getName());
	// return true;
	// } else
	// return false;
	//
	//
	// }
	//
	// public static boolean isViewRefresher(Method method) {
	// Class[] params = method.getParameterTypes();
	// if (params.length != 0 ) return false;
	// return method.getName().equals(JavaIntrospectUtility.VIEW_REFRESHER);
	// }
	//
	// public static boolean isSetChecker (Method method) {
	// Class[] params = method.getParameterTypes();
	// if (params.length != 1 ) return false;
	// if (params[0].isAssignableFrom(method.getDeclaringClass().booleanType()))
	// return true;
	// return false;
	// /*
	// return false;
	//
	// &&
	// //method.getReturnType().isAssignableFrom(Enumeration.class)) {
	// (Boolean.TYPE).isAssignableFrom(method.getReturnType())) {
	// //System.out.println(method.getReturnType().getName());
	// return true;
	// } else
	// return false;
	// */
	//
	// }
	//
	// public static boolean isChecker (Method method, Class argType) {
	// Class from[] = {argType};
	// return isChecker(method, from);
	// }
	//
	//
	// public static boolean isIsEditableKey (Method method) {
	// String methodNameLC = method.getName().toLowerCase();
	// return (methodNameLC.equals("iseditablekey") ||
	// methodNameLC.equals("iskeyeditable"))
	// && isChecker(method, Object.class);
	// }
	//
	// public static boolean isIsEditableElement (Method method) {
	// String methodNameLC = method.getName().toLowerCase();
	// return (methodNameLC.equals("iseditableelement") ||
	// methodNameLC.equals("iseditable") ||
	// methodNameLC.equals("iseditablevalue") ||
	// methodNameLC.equals("isvalueeditable") ||
	// methodNameLC.equals("iselementeditable"))
	// && isChecker(method, Object.class);
	// }
	// public static boolean isIsRemovable (Method method) {
	// return method.getName().toLowerCase().startsWith("isremovable") &&
	// isChecker(method, Object.class);
	// }
	// public static Method getIsEditableKey(Class c) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isIsEditableKey(methods[i])) return methods[i];
	// return null;
	// }
	// public static Method getIsEditableElement(Class c) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isIsEditableElement(methods[i])) return methods[i];
	// return null;
	// }
	// public static Method getViewRefresher (Object viewRefresher) {
	// if (viewRefresher == null)
	// return null;
	// Class viewClass = RemoteSelector.getClass(viewRefresher);
	// Method[] methods = getMethods(viewClass);
	// for (int i = 0; i < methods.length; i++)
	// if (isViewRefresher(methods[i])) return methods[i];
	// return null;
	//
	// }
	// public static Method getIsRemovable(Class c) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isIsRemovable(methods[i])) return methods[i];
	// return null;
	// }
	public static boolean isKeysMethod(Method method) {

		String name = method.getName();
		if (name.startsWith("keys")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 0 &&
			// method.getReturnType().isAssignableFrom(Enumeration.class)) {
					(Enumeration.class)
							.isAssignableFrom(method.getReturnType())) {
				// System.out.println(method.getReturnType().getName());
				return true;
			} else
				return false;
		}
		return false;
	}

	public static boolean isGetIntMethod(Method method, String s) {
		String name = method.getName();
		if (name.equals("get" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 0
					&& method.getReturnType().equals(Integer.TYPE))
				return true;
			else
				return false;
		}
		return false;
	}

	// public static boolean isGetPointMethod(Method method, String s) {
	// String name = method.getName();
	// if (name.equals("get" + s)) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 0 &&
	// //method.getReturnType().equals(Integer.TYPE))
	// whichShape(method.getReturnType()) == JavaIntrospectUtility.POINT_SHAPE)
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }

	public static boolean isGetMethod(Method method, String s, Class c) {
		// return isGetMethod (uiMethodInvocationManager.virtualMethod
		// (method));

		String name = method.getName();
		if (name.equals("get" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 0 && method.getReturnType().equals(c))
				return true;
			else
				return false;
		}
		return false;

	}

	// public static boolean isGetMethod(Method method, String s, Class c) {
	// String name = method.getName();
	// if (name.equals("get" + s)) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 0 &&
	// method.getReturnType().equals(c))
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	public static boolean isGetOrIsMethod(Method method, String s, Class c) {
		String name = method.getName();
		if (name.equals("get" + s) || name.equals("is" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 0 && method.getReturnType().equals(c))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isGetOrIsBooleanMethod(Method method, String s) {
		String name = method.getName();
		if (name.equals("get" + s) || name.equals("is" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 0
					&& method.getReturnType().equals(Boolean.class)
					|| method.getReturnType().equals(Boolean.TYPE))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isGeneralizedGetMethod(Method method, String s,
			Class c) {
		String name = method.getName();
		if (name.equals("get" + s)) {
			Class[] params = method.getParameterTypes();
			if ( // params.length == 0 &&
			method.getReturnType().equals(c))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isSetIntMethod(Method method, String s) {
		String name = method.getName();
		if (name.equals("set" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(Integer.TYPE))
				return true;
			else
				return false;
		}
		return false;
	}

	// public static boolean isSetPointMethod(Method method, String s) {
	// String name = method.getName();
	// if (name.equals("set" + s)) {
	// Class[] params = method.getParameterTypes();
	// if (params.length == 1 &&
	// //params[0].equals(Integer.TYPE) )
	// whichShape(params[0]) == JavaIntrospectUtility.POINT_SHAPE)
	// return true;
	// else
	// return false;
	// }
	// return false;
	// }
	public static boolean isSetMethod(Method method, String s, Class c) {
		String name = method.getName();
		if (name.equals("set" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(c))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isSetBooleanMethod(Method method, String s) {
		String name = method.getName();
		if (name.equals("set" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& (params[0].equals(Boolean.TYPE) || params[0]
							.equals(Boolean.class)))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isGetStringMethod(Method method, String s) {
		String name = method.getName();
		if (name.equals("get" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 0
					&& method.getReturnType().equals(String.class))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isSetStringMethod(Method method, String s) {
		String name = method.getName();
		if (name.equals("set" + s)) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 && params[0].equals(String.class))
				return true;
			else
				return false;
		}
		return false;
	}

	// public static Method getIncrementer(Class c) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isIncrementer(methods[i])) return methods[i];
	// return null;
	// }
	//
	//
	// public static Method getDecrementer(Class c) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isDecrementer(methods[i])) return methods[i];
	// return null;
	// }
	// public static Method getChildIncrementer(Class c, Class childType) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isChildIncrementer(methods[i], childType)) return methods[i];
	// return null;
	// }
	//
	// public static Method getChildDecrementer(Class c, Class childType) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isChildDecrementer(methods[i], childType)) return methods[i];
	// return null;
	// }
	// public static Method getChildIncrementer(Class c, String p) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isChildIncrementer(methods[i], p)) return methods[i];
	// return null;
	// }
	// public static Method getChildIncrementer(ClassDescriptorInterface cd,
	// String p) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// MethodDescriptorProxy[] methods = cd.getMethodDescriptors();
	// for (int i = 0; i < methods.length; i++) {
	// Method vm = VirtualMethodDescriptor.getVirtualMethod(methods[i]);
	// if (isChildIncrementer(vm, p)) return vm;
	// }
	// return null;
	// }
	// public static Method getChildDecrementer(Class c, String p) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isChildDecrementer(methods[i], p)) return methods[i];
	// return null;
	// }
	// public static Method getChildDecrementer(ClassDescriptorInterface cd,
	// String p) {
	// //Object[] params = {};
	// //Method[] methods = c.getMethods();
	// MethodDescriptorProxy[] methods = cd.getMethodDescriptors();
	// for (int i = 0; i < methods.length; i++) {
	// Method vm = VirtualMethodDescriptor.getVirtualMethod(methods[i]);
	// if (isChildDecrementer(vm, p)) return vm;
	// }
	// return null;
	// }
	public static Method getSizeMethod(Class c) {
		// Object[] params = {};
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isSizeMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getSizeMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getSizeMethod(cp);
	// return vm.getMethod();
	// }

	public static Method getElementAtMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isElementAtMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getElementAtMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getElementAtMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	// //return getElementAtMethod (c, 1);
	// }
	// public static Method getElementAtMethod(Class c) {
	// return getElementAtMethod (c, 1);
	// }
	public static Method getElementAtMethod(Class c, int numIndices) {
		// Object[] params = {Integer.TYPE};
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isElementAtMethod(methods[i], numIndices))
				return methods[i];
		return null;
	}

	// public static Method getAddElementMethod(Class c) {
	// //Object[] params = {Integer.TYPE};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isAddElementMethod(methods[i])) return methods[i];
	// return null;
	// }
	// public static Method getAddElementMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getAddElementMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	// }

	public static Method[] getMethods(Class c) {
		return c.getMethods();
	}

	public static Method getInsertElementAtMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isInsertElementAtMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getInsertElementAtMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getInsertElementAtMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	// }
	public static Method getRemoveElementMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isRemoveElementMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getRemoveElementMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getRemoveElementMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	// }
	public static Method getRemoveElementAtMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		Method[] methods = c.getMethods();
		// Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isRemoveElementAtMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getRemoveElementAtMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getRemoveElementAtMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	// }
	public static Method getIndexOfMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isIndexOfMethod(methods[i]))
				return methods[i];
		return null;
	}

	public static Method getSetElementAtMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isSetElementAtMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getSetElementAtMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getSetElementAtMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	// }
	public static Method getPutMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isPutMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getPutMethod(Class c) {
	// //Object[] params = {Integer.TYPE};
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getPutMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	//
	// }
	public static Method getRemoveMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isRemoveMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getRemoveMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getRemoveMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	// }
	public static Method getGetMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isTableGetMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getGetMethod(Class c) {
	// //Object[] params = {Integer.TYPE};
	// //Method[] methods = c.getMethods();
	// Class cp = AClass.Class(c);
	// AVirtualMethod virtualMethod = (AVirtualMethod) getGetMethod(cp);
	// if (virtualMethod == null) return null;
	// return virtualMethod.getMethod();
	// /*
	// MethodPr[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isGetMethod(methods[i])) return methods[i];
	// return null;
	// */
	// }

	public static Method getElementsMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isElementsMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getElementsMethod(Class c) {
	// Class cp = AClass.Class(c);
	// AVirtualMethod vm = (AVirtualMethod) getElementsMethod(cp);
	// if (vm == null) return null;
	// return vm.getMethod();
	// /*
	// //Object[] params = {Integer.TYPE};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isElementsMethod(methods[i])) return methods[i];
	// return null;
	// */
	// }

	public static Method getKeysMethod(Class c) {
		// Object[] params = {Integer.TYPE};
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isKeysMethod(methods[i]))
				return methods[i];
		return null;
	}

	// public static Method getKeysMethod(Class c) {
	// Class cp = AClass.Class(c);
	// Method method = getKeysMethod(cp);
	// if (method == null) return null;
	// return ((AVirtualMethod) method).getMethod();
	// /*
	// //Object[] params = {Integer.TYPE};
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isKeysMethod(methods[i])) return methods[i];
	// return null;
	// */
	// }
	public static Vector getEnumerationGetters(Class c) {
		// Object[] params = {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		Vector retVal = new Vector();
		for (int i = 0; i < methods.length; i++) {
			if (isEnumerationGetter(methods[i]))
				retVal.addElement(methods[i]);
		}
		return retVal;
	}

	public static Method getEnumerationGetter(Class c) {
		Vector v = getEnumerationGetters(c);
		if (v.size() == 0)
			return null;
		return (Method) v.elementAt(0);

	}

	public static Method getGetIntMethod(Class c, String name) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isGetIntMethod(methods[i], name))
				return methods[i];
		return null;

	}

	// public static Method getGetIntMethod(ClassDescriptorInterface c, String
	// name) {
	// //Method[] methods = c.getMethods();
	// PropertyDescriptorProxy pd = c.getPropertyDescriptor(name);
	//
	// if (pd != null && pd.getPropertyType() == c.getRealClass().integerType())
	// return pd.getReadMethod();
	// else
	// return null;
	// }
	// public static Method getGetStringMethod(ClassDescriptorInterface c,
	// String name) {
	// //Method[] methods = c.getMethods();
	// PropertyDescriptorProxy pd = c.getPropertyDescriptor(name);
	//
	// if (pd != null && pd.getPropertyType() == c.getRealClass().stringClass())
	// return pd.getReadMethod();
	// else
	// return null;
	// }
	// public static Method getGetOrIsBooleanMethod(ClassDescriptorInterface c,
	// String name) {
	// PropertyDescriptorProxy pd = c.getPropertyDescriptor(name);
	//
	// if (pd != null && pd.getPropertyType() == c.getRealClass().booleanType())
	// return pd.getReadMethod();
	// else
	// return null;
	//
	// }
	// public static Method getSetIntMethod(ClassDescriptorInterface c, String
	// name) {
	// //Method[] methods = c.getMethods();
	// PropertyDescriptorProxy pd = c.getPropertyDescriptor(name);
	// if (pd != null && pd.getPropertyType() == c.getRealClass().integerType())
	// return pd.getWriteMethod();
	// else
	// return null;
	// }
	// public static Method getSetStringMethod(ClassDescriptorInterface c,
	// String name) {
	// //Method[] methods = c.getMethods();
	// PropertyDescriptorProxy pd = c.getPropertyDescriptor(name);
	// if (pd != null && pd.getPropertyType() == c.getRealClass().stringClass())
	// return pd.getWriteMethod();
	// else
	// return null;
	// }
	// public static Method getSetBooleanMethod(ClassDescriptorInterface c,
	// String name) {
	// //Method[] methods = c.getMethods();
	// PropertyDescriptorProxy pd = c.getPropertyDescriptor(name);
	// if (pd != null && pd.getPropertyType() == c.getRealClass().booleanType())
	// return pd.getWriteMethod();
	// else
	// return null;
	// }
	// public static Method getGetPointMethod(Class c, String name) {
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isGetPointMethod(methods[i], name)) return methods[i];
	// return null;
	//
	// }
	/*
	 * public static Method getGetMethod(Class c, String name, Class type) {
	 * Method[] methods = c.getMethods(); for (int i = 0; i < methods.length;
	 * i++) if (isGetMethod(methods[i], name, type)) return methods[i]; return
	 * null;
	 * 
	 * }
	 */
	public static Method getGetMethod(Class c, String name, Class type) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isGetMethod(methods[i], name, type))
				return methods[i];
		return null;

	}

	// public static Method getGetMethod(ClassDescriptorInterface cd, String
	// name, Class type) {
	// //Method[] methods = c.getMethods();
	// PropertyDescriptorProxy pd = cd.getPropertyDescriptor(name);
	// if (pd != null && pd.getPropertyType().isAssignableFrom(type)) {
	// return pd.getReadMethod();
	// }
	// return null;
	//
	// }
	// public static Method getSetMethod(ClassDescriptorInterface cd, String
	// name, Class type) {
	// //Method[] methods = c.getMethods();
	// PropertyDescriptorProxy pd = cd.getPropertyDescriptor(name);
	// if (pd != null && pd.getPropertyType().equals(type)) {
	// return pd.getWriteMethod();
	// }
	// return null;
	//
	// }
	public static Method getGetOrIsBooleanMethod(Class c, String name) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isGetOrIsBooleanMethod(methods[i], name))
				return methods[i];
		return null;

	}

	public static Method getGeneralizedGetMethod(Class c, String name,
			Class type) {
		// Method[] methods = c.getMethods();
		// VirtualMethod[] methods = getAllMethods(c);
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isGeneralizedGetMethod(methods[i], name, type))
				return methods[i];
		return null;

	}

	/*
	 * public static Method getGetMethod(Class c, Method setMethod) {
	 * 
	 * return getGetMethod (c, getPropertyName(setMethod),
	 * setMethod.getParameterTypes()[0]);
	 * 
	 * }
	 */
	public static Method getGetMethod(Class c, Method setMethod) {
		return getGetMethod(c, getPropertyName(setMethod),
				setMethod.getParameterTypes()[0]);

	}

	public static Method getGeneralizedGetMethod(Class c, Method setMethod) {
		int lastParameterNo = setMethod.getParameterTypes().length - 1;
		Class lastParameter = setMethod.getParameterTypes()[lastParameterNo];
		// return getGeneralizedGetMethod (c, getPropertyName(setMethod),
		// setMethod.getParameterTypes()[0]);
		return getGeneralizedGetMethod(c, getPropertyName(setMethod),
				lastParameter);

	}

	public static boolean matchMethod(Method method, String targetName,
			Class targetReturnType, Class[] targetParameterTypes) {
		String name = method.getName();
		try {
			return (targetReturnType == null ||
			// method.getReturnType().isAssignableFrom(targetReturnType)) &&
					targetReturnType.isAssignableFrom(method.getReturnType()))
					&& (targetName == null || method.getName().toLowerCase()
							.equals(targetName.toLowerCase())) &&
					// (targetParameterTypes == null ||
					// (isAssignableFrom(method.getParameterTypes(),
					// targetParameterTypes)));
					(targetParameterTypes == null || (isAssignableFrom(
							targetParameterTypes, method.getParameterTypes())));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean matchConstructor(Constructor method,
			 Class[] targetParameterTypes) {
		String name = method.getName();
		try {
			return 	targetParameterTypes == null || (isAssignableFrom(
							targetParameterTypes, method.getParameterTypes()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isAssignableFrom(Object[] p1Types, Object[] p2Types) {
		if (p1Types.length != p2Types.length)
			return false;
		for (int i = 0; i < p1Types.length; i++) {
			if (!((Class) p1Types[i]).isAssignableFrom((Class) p2Types[i]))
				return false;
		}
		return true;
	}

	/*
	 * public static Method getMethod(Class c, String targetName, Class
	 * targetReturnType, Class[] targetParameterTypes) { Method[] methods =
	 * c.getMethods(); for (int i = 0; i < methods.length; i++) if
	 * (matchMethod(methods[i], targetName, targetReturnType,
	 * targetParameterTypes)) return methods[i]; return null;
	 * 
	 * }
	 */
	
	public static Method getMethodISA(Class c, String targetName,
			Class targetReturnType, Class... targetParameterTypes) throws NoSuchMethodException{
		Method retVal = getMethod(c, targetName, targetReturnType, targetParameterTypes);
		if (retVal == null) {
			String aMessage =					
					c + "." + targetName + ":" + Arrays.asList(targetParameterTypes) +
					" ->" + targetReturnType;
			throw new NoSuchMethodException(aMessage);
		}
		return retVal;
	}
	public static Constructor getConstructorISA(Class c,  Class... targetParameterTypes) throws NoSuchMethodException{
		Constructor retVal = getConstructor(c,  targetParameterTypes);
		if (retVal == null) {
			String aMessage =					
					c + " constructor " + Arrays.asList(targetParameterTypes);
					
			throw new NoSuchMethodException(aMessage);
		}
		return retVal;
	}


	public static Method getMethod(Class c, String targetName,
			Class targetReturnType, Class[] targetParameterTypes) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		return getMethod(methods, targetName, targetReturnType,
				targetParameterTypes);
		/*
		 * for (int i = 0; i < methods.length; i++) if (matchMethod(methods[i],
		 * targetName, targetReturnType, targetParameterTypes)) return
		 * methods[i]; return null;
		 */

	}
	public static Constructor getConstructor(Class c,  Class[] targetParameterTypes) {
		// Method[] methods = c.getMethods();
		Constructor[] methods = c.getConstructors();
		return getConstructor(methods, 
				targetParameterTypes);
		/*
		 * for (int i = 0; i < methods.length; i++) if (matchMethod(methods[i],
		 * targetName, targetReturnType, targetParameterTypes)) return
		 * methods[i]; return null;
		 */

	}

	public static Method getMethod(Method[] methods, String targetName,
			Class targetReturnType, Class[] targetParameterTypes) {
		for (int i = 0; i < methods.length; i++)
			if (matchMethod(methods[i], targetName, targetReturnType,
					targetParameterTypes))
				return methods[i];
//		System.out.println ("Could not find method matching:" + 
//				targetName + ":" + Arrays.asList(targetParameterTypes) +
//				" ->" + targetReturnType);
		return null;

	}
	public static Constructor getConstructor(Constructor[] methods, 
			Class[] targetParameterTypes) {
		for (int i = 0; i < methods.length; i++)
			if (matchConstructor(methods[i],
					targetParameterTypes))
				return methods[i];
//		System.out.println ("Could not find method matching:" + 
//				targetName + ":" + Arrays.asList(targetParameterTypes) +
//				" ->" + targetReturnType);
		return null;

	}

	// public static Method getMethod(Class c,
	// String targetName, Class targetReturnType,
	// Class[] targetParameterTypes) {
	// //Method[] methods = c.getMethods();
	// Class cProxy = AClass.Class(c);
	// Class targetProxy = AClass.Class(targetReturnType);
	// Class[] targetParameterTypesProxy = AClass.Class(targetParameterTypes);
	// Method retVal = getMethod(cProxy, targetName, targetProxy,
	// targetParameterTypesProxy);
	// if (retVal == null) return null;
	// return ((AVirtualMethod) retVal).getMethod();
	// //return getMethod(cProxy, targetName, targetProxy,
	// targetParameterTypesProxy);
	// /*
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (matchMethod(methods[i], targetName, targetReturnType,
	// targetParameterTypes)) return methods[i];
	// return null;
	// */
	//
	// }

	static Class[] nullParams = {};
	static Class[] objectParam = { Object.class };
	static Class[] nullProxyParams = {};

	// static Class[] objectProxyParam = {StandardProxyTypes.objectClass()};

	public static Method getParameterLessMethod(Class c, String methodName) {
		try {
			return c.getMethod(methodName, nullProxyParams);
		} catch (Exception e) {
			// Message.warning("Could not find method: " + methodName +
			// " in class:" + c.getName());
			return null;
		}
	}

	public static Method getCloneMethod(Class c) {
		return getParameterLessMethod(c, "clone");
	}

	public static Method getSingleObjectParameterMethod(Class c,
			String methodName) {
		Class[] objectProxyParam = { Object.class };
		try {
			return c.getMethod(methodName, objectProxyParam);
		} catch (Exception e) {
			return null;
		}
	}

	public static Method getSingleParameterMethod(Class c, String methodName,
			Class paramClass) {
		try {
			Class[] params = { paramClass };
			return c.getMethod(methodName, params);
		} catch (Exception e) {
			return null;
		}
	}

	public static Method getIsEmptyMethod(Class c) {
		return getParameterLessMethod(c, "isEmpty");
	}

	public static Method getValuesMethod(Class c) {
		return getParameterLessMethod(c, "values");
	}

	public static Method getKeySetMethod(Class c) {
		return getParameterLessMethod(c, "keySet");
	}

	public static Method getEntrySetMethod(Class c) {
		return getParameterLessMethod(c, "entrySet");
	}

	public static Method getClearMethod(Class c) {
		return getParameterLessMethod(c, "clear");
	}

	public static Method getContainsKeyMethod(Class c) {
		return getSingleObjectParameterMethod(c, "containsKey");
	}

	public static Method getContainsValueMethod(Class c) {
		return getSingleObjectParameterMethod(c, "containsValue");
	}

	public static Method getContainsMethod(Class c) {
		return getSingleObjectParameterMethod(c, "contains");
	}

	/*
	 * public static Class mapClass() { return
	 * RemoteSelector.Class(java.util.Map.class); } public static Class
	 * collectionClass() { return
	 * RemoteSelector.Class(java.util.Collection.class); } public static Class
	 * listClass() { return RemoteSelector.Class(java.util.List.class); } public
	 * static Class setClass() { return
	 * RemoteSelector.Class(java.util.Set.class); }
	 * 
	 * public static Class tableClass() { return
	 * RemoteSelector.Class(JTable.class); } public static Class treeClass() {
	 * return RemoteSelector.Class(JTree.class); }
	 */
	public static Method getPutAllMethod(Class c) {
		return getSingleParameterMethod(c, "putAll", Map.class);

	}

	public static Method getAddAllMethod(Class c) {
		return getSingleParameterMethod(c, "addAll", Collection.class);

	}

	public static Method getCheckerMethod(Class c, String targetName) {
		Class[] targetParameterTypes = {};
		return getMethod(c, targetName, Boolean.TYPE, targetParameterTypes);

	}

	/*
	 * public static Method getMethod(Object o, String targetName, Class
	 * targetReturnType, Class[] targetParameterTypes) { if (o == null) return
	 * null; return getMethod (o.getClass(), targetName, targetReturnType,
	 * targetParameterTypes); }
	 */
	public static Method getMethod(Object o, String targetName,
			Class targetReturnType, Class[] targetParameterTypes) {
		if (o == null)
			return null;
		return getMethod(o.getClass(), targetName, targetReturnType,
				targetParameterTypes);
	}

	// public static Class executedCommandListenerClass() {
	// return
	// RemoteSelector.Class(bus.uigen.undo.ExecutedCommandListener.class);
	// }
	//
	// public static Method getAddExecutedCommandListener(Object o, Method m) {
	// Class[] targetParameterTypes = {executedCommandListenerClass()};
	// return getMethod(o, "add" + m.getName() + "CommandListener", null,
	// targetParameterTypes);
	// }
	// public static Method getAddExecutedCommandListener(Object o) {
	// Class[] targetParameterTypes = {executedCommandListenerClass()};
	// return getMethod(o, "addExecutedCommandListener", null,
	// targetParameterTypes);
	// }
	// public static boolean isAddExecutedCommandListener(Method m) {
	// Class params[] = {executedCommandListenerClass()};
	// return m.getName().equals("addExecutedCommandListener") &&
	// bus.uigen.undo.Util.equal(m.getParameterTypes(), params);
	//
	// }
	public static Method getSetIntMethod(Class c, String name) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isSetIntMethod(methods[i], name))
				return methods[i];
		return null;

	}

	// public static Method getSetPointMethod(Class c, String name) {
	// //Method[] methods = c.getMethods();
	// Method[] methods = getMethods(c);
	// for (int i = 0; i < methods.length; i++)
	// if (isSetPointMethod(methods[i], name)) return methods[i];
	// return null;
	//
	// }
	public static Method getSetMethod(Class c, String name, Class type) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isSetMethod(methods[i], name, type))
				return methods[i];
		return null;

	}

	public static Method getSetBooleanMethod(Class c, String name) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isSetBooleanMethod(methods[i], name))
				return methods[i];
		return null;

	}

	public static Method getGetStringMethod(Class c, String name) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isGetStringMethod(methods[i], name))
				return methods[i];
		return null;

	}

	public static Method getSetStringMethod(Class c, String name) {
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isSetStringMethod(methods[i], name))
				return methods[i];
		return null;

	}

	// public static Method getTreeGetChildAtMethod (Class c) {
	// Class[] args = {c.integerType()};
	// return JavaIntrospectUtility.getMethod(c, "getChildAt", null, args);
	// }
	// public static Method getTreeGetChildCountMethod (Class c) { return
	// JavaIntrospectUtility.getGetIntMethod(c, "ChildCount");}
	// public static Method getTreeIsLeafMethod (Class c) { return
	// JavaIntrospectUtility.getCheckerMethod(c, "isLeaf");}
	// public static Method getTreeInsertMethod (Class c) {
	// Class[] insertArgs = {Object.class, c.integerType()};
	// return JavaIntrospectUtility.getMethod(c, "insert", c.voidType(),
	// insertArgs);
	// }
	// public static Method getTreeRemoveMethod (Class c) {
	// Class[] removeArgs = {c.integerType()};
	// return JavaIntrospectUtility.getMethod(c, "remove", c.voidType(),
	// removeArgs);
	// }
	//
	// public static Method getTreeGetUserObjectMethod (Class c) {
	// Class[] nullArgs = {};
	// Method m = JavaIntrospectUtility.getMethod(c, "getUserObject", null,
	// nullArgs);
	// if (m != null && m.getReturnType() != c.voidType()) return m;
	// return JavaIntrospectUtility.getMethod(c, "getSelfObject", null,
	// nullArgs);
	// }
	//
	// public static Method getExpansionObjectMethod (Class c) {
	// Class[] nullArgs = {};
	// Method m = JavaIntrospectUtility.getMethod(c, "getExpansionObject", null,
	// nullArgs);
	// if (m != null && m.getReturnType() != c.voidType()) return m;
	// return null;
	// //return uiBean.getMethod(c, "getSelfObject", null, nullArgs);
	// }
	//
	//
	// public static Method getTreeSetUserObjectMethod (Class c) {
	// Class[] userObjectArgs = {Object.class};
	// Method m = JavaIntrospectUtility.getMethod(c, "setUserObject",
	// c.voidType(), userObjectArgs);
	// if (m != null) return m;
	// return JavaIntrospectUtility.getMethod(c, "setSelfObject", c.voidType(),
	// userObjectArgs);
	// }
	//
	// public static Method getTableGetColumnCountMethod (Class c) {
	// return JavaIntrospectUtility.getGetIntMethod(c, "ColumnCount");
	// }
	// public static Method getTableGetRowCountMethod (Class c) {
	// return JavaIntrospectUtility.getGetIntMethod(c, "RowCount");
	// }
	// public static Method getTableGetColumnNameMethod (Class c) {
	// Class[] args = {c.integerType()};
	// return JavaIntrospectUtility.getMethod(c, "getColumnName",
	// c.stringClass(), args);
	// }
	//
	// public static Method getTableGetValueAtMethod (Class c) {
	// Class[] args = {c.integerType(), c.integerType()};
	// return JavaIntrospectUtility.getMethod(c, "getValueAt", null, args);
	// }
	// public static Method getTableSetValueAtMethod (Class c) {
	// Class[] args = {Object.class, c.integerType(), c.integerType()};
	// return JavaIntrospectUtility.getMethod(c, "setValueAt", c.voidType(),
	// args);
	// }
	// public static Method getTableIsCellEditableMethod (Class c) {
	// Class[] args = {c.integerType(), c.integerType()};
	// return JavaIntrospectUtility.getMethod(c, "isCellEditable", Boolean.TYPE,
	// args);
	// }
	//
	// public static Method getIsIndexedChildEditableMethod (Class c) {
	// Class[] args = {c.integerType()};
	// return JavaIntrospectUtility.getMethod(c, "isEditable", Boolean.TYPE,
	// args);
	// }
	public static Method getIsEditableMethod(Class c) {
		Class[] args = {};
		return JavaIntrospectUtility.getMethod(c, "isEditable", Boolean.TYPE,
				args);
	}

	// public static Method getIsEditableMethod (RemoteClass c) {
	// Class[] args = {};
	// return JavaIntrospectUtility.getMethod(c, "isEditable", Boolean.TYPE,
	// args);
	// }

	public static Method getIsPropertyEditableMethod(Class c, String property) {
		Class[] args = {};
		return JavaIntrospectUtility.getMethod(c, "isEditable" + property,
				Boolean.TYPE, args);
	}

	// static String[] excludeClasses = {"bus.uigen.AMutableString",
	// "bus.uigen.AListenableString", "budget.Test"};
	// static Vector excludeVectorClasses =
	// uiGenerator.arrayToVector(excludeClasses);
	public static boolean isVector(Class c) {
		// System.out.println("checking for vector" + c.getName());
		if (c == Vector.class)
			return true;
		// if (excludeVectorClasses.contains(c.getName())) return false;
		// System.out.println("
		// Object[] params = {
		return getElementsMethod(c) != null
				|| (getSizeMethod(c) != null && getElementAtMethod(c) != null);
	}

	public static boolean isHashtable(Class c) {
		// Object[] params = {
		return ((getKeysMethod(c) != null) && (getElementsMethod(c) != null || getGetMethod(c) != null));
	}

	public static boolean hasXYLocation(Class c) {
		// Object[] params = {
		return ((getGetIntMethod(c, "X") != null) && (getGetIntMethod(c, "Y") != null));
	}

	// public static boolean hasXYLocation(ClassDescriptorInterface c) {
	// //Object[] params = {
	// return ((getGetIntMethod(c, "X") != null) &&
	// (getGetIntMethod(c, "Y") != null) );
	// }
	public static boolean isPredefinedClass(Class c) {
		return c.getName().startsWith("java");
	}

	
	public static void addNew(Vector v, Object o) {
		if (v.contains(o))
			return;
		else
			v.addElement(o);
	}

	public static void addTypes(Vector v, Class c) {
		if ((c == null) || (c == Object.class) || (v.contains(c)))
			return;
		v.addElement(c);
		// addNew(v, c);
		addInterfaces(v, c);
		Class superClass = c.getSuperclass();
		addTypes(v, superClass);
	}

	public static void addInterfaces(Vector v, Class c) {
//		if (c.isInterface())
//			return;
		Class[] interfaces = c.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			addTypes(v, interfaces[i]);
		}
	}

	public static Vector<Class> getTypes(Class c) {
		Vector<Class> v = new Vector();
		addTypes(v, c);
		return v;
	}

	public static boolean contains(String s, String subString) {
		int index = s.lastIndexOf(subString);
		if (index >= 0 && index <= s.length())
			return true;
		else
			return false;
	}

	public static boolean contains(Class c, String name) {
	
		boolean retVal = containsDeep(c, name);
		if (retVal)
			return true;
		

		return false;
	}

	public static boolean containsDeep(Class c, String name) {
		Vector v = getTypes(c);
		for (int i = 0; i < v.size(); i++)
			if (contains(((Class) v.elementAt(i)).getName(), name))
				return true;
		return false;
	}

	public static boolean containsShallow(Class c, String name) {
		return contains(c.getName(), name);
	}

	
	public static final int NO_SHAPE = 0;
	public static final int POINT_SHAPE = 1;
	public static final int RECTANGLE_SHAPE = 2;
	public static final int OVAL_SHAPE = 3;
	public static final int LINE_SHAPE = 4;
	public static final int TEXT_SHAPE = 5;
	public static final int LABEL_SHAPE = 6;
	public static final int ARC_SHAPE = 7;
	public static final int CURVE_SHAPE = 8;

	

	public static boolean isVector(Object o) {
		if (o == null)
			return false;
		return isVector(o.getClass());

	}

	public static boolean isHashtable(Object o) {
		if (o == null)
			return false;
		return isHashtable(o.getClass());

	}

	public static boolean isPoint(Object o) {
		if (o == null)
			return false;
		return isPoint(o.getClass());

	}

	public static boolean isRectangle(Object o) {
		if (o == null)
			return false;
		return isRectangle(o.getClass());

	}

	public static boolean isOval(Object o) {
		if (o == null)
			return false;
		return isOval(o.getClass());

	}

	public static boolean isLine(Object o) {
		if (o == null)
			return false;
		return isLine(o.getClass());

	}

	public static void addElements(Vector vector, Method elements, Object object) {
		try {
			Object[] params = {};
			if (elements == null)
				return;
			Enumeration enumeration = (Enumeration) elements.invoke(object,
					params);
			while (enumeration.hasMoreElements()) {
				vector.addElement(enumeration.nextElement());
			}
		} catch (Exception e) {
		}
		;
	}

	public static Vector toClassVector(Object object) {
		if (object.getClass().equals(Vector.class))
			return (Vector) object;
		return copyToVector(object);
	}

	public static Vector toVector(Object object) {
		if (object instanceof Vector)
			return (Vector) object;
		return copyToVector(object);
	}

	public static Vector copyToVector(Object object) {
		if (object == null)
			return null;
		// if (object instanceof Vector && !(object instanceof
		// AListenableVector))
		/*
		 * if (object instanceof Vector) return (Vector) object;
		 */
		Method size = null;
		Method elementAt = null;
		Method elements = null;
		// VirtualMethod[] methods = object.getClass().getMethods();
		Method[] methods = getMethods(object.getClass());
		Object[] params = {};
		Vector vector = new Vector();
		for (int i = 0; i < methods.length; i++)
			if (isElementAtMethod(methods[i]) || isTableGetMethod(methods[i]))
				elementAt = methods[i];
			else if (isSizeMethod(methods[i]))
				size = methods[i];
			else if (isElementsMethod(methods[i]))
				elements = methods[i];
		try {

			if (elements != null) {

				Enumeration enumeration = (Enumeration) elements.invoke(object,
						params);
				while (enumeration.hasMoreElements())
					vector.addElement(enumeration.nextElement());
				return vector;

			} else if (size != null && elementAt != null) {
				int vectorSize = ((Integer) size.invoke(object, params))
						.intValue();
				for (int i = 0; i < vectorSize; i++) {
					Object[] params2 = { new Integer(i) };
					vector.addElement(elementAt.invoke(object, params2));
				}
				return vector;
			} else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static Hashtable toHashtable(HashMap object) {
		return new Hashtable(object);

	}

	public static Hashtable toHashtable(Hashtable object) {
		return new Hashtable(object);

	}

	public static Hashtable toHashtable(Object object) {

		if (object instanceof Hashtable)
			return toHashtable((Hashtable) object);
		else if (object instanceof HashMap)
			return toHashtable((HashMap) object);
		Method keysMethod = null;
		Method getMethod = null;
		Method elementsMethod = null;
		Method[] methods = getMethods(object.getClass());
		Object[] params = {};
		Hashtable hashtable = new Hashtable();
		for (int i = 0; i < methods.length; i++)
			if (isKeysMethod(methods[i]))
				keysMethod = methods[i];
			else if (isTableGetMethod(methods[i]))
				getMethod = methods[i];
			else if (isElementsMethod(methods[i]))
				elementsMethod = methods[i];
		try {

			if (keysMethod != null) {
				Enumeration keys = (Enumeration) keysMethod.invoke(object,
						params);
				if (elementsMethod != null) {
					Enumeration elements = (Enumeration) elementsMethod.invoke(
							object, params);
					while (keys.hasMoreElements() && elements.hasMoreElements())
						hashtable.put(keys.nextElement(),
								elements.nextElement());
					return hashtable;
				} else if (getMethod != null) {
					while (keys.hasMoreElements()) {
						Object nextKey = keys.nextElement();
						Object[] params2 = { nextKey };
						hashtable.put(nextKey,
								getMethod.invoke(object, params2));
					}
					return hashtable;
				} else
					return null;
			} else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

	static Set<Method> propertyChangeListeners = new HashSet();

	public static boolean isAddPropertyChangeListenerMethod(Method method) {
		if (propertyChangeListeners.contains(method))
			return true;
		util.annotations.ObserverRegisterer observerRegister = method
				.getAnnotation(util.annotations.ObserverRegisterer.class);
		boolean annotated = false;
		boolean correctlyNamed = false;

		if (observerRegister != null
				&& observerRegister.value().equals(
						util.annotations.ObserverTypes.PROPERTY_LISTENER))
			annotated = true;
		else if (method.getName().equalsIgnoreCase("addPropertyChangeListener"))
			correctlyNamed = true;
		if (!annotated && !correctlyNamed)
			return false;

		Class[] params = method.getParameterTypes();
		if (params.length == 1
				&& PropertyChangeListener.class.isAssignableFrom(params[0])) {
			// Message.warning("Use annotation util.ObserverRegisterer + \"" +
			// util.ObserverTypes.PROPERTY_LISTENER + "\" for method " + method
			// ));
			if (!annotated) {
				Tracer.warning("Use annotation @util.annotations.ObserverRegisterer(\""
						+ util.annotations.ObserverTypes.PROPERTY_LISTENER
						+ "\") for method " + method);
			}
			propertyChangeListeners.add(method);
			return true;
		} else {
			Tracer.warning(method
					+ " not recognized as registerer of property change listener because it does not have a single parameter of type:"
					+ PropertyChangeListener.class);
			return false;
		}

	}

	public static boolean isPropertyChangeListenerMethod(Method method) {

		String name = method.getName();
		if (name.endsWith("PropertyChangeListener")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& PropertyChangeListener.class.isAssignableFrom(params[0])) {
				// Message.warning("Use annotation util.ObserverRegisterer + \""
				// + util.ObserverTypes.PROPERTY_LISTENER + "\" for method " +
				// method ));

				return true;
			} else
				return false;
		}
		return false;
	}

	public static boolean isAddPropertyChangeListenerMethodWorking(Method method) {
		String name = method.getName();
		if (name.equals("addPropertyChangeListener")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& PropertyChangeListener.class.isAssignableFrom(params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isAddVectorListenerMethodWorking(Method method) {
		String name = method.getName();
		if (name.equals("addVectorListener")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& VectorListener.class.isAssignableFrom(params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	static Set<Method> vectorListeners = new HashSet();

	public static boolean isAddVectorListenerMethod(Method method) {
		if (vectorListeners.contains(method))
			return true;
		util.annotations.ObserverRegisterer observerRegisterer = method
				.getAnnotation(util.annotations.ObserverRegisterer.class);
		boolean annotated = observerRegisterer != null
				&& observerRegisterer.value().equals(
						util.annotations.ObserverTypes.VECTOR_LISTENER);

		String name = method.getName();
		boolean correctlyNamed = false;
		if (!annotated)
			correctlyNamed = name.equals("addVectorListener");

		if (!annotated && !correctlyNamed)
			return false;

		Class[] params = method.getParameterTypes();
		if (params.length == 1
				&& VectorListener.class.isAssignableFrom(params[0])) {
			// Message.warning("Use annotation util.ObserverRegisterer + \"" +
			// util.ObserverTypes.PROPERTY_LISTENER + "\" for method " + method
			// ));
			if (!annotated) {
				Tracer.warning("Use annotation @util.annotations.ObserverRegisterer(\""
						+ util.annotations.ObserverTypes.VECTOR_LISTENER
						+ "\") for method " + method);
			}
			vectorListeners.add(method);
			return true;
		} else {
			Tracer.warning(method
					+ " not recognized as registerer of vector  listener because it does not have a single parameter of type:"
					+ VectorListener.class);
			return false;
		}

	}

	public static boolean isAddHashtableListenerMethod(Method method) {
		String name = method.getName();
		if (name.equals("addHashtableListener")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& HashtableListener.class.isAssignableFrom(params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	public static Class remotePropertyChangeListenerClass() {
		return util.models.RemotePropertyChangeListener.class;
	}

	public static boolean isAddRemotePropertyChangeListenerMethod(Method method) {
		String name = method.getName();
		if (name.equals("addRemotePropertyChangeListener")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& remotePropertyChangeListenerClass().isAssignableFrom(
							params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isRemotePropertyChangeListenerMethod(Method method) {
		String name = method.getName();
		if (name.endsWith("RemotePropertyChangeListener")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1 &&
			// used to be simply propertyChangeLis
					// propertyChangeListenerClass().isAssignableFrom(params[0]))
					remotePropertyChangeListenerClass().isAssignableFrom(
							params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	public static Method getAddPropertyChangeListenerMethod(Class c) {
		if (c == null)
			return null;
		// VirtualMethod[] methods = c.getMethods();
		// Method[] methods = getMethods(c);
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isAddPropertyChangeListenerMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static Method getAddVectorListenerMethod(Class c) {
		if (c == null)
			return null;
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isAddVectorListenerMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static Method getAddHashtableListenerMethod(Class c) {
		if (c == null)
			return null;
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isAddHashtableListenerMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static Method getAddRemotePropertyChangeListenerMethod(Class c) {
		if (c == null)
			return null;
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isAddRemotePropertyChangeListenerMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static boolean isTableModelListenerMethod(Method method) {
		String name = method.getName();
		if (name.endsWith("TableModelListener")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& javax.swing.event.TableModelListener.class
							.isAssignableFrom(params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isAddTableModelListenerMethod(Method method) {
		String name = method.getName();
		return (name.startsWith("add") && isTableModelListenerMethod(method));
	}

	public static boolean isRemoveTableModelListenerMethod(Method method) {
		String name = method.getName();
		return (name.startsWith("remove") && isTableModelListenerMethod(method));
	}

	public static Method getAddTableModelListenerMethod(Class c) {
		if (c == null)
			return null;
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isAddTableModelListenerMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static Method getRemoveTableModelListenerMethod(Class c) {
		if (c == null)
			return null;
		// Method[] methods = c.getMethods();
		Method[] methods = getMethods(c);
		for (int i = 0; i < methods.length; i++)
			if (isRemoveTableModelListenerMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static boolean isTreeModelListenerMethod(Method method) {
		String name = method.getName();
		if (name.endsWith("TreeModelListener")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& javax.swing.event.TreeModelListener.class
							.isAssignableFrom(params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isAddTreeModelListenerMethod(Method method) {
		String name = method.getName();
		return (name.startsWith("add") && isTreeModelListenerMethod(method));
	}

	public static boolean isRemoveTreeModelListenerMethod(Method method) {
		String name = method.getName();
		return (name.startsWith("remove") && isTreeModelListenerMethod(method));
	}

	public static Method getAddTreeModelListenerMethod(Class c) {
		if (c == null)
			return null;
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isAddTreeModelListenerMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static Method getRemoveTreeModelListenerMethod(Class c) {
		if (c == null)
			return null;
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isRemoveTreeModelListenerMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static boolean isObserverMethod(Method method) {
		String name = method.getName();
		if (name.endsWith("Observer")) {
			Class[] params = method.getParameterTypes();
			if (params.length == 1
					&& Observer.class.isAssignableFrom(params[0]))
				return true;
			else
				return false;
		}
		return false;
	}

	public static boolean isAddObserverMethod(Method method) {
		String name = method.getName();
		return (name.startsWith("add") && isObserverMethod(method));
	}

	public static boolean isDeleteObserverMethod(Method method) {
		String name = method.getName();
		return (name.startsWith("delete") && isObserverMethod(method));
	}

	public static Method getAddObserverMethod(Class c) {
		if (c == null)
			return null;
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isAddObserverMethod(methods[i]))
				return methods[i];
		return null;

	}

	public static Method getDeleteObserverMethod(Class c) {
		if (c == null)
			return null;
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isDeleteObserverMethod(methods[i]))
				return methods[i];
		return null;

	}

	// comp.
	// added so you can get the setter method by just giving the name of the
	// property
	// used in TxfrButton's actnperf.
	// and DatabaseComposer
	public static Method getSetterMethod(Class c, String propertyName) {
		// Method[] methods = c.getMethods();
		// Method[] methods = getMethods(c);
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (isSetter(methods[i])
					&& methods[i].getName().toLowerCase()
							.endsWith(propertyName.toLowerCase()))
				return methods[i];
		return null;
	}

	// comp.
	// added so you can get the getter method by just giving the name of the
	// property
	// used in TxfrButton's actnperf.
	// and DatabaseComposer
	public static Method getGetterMethod(Class c, String propertyName) {
		propertyName = Character.toUpperCase(propertyName.charAt(0))
				+ propertyName.substring(1);
		// Method[] methods = c.getMethods();
		// Method[] methods = getMethods(c);
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			// if (isGetter(methods[i]) &&
			// methods[i].getName().endsWith(propertyName))
			// if (methods[i].getName().equals("get" + propertyName) &&
			// isGetter(methods[i]) )
			if (isGetter(methods[i], propertyName))
				return methods[i];
		return null;
	}

	// F.O.
	// comp.
	/*
	 * public static Enumeration getAllPropertyNames(Object realObject) {
	 * Hashtable propHash = new Hashtable(); Method[] methods =
	 * realObject.getClass().getMethods();
	 * 
	 * for (int i=0; i < methods.length; i++) {//for each method if
	 * (isSimplePropertyMethod(methods[i])) { //if it's a property method
	 * 
	 * String aProperty = methods[i].getName().substring(3); //take the 3 letter
	 * set/get off propHash.put(aProperty,aProperty); //hash it ...assume
	 * get*sets will overwrite eachother } }
	 * 
	 * if (!propHash.isEmpty()) return propHash.elements(); else return null;
	 * 
	 * }//end getPNam
	 */

	public static Enumeration getAllPropertiesNames(Object realObject) { // difference
																			// is
																			// sthat
																			// this
																			// doesn't
																			// check
																			// if
																			// isSimple...rather
																			// if
																			// isProperty
		return getAllPropertiesNames(realObject.getClass());
		/*
		 * Hashtable propHash = new Hashtable(); Method[] methods =
		 * RemoteSelector.getClass(realObject).getMethods();
		 * 
		 * for (int i=0; i < methods.length; i++) {//for each method if
		 * (isPropertyMethod(methods[i])) { //if it's a property method
		 * 
		 * String aProperty = methods[i].getName().substring(3); //take the 3
		 * letter set/get off aProperty =
		 * Character.toLowerCase(aProperty.charAt(0)) + aProperty.substring(1);
		 * // System.out.println("UIBEAN" + aProperty); if
		 * (!aProperty.equals("Class")) propHash.put(aProperty,aProperty);
		 * //hash it ...assume get*sets will overwrite eachother } }
		 * 
		 * // System.exit(0);
		 * 
		 * if (!propHash.isEmpty()) return propHash.elements(); else return
		 * null;
		 */

	}// end getPNam

	public static Enumeration getAllPropertiesNames(Class cls) {
		return getAllPropertiesNamesVector(cls).elements();
	}

	public static boolean isUpperCase(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLowerCase(s.charAt(i)))
				return false;
		}
		return true;
	}

	public static Vector getAllPropertiesNamesVector(Class cls) { // difference
																	// is sthat
																	// this
																	// doesn't
																	// check if
																	// isSimple...rather
																	// if
																	// isProperty
		Vector properties = new Vector();
		Method[] methods = cls.getMethods();

		for (int i = 0; i < methods.length; i++) {// for each method
			if (isPropertyMethod(methods[i])) { // if it's a property method

				String aProperty = methods[i].getName().substring(3); // take
																		// the 3
																		// letter
																		// set/get
																		// off
				String suffix = aProperty.substring(1);
				if (!isUpperCase(suffix))
					aProperty = Character.toLowerCase(aProperty.charAt(0))
							+ suffix;
				// aProperty = Character.toLowerCase(aProperty.charAt(0)) +
				// aProperty.substring(1);
				// System.out.println("UIBEAN" + aProperty);
				if (!aProperty.equals("Class")
						&& !properties.contains(aProperty))
					properties.addElement(aProperty); // hash it ...assume
														// get*sets will
														// overwrite eachother
			}
		}

		// System.exit(0);
		return properties;
		/*
		 * if (!propHash.isEmpty()) return propHash.elements(); else return
		 * null;
		 */

	}// end getPNam

	// F.O. changed below from protected to public b/c I need it elsewhere...why
	// make it protected?
	public static boolean isPropertyMethod(Method method) {
		if (!Modifier.isPublic(method.getModifiers()))
			return false;
		String name = method.getName();

		if (name.startsWith("get") && !name.equals("get")) {
			if (method.getParameterTypes().length == 0
					&& !(method.getReturnType().equals(Void.TYPE)))
				return true;
			else
				return false;
		} else if (name.startsWith("set") && !name.equals("set")) {
			if (method.getParameterTypes().length == 1
					&& method.getReturnType().equals(Void.TYPE))
				return true;
			else
				return false;
		} else
			return false;
	}

	// end comp.

	// F.O.
	public static Method getMethod(Object o, String origMeth) {
		String meth;
		if (Character.isDigit(origMeth.charAt(0)))
			;
		meth = "_" + origMeth + "_";
		Class c = o.getClass();
		Method[] methods = c.getMethods();

		// below is not good because it shouldn't be lowercasing the methodnames
		// below before the test
		// can get away w/ it for now; dunno if it will mess things up
		for (int i = 0; i < methods.length; i++) {
			// System.out.println( methods[i].getName().toLowerCase() + " ,,,, "
			// + meth.toLowerCase());
			if (methods[i].getName().toLowerCase().equals(meth.toLowerCase())) {
				// System.out.println( methods[i].getName().toLowerCase() +
				// " ,,,, " + meth.toLowerCase());
				return methods[i];
			}
		}
		return null;
	}

	// public static String getVirtualClass(Object o) {
	// if (o == null) return null;
	// Class realClass = o.getClass;
	// Class[] argTypes = {};
	// try {
	// Method m = realClass.getMethod("getVirtualClass", argTypes);
	// if (m.getReturnType() != realClass.stringClass()) return null;
	// Object[] args = {};
	// String retVal = (String) m.invoke(o, args);
	// return retVal;
	//
	// } catch (Exception e) {
	// return null;
	// }
	//
	// }

	public static void invokeInitSerializedObject(Object o) {
		if (o == null)
			return;
		try {
			Method init = o.getClass().getMethod(INIT_SERIALIZED_OBJECT,
					nullProxyParams);
			if (init == null)
				return;
			init.invoke(o, emptyArgs);
		} catch (NoSuchMethodException me) {
			System.err.println("Object: " + o + "does not have method: "
					+ INIT_SERIALIZED_OBJECT);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Vector propertyValues(PropertyDescriptor[] properties,
			Object viewObject) {
		Vector retVal = new Vector();
		for (int i = 0; i < properties.length; i++) {
			PropertyDescriptor property = properties[i];
			// Get the value of the property.
			Method readMethod = property.getReadMethod();
			if (readMethod != null && !property.getName().equals("class")) {
				try {
					Object value = readMethod.invoke(viewObject, null);
					// System.out.println("found prop value" + value);
					retVal.addElement(value);
					// System.out.println("Read "+property.getName()+" = "+value);
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	public static Class classForName(String name) {
		try {
			return Class.forName(name);
		} catch (Exception e) {
			return null;
		}
	}

}
