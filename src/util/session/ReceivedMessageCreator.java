package util.session;

import java.util.Map;
/*
 * Looks as if the main reason for this is to convert calls into
 * messages that can be delayed. Also maybe so that the messages can be
 * transformed or queued
 */
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