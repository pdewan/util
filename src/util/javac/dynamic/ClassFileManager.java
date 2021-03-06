package util.javac.dynamic;

import java.io.IOException;
import java.security.SecureClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;

public class ClassFileManager extends
SimpleClassFileManager {
/**
* Instance of JavaClassObject that will store the
* compiled bytecode of our class
*/
private JavaClassObject jclassObject;

protected Map<String, JavaClassObject> javaClassesObjects = new HashMap();
protected Map<String, byte[]> javaClassesBytes = new HashMap();



/**
* Will initialize the manager with the specified
* standard java file manager
*
* @param standardManger
*/
//public ClassFileManager(StandardJavaFileManager
//standardManager) {
//super(standardManager);
//}

public ClassFileManager(StandardJavaFileManager standardManager) {
		super(standardManager);
		// TODO Auto-generated constructor stub
	}

/**
* Will be used by us to get the class loader for our
* compiled class. It creates an anonymous class
* extending the SecureClassLoader which uses the
* byte code created by the compiler and stored in
* the JavaClassObject, and returns the Class for it
*/
@Override
public SecureClassLoader getClassLoader(Location location) {
return new SecureClassLoader() {
    @Override
    protected Class<?> findClass(String name)
        throws ClassNotFoundException {
        byte[] b = jclassObject.getBytes();
        return super.defineClass(name, jclassObject
            .getBytes(), 0, b.length);
    }
};
}

public byte[] getClassBytes() {
	return (jclassObject == null)?null:jclassObject.getBytes();
//	return jclassObject.getBytes();
}

/**
* Gives the compiler an instance of the JavaClassObject
* so that the compiler can write the byte code into it.
*/
@Override
public SimpleJavaFileObject getJavaFileForOutput(Location location,
String className, Kind kind, FileObject sibling)
    throws IOException {
    jclassObject = new JavaClassObject(className, kind);
    javaClassesObjects.put(className, jclassObject);
return jclassObject;
}
public Map<String, byte[]> getJavaClassesBytes() {
	return javaClassesBytes;
}
}
