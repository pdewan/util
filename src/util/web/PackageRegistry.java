package util.web;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import util.annotations.Visible;

public class PackageRegistry {

	public static void register(Map<String, String> registry,
			String aPackageName, String aURL) {
		registry.put(aPackageName, aURL);
	}

	// public static String bestURL(Map<String, String> registry, ClassProxy
	// aClass) {
	// return bestURL(registry, aClass.getClass());
	//
	// }
	public static String bestURL(Map<String, String> registry, Class aClass) {
		return bestURL(registry, aClass.getPackage().getName());
	}

	public static String bestURL(Map<String, String> registry,
			String aPackageName) {
		String bestMatch = "";
		for (String aRegisteredPackageName : registry.keySet()) {
			if (aPackageName.startsWith(aRegisteredPackageName)
					&& aRegisteredPackageName.length() > bestMatch.length()) {
				bestMatch = aRegisteredPackageName;
			}
		}
		return registry.get(bestMatch);
	}

	@Visible(false)
	public static String toKeyword(String uriString) {
		if (uriString == null || uriString.length() == 0)
			return null;
		int lastSlash = uriString.lastIndexOf("/");
		String withoutSlash = uriString;
		if (lastSlash != -1) {
			withoutSlash = uriString.substring(lastSlash + 1);
		}
		int lastQuestion = withoutSlash.indexOf("?");
		String withoutSlashAndQuestion = withoutSlash;
		if (lastQuestion != -1) {
			withoutSlashAndQuestion = withoutSlash.substring(0, lastQuestion);
		}
		return withoutSlashAndQuestion;
	}

	@Visible(false)
	public static URI toURI(String aClassName, String uriString) {
		return java.net.URI.create(DocPackageRegistry.toMaybeExpandedURLString(
				aClassName, uriString));

	}

}
