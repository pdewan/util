package util.session;

import java.util.Map;

public interface DelayedMessageReceiver {
	public void addReceivedMessageListener(ReceivedMessageListener listener);

	public void removeReceivedMessageListener(ReceivedMessageListener listener);

	public void addSessionMessageListener(SessionMessageListener listener);

	public void removeSessionMessageListener(SessionMessageListener listener);

	public void addPeerMessageListener(PeerMessageListener listener);

	public void removePeerMessageListener(PeerMessageListener listener);

	public void setSerializedMulticastGroups(SerializedProcessGroups newVal);

	public SerializedProcessGroups getSerializedMulticastGroups();

	public void delayedNewObject(String clientName, Object value);

	public void delayedUserJoined(Map<MessageReceiver, String> theClients,
			String theClientName, MessageReceiver theClient,
			String theApplicationName, boolean newSession,
			boolean newApplication);

	public void delayedUserLeft(String theClientName,
			MessageReceiver theClient, String theApplicationName);

	MessageFilter<ReceivedMessage> getReceivedMessageQueuer();

	public void setClients(Map<MessageReceiver, String> theClients);

}
