package util.session;

import java.util.Collection;

public interface SessionMessageListener {
	void clientJoined(String aClientName, String anApplicationName,
			String aSessionName, boolean isNewSession, boolean isNewApplication,
			Collection<String> allUsers);

	void clientLeft(String aClientName, String anApplicationName);
}
