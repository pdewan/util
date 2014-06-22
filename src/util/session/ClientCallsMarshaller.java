package util.session;

import java.util.Map;
/*
 * Looks as if the main reason for this is to convert or marshall calls to client into
 * received messages that can be delayed. Also maybe so that the messages can be
 * transformed or queued
 */
public interface ClientCallsMarshaller {

	public abstract ReceivedMessage newObject(String clientName,
			ObjectReceiver client, Object value);

	public abstract ReceivedMessage userJoined(ProcessGroup processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<ObjectReceiver, String> theClients, String clientName,
			ObjectReceiver client, String theApplicationName,
			boolean newSession, boolean newApplication);

	public abstract ReceivedMessage userLeft(String theClientName,
			ObjectReceiver theClient, String theApplicationName);

}