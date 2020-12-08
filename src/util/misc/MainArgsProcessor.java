/*
 * Created on Feb 9, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import util.trace.Tracer;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

public class MainArgsProcessor {
	public static final Object NO_VALUE = "";

	public static void printFlags(String[] regFlags, String[] boolFlags) {
		System.err.println("Boolean Flags Supported:");
		for (int i = 0; i < boolFlags.length; i++) {
			System.out.println(boolFlags[i]);
		}
		System.err.println("Regular Flags Supported:");
		for (int i = 0; i < regFlags.length; i++) {
			System.out.println(regFlags[i]);
		}
	}

	static boolean isFlagArg(String flag, Vector regFlagVector,
			Vector boolFlagVector, Vector dupFlagVector) {
		return regFlagVector.contains(flag) || boolFlagVector.contains(flag)
				|| dupFlagVector.contains(flag);
	}

	public static Hashtable toTable(String[] regFlags, String[] boolFlags,
			String[] dupFlags, String[] args) {
		Hashtable retVal = new Hashtable();
		Vector regFlagVector = Common.arrayToVector(regFlags);
		Vector boolFlagVector = Common.arrayToVector(boolFlags);
		Vector dupFlagVector = Common.arrayToVector(dupFlags);

		Vector argVector = Common.arrayToVector(args);
		for (int i = 0; i < regFlagVector.size(); i++) {
			String flag = (String) regFlagVector.elementAt(i);
			int flagIndex = argVector.indexOf(flag);
			if (flagIndex < 0)
				continue;
			if (flagIndex == argVector.size() - 1) {
				missingArgument(flag);
				argVector.remove(flag);
				continue;
			}
			String flagVal = (String) argVector.elementAt(flagIndex + 1);

			if (isFlagArg(flagVal, regFlagVector, boolFlagVector, dupFlagVector)) {
				missingArgument(flag);
				argVector.remove(flag);
				continue;
			}

			Object oldVal = retVal.put(flag, flagVal);
			if (oldVal != null) {
				Tracer.error("Duplicate flag value for flag: " + flagVal);
			}
			argVector.remove(flag);
			argVector.remove(flagVal);
		}
		for (int i = 0; i < dupFlagVector.size(); i++) {
			String flag = (String) dupFlagVector.elementAt(i);
			List flags = new ArrayList();
			retVal.put(flag, flags);
			int flagIndex;
			while (true) {

				flagIndex = argVector.indexOf(flag);
				if (flagIndex < 0)
					break;
				if (flagIndex == argVector.size() - 1) {
					missingArgument(flag);
					argVector.remove(flag);
					continue;
				}
				String flagVal = (String) argVector.elementAt(flagIndex + 1);
				if (isFlagArg(flagVal, regFlagVector, boolFlagVector,
						dupFlagVector)) {
					missingArgument(flag);
					argVector.remove(flag);
					continue;
				}

				// // this check should never be successful
				// if (regFlagVector.contains(flagVal)) {
				// missingArgument (flag);
				// argVector.remove(flag);
				// continue;
				// }

				flags.add(flagVal);
				argVector.remove(flag);
				argVector.remove(flagVal);
			}
		}
		for (int i = 0; i < boolFlagVector.size(); i++) {
			String flag = (String) boolFlagVector.elementAt(i);
			int flagIndex = argVector.indexOf(flag);
			if (flagIndex < 0)
				continue;
			retVal.put(flag, NO_VALUE);
			argVector.remove(flag);
		}
		for (int i = 0; i < argVector.size(); i++) {
			System.err.println("Warning: Unrecognized main argument:"
					+ argVector.elementAt(i));
		}
		if (argVector.size() > 0)
			printFlags(regFlags, boolFlags);
		return retVal;

	}

	static void missingArgument(String flag) {
		System.err.println("Warning: " + flag
				+ " should be followed by an argument. Ignoring flag");
		// flagTable.put(flag, NO_VALUE);
	}

}
