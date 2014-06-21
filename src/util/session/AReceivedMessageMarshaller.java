package util.session;

import java.util.Map;

public class AReceivedMessageMarshaller implements ReceivedMessageCreator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessageCreator#newObject(java.lang.String,
	 * util.session.MessageReceiver, java.lang.Object)
	 */
	public ReceivedMessage newObject(String clientName, MessageReceiver client,
			Object value) {
		return new AReceivedMessage(ReceivedMessageType.NewObject, clientName,
				client, null, value, false, false, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * util.session.ReceivedMessageCreator#userJoined(util.session.ProcessGroup,
	 * util.session.SerializedProcessGroups, java.util.Map, java.lang.String,
	 * util.session.MessageReceiver, java.lang.String, boolean, boolean)
	 */
	public ReceivedMessage userJoined(ProcessGroup processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<MessageReceiver, String> theClients, String clientName,
			MessageReceiver client, String theApplicationName,
			boolean newSession, boolean newApplication) {
		return new AReceivedMessage(ReceivedMessageType.ClientJoined,
				clientName, client, theApplicationName, null, newSession,
				newApplication, theClients, serializedProcessGroups,
				processGroup);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessageCreator#userLeft(java.lang.String,
	 * util.session.MessageReceiver, java.lang.String)
	 */
	public ReceivedMessage userLeft(String theClientName,
			MessageReceiver theClient, String theApplicationName) {
		return new AReceivedMessage(ReceivedMessageType.ClientLeft,
				theClientName, theClient, theApplicationName, null, false,
				false, null, null, null);

	}

}
