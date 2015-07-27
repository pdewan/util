package util.misc;

import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import util.trace.Tracer;

public class RemoteReflectionUtility {
	public static String INIT_SERIALIZED_OBJECT = "initSerializedObject";
	public static String LIST_ADD = "add";
	public static String LIST_SIZE = "size";
	public static String LIST_GET = "get";
	static Set<Class> atomicList =  new HashSet();

	static Map<String, Class> primitiveToType = new HashMap();
	static Object[] emptyArgs = {};
	static Class[] emptyClassArgs = {};

	public static Class forName(String typeName) {
		try {
			Class retVal = primitiveToType.get(typeName);
			if (retVal == null)
				return Class.forName(typeName);
			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static void checkRemoteMethods (Class aClass) {
		if (!aClass.isInterface() || !(Remote.class.isAssignableFrom(aClass))) return;
		Method[] methods = aClass.getMethods();
		for (Method method:methods) {
			if (!throwsRemoteException(method)) {
				Tracer.warning("Remote " + method + " should throw " + RemoteException.class);
			}
		}
		
		
	}
	
	public static boolean throwsRemoteException(Method aMethod) {
		Class[] exceptionTypes = aMethod.getExceptionTypes();
		for (Class exceptionType: exceptionTypes) {
			if (RemoteException.class.isAssignableFrom(exceptionType))
				return true;
		}
		return false;
	}

	public static boolean isGetter(Method method) {
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

	public static void invokeInitSerializedObject(Object o) {
		if (o == null)
			return;
		try {
			Tracer.info(RemoteReflectionUtility.class, "Calling on object: " + o + " method"
					+ INIT_SERIALIZED_OBJECT);
			Method init = o.getClass().getMethod(INIT_SERIALIZED_OBJECT,
					emptyClassArgs);
			if (init == null)
				return;
			init.invoke(o, emptyArgs);
		} catch (NoSuchMethodException me) {
			Tracer.warning ("Class: " + o.getClass() + "does not have method: "
					+ INIT_SERIALIZED_OBJECT);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean isPrimitive(Class aClass) {
		return primitiveToType.values().contains(aClass);
	}
	
	static void initPrimitiveToType() {
		primitiveToType.put("int", Integer.TYPE);
		primitiveToType.put("boolean", Boolean.TYPE);
		primitiveToType.put("float", Float.TYPE);
		primitiveToType.put("double", Double.TYPE);
		primitiveToType.put("long", Long.TYPE);
		primitiveToType.put("short", Short.TYPE);
		primitiveToType.put("char", Character.TYPE);
	}
	
	static void initAtomicList() {
		
		// should really
		atomicList.add(String.class);
	    atomicList.add(Boolean.class);
	    atomicList.add(Integer.class);
	    atomicList.add(Float.class);
	    atomicList.add(Byte.class);
	    atomicList.add(Double.class);
	    atomicList.add(Long.class);
	    atomicList.add(Short.class);
	    atomicList.add(Character.class);
	    atomicList.add(Boolean.class);
	    atomicList.addAll(primitiveToType.values());
	    
	}
	
	public static  boolean isAtomicClass(Class aClass) {
		return atomicList.contains(aClass);
	  }

	static {
		initPrimitiveToType();
		initAtomicList();
//		primitiveToType.put("int", Integer.TYPE);
//		primitiveToType.put("boolean", Boolean.TYPE);
//		primitiveToType.put("float", Float.TYPE);
//		primitiveToType.put("double", Double.TYPE);
//		primitiveToType.put("long", Long.TYPE);
//		primitiveToType.put("short", Short.TYPE);
//		primitiveToType.put("char", Character.TYPE);
	}

	public static Class[] getRemoteInterfaces(Remote remote) {
		Class remoteClass = remote.getClass();
		return getProxyInterfaces(remoteClass);

	}
	
	public static void addProxyInterfaces(Class aRemoteClass,
			Set<Class> aRemoteInterfaces) {		
		if (aRemoteClass.equals(Object.class)) return;
		if (aRemoteClass.isInterface()) {
			aRemoteInterfaces.add(aRemoteClass);
			return ;
		}
		Class[] interfaces = aRemoteClass.getInterfaces();
//		if (interfaces.length == 1) {
//			aRemoteInterfaces.add(interfaces[0]); // only interface, no choice
//		} else {
			for (int i = 0; i < interfaces.length; i++) {
				Class anInterface = interfaces[i]; // we are not looking for
													// instances of Remote
				// if (Remote.class.isAssignableFrom(anInterface)) {
				// but we will require Remote methods to throw RemoteException
//				checkRemoteMethods(anInterface);
				aRemoteInterfaces.add(anInterface);
				// return anInterface;
				// }
			}
//		}
		addProxyInterfaces(aRemoteClass.getSuperclass(), aRemoteInterfaces);
	}
	
	public static Class[] getProxyInterfaces(Class remoteClass) {
		if (remoteClass.isInterface()) {
			return new Class[] { remoteClass };
		}
	
		Set<Class> remoteList = new HashSet();
		addProxyInterfaces(remoteClass, remoteList);
		Class[] retVal = new Class[remoteList.size()];
		int i = 0;
		for (Class aClass: remoteList) {
			retVal[i] = aClass;
			i++;
		}
//		for (int i = 0; i < retVal.length; i++) {
//			retVal[i] = remoteList.get(i);
//		}
		return retVal;
	}


//	public static Class[] getProxyInterfaces(Class remoteClass) {
//		if (remoteClass.isInterface()) {
//			return new Class[] { remoteClass };
//		}
//		List<Class> remoteList = new ArrayList();
//		Class[] interfaces = remoteClass.getInterfaces();
//		if (interfaces.length == 1)  {
//			remoteList.add(interfaces[0]); // only interface, no choice
//		}  else {
//		for (int i = 0; i < interfaces.length; i++) {
//			Class anInterface = interfaces[i]; // we are not looking for instances of Remote
////			if (Remote.class.isAssignableFrom(anInterface)) {
//				remoteList.add(anInterface);
//				// return anInterface;
////			}
//		}
//		}
//		Class[] retVal = new Class[remoteList.size()];
//		for (int i = 0; i < retVal.length; i++) {
//			retVal[i] = remoteList.get(i);
//		}
//		return retVal;
//	}

	// public static boolean isList(Object o) {
	// return getListAddMethod(o.getClass()) != null &&
	// getListGetMethod(o.getClass()) != null;
	// }
	public static boolean isList(Class c) {
		Method listAddMethod = getListAddMethod(c);
		Method listGetMethod = getListGetMethod(c);
		Method listSizeMethod = getListSizeMethod(c);

		return listAddMethod != null
				&& listGetMethod != null
				&& listSizeMethod != null
				&& listGetMethod.getReturnType() == listAddMethod
						.getParameterTypes()[0];
	}

	public static int listSize(Object list) {
		try {
			Method sizeMethod = getListSizeMethod(list.getClass());
			return (Integer) sizeMethod.invoke(list, emptyArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static Object listGet(Object list, int index) {
		try {
			Method getMethod = getListGetMethod(list.getClass());
			Object[] args = { index };
			return getMethod.invoke(list, args);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static Object listAdd(Object list, Object element) {
		try {
			Method addMethod = getListAddMethod(list.getClass());
			Object[] addArgs = { element };
			return addMethod.invoke(list, addArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static Method getListAddMethod(Class objectClass) {
		try {
			Method[] methods = objectClass.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(LIST_ADD)
						&& method.getParameterTypes().length == 1) {
					
//				System.out.println("returned add method for:" + objectClass + "Method:" + method);
				

				return method;
			    }
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static Method getListGetMethod(Class objectClass) {
		try {
			Class[] paramTypes = { Integer.TYPE };
			return objectClass.getMethod(LIST_GET, paramTypes);
		} catch (Exception e) {
			return null;
		}
	}

	public static Method getListSizeMethod(Class objectClass) {
		try {
			return objectClass.getMethod(LIST_SIZE, emptyClassArgs);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isTransient(Method method) {
	
		return method.getAnnotation(Transient.class) != null || isJavaBeansTransient(method);
	}
	
	public static boolean isJavaBeansTransient(Method method) {
		java.beans.Transient javaBeansTransient = method.getAnnotation(java.beans.Transient.class);
		return javaBeansTransient != null && javaBeansTransient.value();
	}

	public static Set<Class> getSuperTypes(Class c) {
		Set<Class> retVal = new HashSet();
		getSuperTypes(c, retVal);
		return retVal;
	}

	static void getSuperTypes(Class c, Set<Class> superTypes) {
		superTypes.add(c);
		Class[] interfaces = c.getInterfaces();
		for (Class anInterface : interfaces) {
			getSuperTypes(anInterface, superTypes);
		}
		getSuperTypes(c.getSuperclass(), superTypes);

	}

}
