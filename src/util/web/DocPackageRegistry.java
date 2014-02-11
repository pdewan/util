package util.web;

import java.util.HashMap;
import java.util.Map;

import util.annotations.Visible;

// equiavelent to creating two different instances
public class DocPackageRegistry extends PackageRegistry {
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

	@Visible(false)
	public static String toMaybeExpandedURLString(String aClassName,
			String uriString) {
		if (uriString == null || uriString.length() == 0)
			return null;
		String finalString = uriString;
		if (!uriString.contains("://")) {
			String home = bestURL(aClassName);
			if (home == null)
				// home = (String)
				// AttributeNames.getDefaultOrSystemDefault(DocPackageRegistry.URI_HOME);
				home = DEFAULT_URI_HOME;

			if (home == null)
				return null;
			if (!home.endsWith("/")) {
				home += "/";
			}
			finalString = home + uriString;
		}

		return finalString;

	}

	public static final String URI_HOME = "URI HOME";
	// public static final String DEFAULT_URI_HOME =
	// "http://www.cs.unc.edu/~dewan/comp114/f11/";
	public static final String DEFAULT_URI_HOME = "http://www.cs.unc.edu/~dewan/comp401/current/";

}
