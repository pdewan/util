package util.trace.session;

import util.trace.Traceable;

public class SessionTraceUtility {
	public static boolean clientJoined(Traceable aTraceable, String aUserName) {
		if (!(aTraceable instanceof ServerClientJoined))
//		if (!(aTraceable instanceof ClientJoinFinished))

			return false;
		ServerClientJoined aClientJoined = (ServerClientJoined) aTraceable;
//		ClientJoinFinished aClientJoined = (ClientJoinFinished) aTraceable;
		return aClientJoined.getUserName().equals(aUserName);

	}

}
