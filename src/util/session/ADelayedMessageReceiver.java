package util.session;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;
import util.trace.session.ReceivedMessageDistributedToListeners;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public class ADelayedMessageReceiver implements /*
												 * Communicator,
												 * MessageReceiver,
												 */DelayedMessageReceiver,
		Serializable {
	MessageProcessor<ReceivedMessage> receivedMessageProcessor;
	MessageFilter<ReceivedMessage> receivedMessageQueuer;
	String clientName;
	String sessionName;
	String applicationName;
	ProcessGroup processGroup;
	List<SessionMessageListener> sessionMessageListeners = new ArrayList();
	List<PeerMessageListener> peerMessageListeners = new ArrayList();
	Map<MessageReceiver, String> clients = new HashMap();
	SerializedProcessGroups serializedMulticastGroups;
	CommunicatorInternal communicator;

	public ADelayedMessageReceiver(String theServerHost, String theSessionName,
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
			receivedMessageProcessor = new AReceivedMessageProcessor(this);
			communicator = theCommunicator;
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.fatalError("Could not lookup multicast server on host:"
					+ serverHost);
		}
	}

	public synchronized void delayedNewObject(String clientName, Object value) {
		ReceivedMessageDistributedToListeners.newCase(value, clientName, this);
		for (PeerMessageListener listener : peerMessageListeners) {
			listener.objectReceived(value, clientName);
		}
	}

	public synchronized void delayedUserJoined(
			Map<MessageReceiver, String> theClients, String theClientName,
			MessageReceiver theClient, String theApplicationName,
			boolean newSession, boolean newApplication) {
		for (SessionMessageListener listener : sessionMessageListeners) {
			listener.userJoined(theClientName, theApplicationName,
					getSessionName(), newSession, newApplication,
					clients.values());
		}
		if (applicationName.equals(theApplicationName))
			clients.put(theClient, theClientName);

	}

	public synchronized void delayedUserLeft(String theClientName,
			MessageReceiver theClient, String theApplicationName) {
		for (SessionMessageListener listener : sessionMessageListeners) {
			listener.userLeft(theClientName, theApplicationName);
		}
		if (applicationName.equals(theApplicationName))
			clients.remove(theClientName);
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

	public void setClients(Map<MessageReceiver, String> theClients) {
		clients = theClients;
		communicator.setClients(theClients);
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
