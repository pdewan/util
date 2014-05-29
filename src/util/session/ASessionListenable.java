package util.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASessionListenable {
	Map<MessageReceiver, String> clients = new HashMap();
	List<SessionMessageReceiver> listeners = new ArrayList();
	List<SessionMessageReceiverLocal> listenersLocal = new ArrayList();

	ReceivedMessageCreator receivedMessageCreator = new AReceivedMessageCreator();

	public void addSessionListener(SessionMessageReceiver theListener) {
		if (listeners.contains(theListener))
			return;
		listeners.add(theListener);
	}

	public void removeSessionListener(SessionMessageReceiver theListener) {
		listeners.remove(theListener);
	}

	public void addSessionListenerLocal(SessionMessageReceiverLocal theListener) {
		if (listenersLocal.contains(theListener))
			return;
		listenersLocal.add(theListener);
	}

	public void removeSessionListenerLocal(
			SessionMessageReceiverLocal theListener) {
		listeners.remove(theListener);
	}

	void notifyClientJoinedRemote(ProcessGroup processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<MessageReceiver, String> theClients, String theSessionName,
			String theClientName, MessageReceiver theClient,
			String theApplicationName, boolean newSession,
			boolean newApplication) {
		try {
			for (SessionMessageReceiver listener : listeners) {
				ReceivedMessage receivedMessage = receivedMessageCreator
						.userJoined(processGroup, serializedProcessGroups,
								theClients, theClientName, theClient,
								theApplicationName, newSession, newApplication);
				listener.newMessage(receivedMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void notifyClientJoinedLocal(ProcessGroupLocal processGroupLocal,
			SerializedProcessGroups serializedProcessGroups,
			Map<MessageReceiver, String> theClients, String theSessionName,
			String theClientName, MessageReceiver theClient,
			String theApplicationName, boolean newSession,
			boolean newApplication) {
		for (SessionMessageReceiverLocal listener : listenersLocal) {
			listener.userJoined(processGroupLocal, serializedProcessGroups,
					theClients, theClientName, theClient, theApplicationName,
					newSession, newApplication);
		}
	}

	void notifyClentLeftLocal(String theClientName, MessageReceiver theClient,
			String theApplicationName) {
		for (SessionMessageReceiverLocal listener : listenersLocal) {
			ReceivedMessage receivedMessage = receivedMessageCreator.userLeft(
					theClientName, theClient, theApplicationName);
			listener.userLeft(theClientName, theClient, theApplicationName);
		}
	}

	void notifyClentLeftRemote(String theClientName, MessageReceiver theClient,
			String theApplicationName) {
		for (SessionMessageReceiver listener : listeners) {
			try {
				ReceivedMessage receivedMessage = receivedMessageCreator
						.userLeft(theClientName, theClient, theApplicationName);
				listener.newMessage(receivedMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
