package util.introspect;

public class ClassLoaderFactory {
	static ClassLoader classLoader = ClassLoaderFactory.class.getClassLoader();
	public static ClassLoader getCurrentClassLoader() {
		return classLoader;
	}
	public static void setCurrentClassLoader(ClassLoader aClassLoader) {
		classLoader = aClassLoader;	
	}
}
