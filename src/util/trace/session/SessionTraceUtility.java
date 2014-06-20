package util.trace.session;

import util.trace.Traceable;

public class SessionTraceUtility {
	public static boolean clientJoined(Traceable aTraceable, String aUserName) {
		if (!(aTraceable instanceof ServerClientJoined))
			return false;
		ServerClientJoined aClientJoined = (ServerClientJoined) aTraceable;
		return aClientJoined.getUserName().equals(aUserName);

	}

}
