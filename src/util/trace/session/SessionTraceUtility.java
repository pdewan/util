package util.trace.session;

import util.trace.Traceable;

public class SessionTraceUtility {
	public static boolean clientJoined(Traceable aTraceable, String aUserName) {
		if (!(aTraceable instanceof ClientJoined))
			return false;
		ClientJoined aClientJoined = (ClientJoined) aTraceable;
		return aClientJoined.getUserName().equals(aUserName);

	}

}
