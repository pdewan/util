package util.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.trace.Tracer;
import util.trace.session.ClientJoinInformationUpdated;
import util.trace.session.ClientLeaveInformationUpdated;
import util.trace.session.ClientJoinNotificationDistributedToListeners;
import util.trace.session.ClientJoinNotificationUnmarshalled;
import util.trace.session.ClientLeaveNotificationDistributedToListeners;
import util.trace.session.ReceivedMessageDistributedToListeners;
/*
 * Keeps session state and also distributes information to listeners
 * One for each application in the session
 * Bad design to ave both functions in the module
 */
@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public class AnUmarshalledReceivedMessageDispatcherAndSessionStateManager implements /*
												 * Communicator,
												 * MessageReceiver,
												 */MessageDispatcher,
		Serializable {
	MessageProcessor<ReceivedMessage> receivedMessageProcessor;
	MessageFilter<ReceivedMessage> receivedMessageQueuer;
	String clientName;
	String sessionName;
	String applicationName;
	ProcessGroup processGroup;
	List<SessionMessageListener> sessionMessageListeners = new ArrayList();
	List<PeerMessageListener> peerMessageListeners = new ArrayList();
	Map<ObjectReceiver, String> clients = new HashMap();
	SerializedProcessGroups serializedMulticastGroups;
	CommunicatorInternal communicator;

	public AnUmarshalledReceivedMessageDispatcherAndSessionStateManager(String theServerHost, String theSessionName,
			String theApplicationName, String theClientName,
			CommunicatorInternal theCommunicator) {
		create(theServerHost, theSessionName, theApplicationName,
				theClientName, theCommunicator);
	}

	public MessageFilter<ReceivedMessage> getReceivedMessageQueuer() {
		if (receivedMessageQueuer == null) {
			setReceivedMessageQueuer(AReceivedMessageFilterSelector
					.getMessageFilterFactory().getMessageFilter());
		}
		return receivedMessageQueuer;
	}

	public void create(String serverHost, String theSessionName,
			String theApplicationName, String theClientName,
			CommunicatorInternal theCommunicator) {
		try {
			clientName = theClientName;
			sessionName = theSessionName;
			applicationName = theApplicationName;
			receivedMessageProcessor = new AReceivedMessageUmarshaller(this, theClientName);
			communicator = theCommunicator;
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.fatalError("Could not lookup multicast server on host:"
					+ serverHost);
		}
	}

	public synchronized void delayedNewObject(String clientName, Object value) {
		ReceivedMessageDistributedToListeners.newCase(CommunicatorSelector.getProcessName(), value, clientName, this);

		for (PeerMessageListener listener : peerMessageListeners) {
			listener.objectReceived(value, clientName);
		}
	}
    /*
     * notification as already been distributed, this is another notification
     * keeps awareness information in system data structures so users who wish to \
     * poll for information need not define listeners
     */
	public synchronized void delayedUserJoined(
			Map<ObjectReceiver, String> theClients, String theClientName,
			ObjectReceiver theClient, String theApplicationName,
			boolean newSession, boolean newApplication) {
		
		ClientJoinNotificationDistributedToListeners.newCase(
				CommunicatorSelector.getProcessName(),
				theClientName, theApplicationName, getSessionName(), this);
//		if (clientName.equals(theClientName)) {
		for (SessionMessageListener listener : sessionMessageListeners) {
			listener.userJoined(theClientName, theApplicationName,
					getSessionName(), newSession, newApplication,
					clients.values());
		}
		if (applicationName != null && applicationName.equals(theApplicationName)) {
			ClientJoinInformationUpdated.newCase(
					CommunicatorSelector.getProcessName(),
					theClientName, theApplicationName, getSessionName(), this);
			clients.put(theClient, theClientName);
		}

	}

	public synchronized void delayedUserLeft(String theClientName,
			ObjectReceiver theClient, String theApplicationName) {
		ClientLeaveNotificationDistributedToListeners.newCase(
				CommunicatorSelector.getProcessName(),
				theClientName, theApplicationName, getSessionName(), this);
		for (SessionMessageListener listener : sessionMessageListeners) {
			listener.userLeft(theClientName, theApplicationName);
		}
		if (applicationName.equals(theApplicationName)) {
			ClientLeaveInformationUpdated.newCase(
					CommunicatorSelector.getProcessName(),
					theClientName, theApplicationName, getSessionName(), this);
			clients.remove(theClientName);
		}
	}

	public String getSessionName() {
		return sessionName;
	}

	public String getUserName() {
		return clientName;
	}

	@Override
	public void addReceivedMessageListener(ReceivedMessageListener listener) {
		addSessionMessageListener(listener);
		addPeerMessageListener(listener);
	}

	@Override
	public synchronized void removeReceivedMessageListener(
			ReceivedMessageListener listener) {
		removeSessionMessageListener(listener);
		removePeerMessageListener(listener);
	}

	@Override
	public synchronized void addSessionMessageListener(
			SessionMessageListener listener) {
		if (sessionMessageListeners.contains(listener))
			return;
		sessionMessageListeners.add(listener);
	}

	@Override
	public synchronized void removeSessionMessageListener(
			SessionMessageListener listener) {
		sessionMessageListeners.remove(listener);
	}

	@Override
	public synchronized void addPeerMessageListener(PeerMessageListener listener) {
		if (peerMessageListeners.contains(listener))
			return;
		peerMessageListeners.add(listener);
	}

	@Override
	public synchronized void removePeerMessageListener(
			PeerMessageListener listener) {
		peerMessageListeners.remove(listener);
	}

	public void setClients(Map<ObjectReceiver, String> theClients) {
		clients = theClients;
		communicator.setClients(theClients); // the clients are set either in the relayer or in the direct communicator
	}

	@Override
	public void setSerializedMulticastGroups(SerializedProcessGroups newVal) {
		serializedMulticastGroups = newVal;
	}

	@Override
	public SerializedProcessGroups getSerializedMulticastGroups() {
		return serializedMulticastGroups;
	}

	public void setReceivedMessageQueuer(
			MessageFilter<ReceivedMessage> theReceivedMessageQueuer) {
		receivedMessageQueuer = theReceivedMessageQueuer;
		receivedMessageQueuer.setMessageProcessor(receivedMessageProcessor);

	}

}
