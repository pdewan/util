package util.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.trace.session.ServerJoinNotificationDeliveredToListeners;
import util.trace.session.ServerRemoteJoinNotificationMarshalled;
import util.trace.session.ServerRemoteJoinNotificationSent;
import util.trace.session.ServerRemoteLeaveNotificationMarshalled;
import util.trace.session.ServerRemoteLeaveNotificationSent;

public class ASessionListenable {
	Map<ObjectReceiver, String> clients = new HashMap();
	List<MessageReceiver> listeners = new ArrayList();
	List<SessionMessageReceiverLocal> listenersLocal = new ArrayList();

	ClientCallsMarshaller receivedMessageCreator = new AClientCallsMarshaller();

	public void addSessionListener(MessageReceiver theListener) {
		if (listeners.contains(theListener))
			return;
		listeners.add(theListener);
	}

	public void removeSessionListener(MessageReceiver theListener) {
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
			Map<ObjectReceiver, String> theClients, String theSessionName,
			String theClientName, ObjectReceiver theClient,
			String theApplicationName, boolean newSession,
			boolean newApplication) {
		try {
			for (MessageReceiver listener : listeners) {
				ReceivedMessage receivedMessage = receivedMessageCreator
						.userJoined(processGroup, serializedProcessGroups,
								theClients, theClientName, theClient,
								theApplicationName, newSession, newApplication);
				ServerRemoteJoinNotificationMarshalled.newCase(
						CommunicatorSelector.getProcessName(), 
						receivedMessage, theClientName, this);
				ServerRemoteJoinNotificationSent.newCase(
						CommunicatorSelector.getProcessName(), 
						receivedMessage, theClientName, this);
				listener.newMessage(receivedMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void notifyClientJoinedLocal(ProcessGroupLocal processGroupLocal,
			SerializedProcessGroups serializedProcessGroups,
			Map<ObjectReceiver, String> theClients, String theSessionName,
			String theClientName, ObjectReceiver theClient,
			String theApplicationName, boolean newSession,
			boolean newApplication) {
		ServerJoinNotificationDeliveredToListeners.newCase(
				CommunicatorSelector.getProcessName(), null, theClientName, this);
		for (SessionMessageReceiverLocal listener : listenersLocal) {
			listener.userJoined(processGroupLocal, serializedProcessGroups,
					theClients, theClientName, theClient, theApplicationName,
					newSession, newApplication);
		}
	}

	void notifyClentLeftLocal(String theClientName, ObjectReceiver theClient,
			String theApplicationName) {
		ServerJoinNotificationDeliveredToListeners.newCase(
				CommunicatorSelector.getProcessName(), null, theClientName, this);
		for (SessionMessageReceiverLocal listener : listenersLocal) {
			ReceivedMessage receivedMessage = receivedMessageCreator.userLeft(
					theClientName, theClient, theApplicationName);
			listener.userLeft(theClientName, theClient, theApplicationName);
		}
	}

	void notifyClentLeftRemote(String theClientName, ObjectReceiver theClient,
			String theApplicationName) {
		for (MessageReceiver listener : listeners) {
			try {
				ReceivedMessage receivedMessage = receivedMessageCreator
						.userLeft(theClientName, theClient, theApplicationName);
				ServerRemoteLeaveNotificationMarshalled.newCase(
						CommunicatorSelector.getProcessName(), 
						receivedMessage, theClientName, this);
				ServerRemoteLeaveNotificationSent.newCase(
						CommunicatorSelector.getProcessName(), 
						receivedMessage, theClientName, this);
				listener.newMessage(receivedMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
