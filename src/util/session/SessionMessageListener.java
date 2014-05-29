package util.session;

import java.util.Collection;

public interface SessionMessageListener {
	void userJoined(String userName, Object theApplicationName,
			String theSessionName, boolean newSession, boolean newApplication,
			Collection<String> allUsers);

	void userLeft(String userName, Object theApplicationName);
}
