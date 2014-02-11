package util.session;

import java.util.Map;

public interface ReceivedMessageCreator {

	public abstract ReceivedMessage newObject(String clientName,
			MessageReceiver client, Object value);

	public abstract ReceivedMessage userJoined(ProcessGroup processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<MessageReceiver, String> theClients, String clientName,
			MessageReceiver client, String theApplicationName,
			boolean newSession, boolean newApplication);

	public abstract ReceivedMessage userLeft(String theClientName,
			MessageReceiver theClient, String theApplicationName);

}