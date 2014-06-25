package util.session;

import java.util.Collection;

public interface SessionMessageListener {
	void userJoined(String aUserName, String anApplicationName,
			String aSessionName, boolean isNewSession, boolean isNewApplication,
			Collection<String> allUsers);

	void userLeft(String userName, String theApplicationName);
}
