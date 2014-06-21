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
import util.models.ABoundedBuffer;
import util.trace.Tracer;
import util.trace.session.ClientJoinFinished;
import util.trace.session.ClientJoinInitiated;
import util.trace.session.MessagePutInQueue;
import util.trace.session.QueueCreated;
import util.trace.session.ThreadCreated;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public abstract class ASessionManagerClient extends ASessionListenable
		implements DelayManager, Communicator, MessageReceiver,
		DelayedMessageReceiver, Serializable {
	MessageProcessor<SentMessage> sentMessageProcessor;
	MessageProcessor<ReceivedMessage> receivedMessageProcessor;
	MessageFilter<SentMessage> sentMessageQueuer;
	MessageFilter<ReceivedMessage> receivedMessageQueuer;
	MessageReceiver exportedMessageReceiver;
	String clientName;
	String sessionName;
	String applicationName;
	ProcessGroup processGroup;
	SessionManager sessionManager;
	List<SessionMessageListener> sessionMessageListeners = new ArrayList();
	List<PeerMessageListener> peerMessageListeners = new ArrayList();
	ABoundedBuffer<SentMessage> outputMessageQueue;
	MessageSenderRunnable messageSenderRunnable;
	Thread messageSenderThread;
	Map<MessageReceiver, String> clients = new HashMap();
	SerializedProcessGroups serializedMulticastGroups;
	ABoundedBuffer<ReceivedMessage> inputMessageQueue;
	MessageReceiverRunnable messageReceiverRunnable;
	Thread messageReceiverThread;
	int delayToServer, delayVariation;
	boolean inSession;
	JoinLock joinLock = new AJoinLock();

	public ASessionManagerClient(String theServerHost, String theSessionName,
			String theApplicationName, String theClientName) {
		create(theServerHost, theSessionName, theApplicationName, theClientName);
	}

	public ASessionManagerClient(String[] args) {
		if (args.length < 4) {
			Tracer.fatalError("Please supply server host name, session Name, and clientName as main argument");
		}
		create(args[0], args[1], args[2], args[3]);
	}

	SessionManager getSessionManagerHandle(String serverHost) {
		try {
			Registry registry = Common.getRegistry(serverHost);
			return (SessionManager) registry
					.lookup(SessionManager.SESSION_MANAGER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.fatalError("Could not lookup multicast server on host:"
					+ serverHost);
			return null;
		}
	}

	MessageReceiver generateRemoteProxyToSelf() {
		try {
			return (MessageReceiver) UnicastRemoteObject.exportObject(
					(MessageReceiver) this, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	MessageFilter<SentMessage> getSentMessageQueuer() {
		if (sentMessageQueuer == null) {
			setSentMessageQueuer(ASentMessageFilterSelector
					.getMessageFilterCreator().getMessageFilter());
		}
		return sentMessageQueuer;
	}

	void createOutputBufferAndThread() {
		outputMessageQueue = new ABoundedBuffer(AProcessGroup.OUTPUT_MESSAGE_QUEUE);
		QueueCreated.newCase(ACommunicatorSelector.getProcessName(), outputMessageQueue.getName(), this);

		sentMessageProcessor = new ASentMessageProcessor(outputMessageQueue);
		messageSenderRunnable = new AMessageSenderRunnable(outputMessageQueue,
				this, sessionManager);
		messageSenderThread = new Thread(messageSenderRunnable);
		messageSenderThread.setName("Message Sender");
		ThreadCreated.newCase(messageSenderThread.getName(), ACommunicatorSelector.getProcessName(), this);

		messageSenderThread.start();
	}

	public MessageFilter<ReceivedMessage> getReceivedMessageQueuer() {
		if (receivedMessageQueuer == null) {
			setReceivedMessageQueuer(AReceivedMessageFilterSelector
					.getMessageFilterFactory().getMessageFilter());
		}
		return receivedMessageQueuer;
	}

	void createInputBufferAndThread() {
		inputMessageQueue = new ABoundedBuffer(AMessageReceiver.INPUT_MESSAGE_QUEUE);
		QueueCreated.newCase(ACommunicatorSelector.getProcessName(), inputMessageQueue.getName(), this);

		receivedMessageProcessor = new AReceivedMessageUmarshaller(this);

		messageReceiverRunnable = new AMessageReceiverRunnable(
				inputMessageQueue, this, getReceivedMessageQueuer());
		messageReceiverThread = new Thread(messageReceiverRunnable);
		ThreadCreated.newCase(messageReceiverThread.getName(), ACommunicatorSelector.getProcessName(), this);

		messageReceiverThread.start();
	}

	@Override
	public void create(String serverHost, String theSessionName,
			String theApplicationName, String theClientName) {
		try {
			clientName = theClientName;
			sessionManager = getSessionManagerHandle(serverHost);
			sessionName = theSessionName;
			applicationName = theApplicationName;
			exportedMessageReceiver = generateRemoteProxyToSelf();
			createOutputBufferAndThread();
			createInputBufferAndThread();
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.fatalError("Could not lookup multicast server on host:"
					+ serverHost);
		}
	}

	@Override
	public void join() {
		ClientJoinInitiated.newCase(ACommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);

		asyncJoin();
		joinLock.waitFotJoin();
		ClientJoinFinished.newCase(ACommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);

	}

	public void asyncJoin() {

		Object[] args = { sessionName, applicationName, clientName,
				exportedMessageReceiver };
		SentMessage sentMessage = new ASentMessage(sessionName, applicationName, clientName,
				exportedMessageReceiver, SentMessageType.Join, args);
		MessagePutInQueue.newCase(ACommunicatorSelector.getProcessName(), sentMessage, null, outputMessageQueue.getName(), this);
		outputMessageQueue.put(sentMessage);
//		outputMessageQueue
//				.put(new ASentMessage(sessionName, applicationName, clientName,
//						exportedMessageReceiver, SentMessageType.Join, args));
		inSession = true;
	}

	@Override
	public void leave() {
		inSession = false;

	}

	@Override
	public void newObject(String clientName, MessageReceiver client,
			Object value) {
		Tracer.info("Client received newObject from:" + clientName);
		AReceivedMessage receivedMesage = new AReceivedMessage(
				ReceivedMessageType.NewObject, clientName, client, null, value,
				false, false, null, null, null);
		MessagePutInQueue.newCase(ACommunicatorSelector.getProcessName(), receivedMesage, receivedMesage.getClientName(),  inputMessageQueue.getName(), this);

		inputMessageQueue.put(receivedMesage);
	}

	public void delayedNewObject(String clientName, Object value) {
		for (PeerMessageListener listener : peerMessageListeners) {
			listener.objectReceived(value, clientName);
		}
	}

	public void delayedUserJoined(Map<MessageReceiver, String> theClients,
			String theClientName, MessageReceiver theClient,
			String theApplicationName, boolean newSession,
			boolean newApplication) {
		for (SessionMessageListener listener : sessionMessageListeners) {
			listener.userJoined(theClientName, theApplicationName,
					getSessionName(), newSession, newApplication,
					clients.values());
		}
		if (applicationName.equals(theApplicationName))
			clients.put(theClient, theClientName);

	}

	public void delayedUserLeft(String theClientName,
			MessageReceiver theClient, String theApplicationName) {
		for (SessionMessageListener listener : sessionMessageListeners) {
			listener.userLeft(theClientName, theApplicationName);
		}
		if (applicationName.equals(theApplicationName))
			clients.remove(theClientName);
	}

	public void userJoined(ProcessGroup processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<MessageReceiver, String> theClients, String clientName,
			MessageReceiver client, String theApplicationName,
			boolean newSession, boolean newApplication) throws RemoteException {
		setClients(theClients);
		setSerializedMulticastGroups(serializedProcessGroups);
		messageSenderRunnable.setProcessGroup(processGroup);
		joinLock.notifyJoined();
		AReceivedMessage receivedMesage = new AReceivedMessage(
				ReceivedMessageType.ClientJoined, clientName, client,
				theApplicationName, null, newSession, newApplication,
				theClients, serializedProcessGroups, processGroup);
		MessagePutInQueue.newCase(ACommunicatorSelector.getProcessName(), receivedMesage, receivedMesage.getClientName(), inputMessageQueue.getName(), this);

		inputMessageQueue.put(receivedMesage);

	}

	public void userLeft(String theClientName, MessageReceiver theClient,
			String theApplicationName) throws RemoteException {
		AReceivedMessage receivedMesage = new AReceivedMessage(
				ReceivedMessageType.ClientLeft, theClientName, theClient,
				theApplicationName, null, false, false, null, null, null);
		MessagePutInQueue.newCase(ACommunicatorSelector.getProcessName(), receivedMesage, receivedMesage.getClientName(), inputMessageQueue.getName(), this);

		inputMessageQueue.put(receivedMesage);
	}

	public int getMinimumDelayToServer() {
		return delayToServer;
	}

	public void setMinimumDelayToServer(int theDelay) {
		delayToServer = theDelay;
	}

	public int getDelayVariation() {
		return delayVariation;
	}

	public void setDelayVariation(int theDelay) {
		delayVariation = theDelay;
	}

	@Override
	public String getSessionName() {
		return sessionName;
	}

	@Override
	public String getUserName() {
		return clientName;
	}

	@Override
	public void addReceivedMessageListener(ReceivedMessageListener listener) {
		addSessionMessageListener(listener);
		addPeerMessageListener(listener);
	}

	@Override
	public void removeReceivedMessageListener(ReceivedMessageListener listener) {
		removeSessionMessageListener(listener);
		removePeerMessageListener(listener);
	}

	@Override
	public void addSessionMessageListener(SessionMessageListener listener) {
		if (sessionMessageListeners.contains(listener))
			return;
		sessionMessageListeners.add(listener);
	}

	@Override
	public void removeSessionMessageListener(SessionMessageListener listener) {
		sessionMessageListeners.remove(listener);
	}

	@Override
	public void addPeerMessageListener(PeerMessageListener listener) {
		if (peerMessageListeners.contains(listener))
			return;
		peerMessageListeners.add(listener);
	}

	@Override
	public void removePeerMessageListener(PeerMessageListener listener) {
		peerMessageListeners.remove(listener);
	}

	public long calculateDelay(long messageTime) {
		return calculateDelay(messageTime, getMinimumDelayToServer(),
				getDelayVariation());
	}

	public static long calculateDelay(long messageTime, int minimumDelay,
			int delayVariation) {
		double random = Math.random();
		long randomDelay = Math.round(delayVariation * random);
		long actualDelay = randomDelay + minimumDelay
				- (System.currentTimeMillis() - messageTime);
		return actualDelay;
	}

	@Override
	public String[] getUserNames() {
		String[] values = {};
		values = clients.values().toArray(values);
		return values;
	}

	public void setClients(Map<MessageReceiver, String> theClients) {
		clients = theClients;
	}

	@Override
	public Map<MessageReceiver, String> getClients() {
		return clients;
	}

	@Override
	public void setSerializedMulticastGroups(SerializedProcessGroups newVal) {
		serializedMulticastGroups = newVal;
	}

	@Override
	public SerializedProcessGroups getSerializedMulticastGroups() {
		return serializedMulticastGroups;
	}

	public void setSentMessageQueuer(
			MessageFilter<SentMessage> theSentMessageQueuer) {
		sentMessageQueuer = theSentMessageQueuer;
		sentMessageQueuer.setMessageProcessor(sentMessageProcessor);
	}

	public void setReceivedMessageQueuer(
			MessageFilter<ReceivedMessage> theReceivedMessageQueuer) {
		receivedMessageQueuer = theReceivedMessageQueuer;
		receivedMessageQueuer.setMessageProcessor(receivedMessageProcessor);

	}

}
