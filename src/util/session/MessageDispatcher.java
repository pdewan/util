package util.session;

import java.util.Map;
/*
 * Seems more reasonable to have this interface extend a Message Receiver
 * but the latter is a Remote so that may be a reason
 * allows processes to add themselves as observables of this
 * implemneted by two classes, ADelayedMessageReceiver, ASessionManagerClient
 */
public interface MessageDispatcher {
	public void addReceivedMessageListener(ReceivedMessageListener listener);

	public void removeReceivedMessageListener(ReceivedMessageListener listener);

	public void addSessionMessageListener(SessionMessageListener listener);

	public void removeSessionMessageListener(SessionMessageListener listener);

	public void addPeerMessageListener(PeerMessageListener listener);

	public void removePeerMessageListener(PeerMessageListener listener);

	public void setSerializedMulticastGroups(SerializedProcessGroups newVal);

	public SerializedProcessGroups getSerializedMulticastGroups();

	public void delayedNewObject(String clientName, Object value);

	public void delayedUserJoined(Map<ObjectReceiver, String> theClients,
			String theClientName, ObjectReceiver theClient,
			String theApplicationName, boolean newSession,
			boolean newApplication);

	public void delayedUserLeft(String theClientName,
			ObjectReceiver theClient, String theApplicationName);

	MessageFilter<ReceivedMessage> getReceivedMessageQueuer();

	public void setClients(Map<ObjectReceiver, String> theClients);

}
