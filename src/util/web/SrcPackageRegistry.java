package util.web;

import java.util.HashMap;
import java.util.Map;

public class SrcPackageRegistry extends PackageRegistry {
	public static Map<String, String> registry = new HashMap();

	public static void register(String aPackageName, String aURL) {
		register(registry, aPackageName, aURL);
	}

	// public static String bestURL(ClassProxy aClass) {
	// return bestURL(aClass.getClass());
	//
	// }
	public static String bestURL(Class aClass) {
		return bestURL(aClass.getPackage().getName());
	}

	public static String bestURL(String aPackageName) {
		return bestURL(registry, aPackageName);
		// String bestMatch = "";
		// for (String aRegisteredPackageName: registry.keySet()) {
		// if (aPackageName.startsWith(aRegisteredPackageName) &&
		// aRegisteredPackageName.length() > bestMatch.length() ) {
		// bestMatch = aRegisteredPackageName;
		// }
		// }
		// return registry.get(bestMatch);
	}

}
