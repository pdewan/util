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
import util.trace.session.ClientLeaveFinished;
import util.trace.session.ClientLeaveInitiated;
import util.trace.session.MessagePutInQueue;
import util.trace.session.QueueCreated;
import util.trace.session.ThreadCreated;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public abstract class ASessionManagerClient extends ASessionListenable
		implements DelayManager, Communicator, ObjectReceiver,
		MessageDispatcher, Serializable {
	MessageProcessor<SentMessage> sentMessageProcessor;
	MessageProcessor<ReceivedMessage> receivedMessageProcessor;
	MessageFilter<SentMessage> sentMessageFilter;
	MessageFilter<ReceivedMessage> receivedMessageQueuer;
	ObjectReceiver exportedMessageReceiver;
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
	Map<ObjectReceiver, String> clients = new HashMap();
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

	ObjectReceiver generateRemoteProxyToSelf() {
		try {
			return (ObjectReceiver) UnicastRemoteObject.exportObject(
					(ObjectReceiver) this, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public MessageFilter<SentMessage> getSentMessageFilter() {
		if (sentMessageFilter == null) {
			setSentMessageFilter(SentMessageFilterSelector
					.getMessageFilterCreator().getMessageFilter());
		}
		return sentMessageFilter;
	}

	void createOutputBufferAndThread() {
		outputMessageQueue = new ABoundedBuffer(AProcessGroup.OUTPUT_MESSAGE_QUEUE);
		QueueCreated.newCase(CommunicatorSelector.getProcessName(), outputMessageQueue.getName(), this);

		sentMessageProcessor = new ASentMessageQueuer(outputMessageQueue);
		messageSenderRunnable = new AMessageSenderRunnable(outputMessageQueue,
				this, sessionManager);
		messageSenderThread = new Thread(messageSenderRunnable);
		messageSenderThread.setName("Message Sender" + "(" + sessionName + "," + applicationName + ")");
		ThreadCreated.newCase(messageSenderThread.getName(), CommunicatorSelector.getProcessName(), this);

		messageSenderThread.start();
	}
	@Override
	public MessageFilter<ReceivedMessage> getReceivedMessageFilter() {
		if (receivedMessageQueuer == null) {
			setReceivedMessageQueuer(ReceivedMessageFilterSelector
					.getMessageFilterCreator().getMessageFilter());
		}
		return receivedMessageQueuer;
	}

	void createInputBufferAndThread() {
		inputMessageQueue = new ABoundedBuffer(AMessageReceiver.INPUT_MESSAGE_QUEUE);
		QueueCreated.newCase(CommunicatorSelector.getProcessName(), inputMessageQueue.getName(), this);

		receivedMessageProcessor = new AReceivedMessageUmarshaller(this, clientName);

		messageReceiverRunnable = new AMessageReceiverRunnable(
				inputMessageQueue, this, getReceivedMessageFilter());
		messageReceiverThread = new Thread(messageReceiverRunnable);
		ThreadCreated.newCase(messageReceiverThread.getName(), CommunicatorSelector.getProcessName(), this);

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
		ClientJoinInitiated.newCase(CommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);

		asyncJoin();
		joinLock.waitFotJoin();
		ClientJoinFinished.newCase(CommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);

	}

	public void asyncJoin() {

		Object[] args = { sessionName, applicationName, clientName,
				exportedMessageReceiver };
		SentMessage sentMessage = new ASentMessage(sessionName, applicationName, clientName,
				exportedMessageReceiver, SentMessageType.Join, args);
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), sentMessage, null, outputMessageQueue.getName(), this);
		try {
			outputMessageQueue.put(sentMessage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		outputMessageQueue
//				.put(new ASentMessage(sessionName, applicationName, clientName,
//						exportedMessageReceiver, SentMessageType.Join, args));
		inSession = true;
	}

	@Override
	public void leave() {
		ClientLeaveInitiated.newCase(CommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);
		asyncLeave();		
		ClientLeaveFinished.newCase(CommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);


	}
	public void asyncLeave() {

		Object[] args = { sessionName, applicationName, clientName,
				exportedMessageReceiver };
		SentMessage sentMessage = new ASentMessage(sessionName, applicationName, clientName,
				exportedMessageReceiver, SentMessageType.Leave, args);
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), sentMessage, null, outputMessageQueue.getName(), this);
		try {
			outputMessageQueue.put(sentMessage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		outputMessageQueue
//				.put(new ASentMessage(sessionName, applicationName, clientName,
//						exportedMessageReceiver, SentMessageType.Join, args));
		inSession = false;
	}

	@Override
	public void newObject(String clientName, ObjectReceiver client,
			Object value) {
		Tracer.info("Client received newObject from:" + clientName);
		AReceivedMessage receivedMesage = new AReceivedMessage(
				ReceivedMessageType.NewObject, clientName, client, null, value,
				false, false, null, null, null);
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), receivedMesage, receivedMesage.getClientName(),  inputMessageQueue.getName(), this);

		try {
			inputMessageQueue.put(receivedMesage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delayedNewObject(String clientName, Object value) {
		for (PeerMessageListener listener : peerMessageListeners) {
			listener.objectReceived(value, clientName);
		}
	}

	public void delayedUserJoined(Map<ObjectReceiver, String> theClients,
			String theClientName, ObjectReceiver theClient,
			String theApplicationName, boolean newSession,
			boolean newApplication) {
		for (SessionMessageListener listener : sessionMessageListeners) {
			listener.clientJoined(theClientName, theApplicationName,
					getSessionName(), newSession, newApplication,
					clients.values());
		}
		if (applicationName.equals(theApplicationName))
			clients.put(theClient, theClientName);

	}

	public void delayedUserLeft(String theClientName,
			ObjectReceiver theClient, String theApplicationName) {
		for (SessionMessageListener listener : sessionMessageListeners) {
			listener.clientLeft(theClientName, theApplicationName);
		}
		if (applicationName.equals(theApplicationName))
			clients.remove(theClientName);
	}

	public void userJoined(ProcessGroup processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<ObjectReceiver, String> theClients, String clientName,
			ObjectReceiver client, String theApplicationName,
			boolean newSession, boolean newApplication) throws RemoteException {
		setClients(theClients);
		setSerializedMulticastGroups(serializedProcessGroups);
		messageSenderRunnable.setProcessGroup(processGroup);
		joinLock.notifyJoined();
		AReceivedMessage receivedMesage = new AReceivedMessage(
				ReceivedMessageType.ClientJoined, clientName, client,
				theApplicationName, null, newSession, newApplication,
				theClients, serializedProcessGroups, processGroup);
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), receivedMesage, receivedMesage.getClientName(), inputMessageQueue.getName(), this);

		try {
			inputMessageQueue.put(receivedMesage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void userLeft(String theClientName, ObjectReceiver theClient,
			String theApplicationName) throws RemoteException {
		AReceivedMessage receivedMesage = new AReceivedMessage(
				ReceivedMessageType.ClientLeft, theClientName, theClient,
				theApplicationName, null, false, false, null, null, null);
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), receivedMesage, receivedMesage.getClientName(), inputMessageQueue.getName(), this);

		try {
			inputMessageQueue.put(receivedMesage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public String getClientName() {
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
// why is this duplicated?
	public static long calculateDelay(long messageTime, int minimumDelay,
			int delayVariation) {
//		double random = Math.random();
//		long randomDelay = Math.round(delayVariation * random);
//		long actualDelay = randomDelay + minimumDelay
//				- (System.currentTimeMillis() - messageTime);
//		return actualDelay;
		return ADelayManager.calculateDelay(messageTime, minimumDelay, delayVariation);
	}

	@Override
	public String[] getUserNames() {
		String[] values = {};
		values = clients.values().toArray(values);
		return values;
	}

	public void setClients(Map<ObjectReceiver, String> theClients) {
		clients = theClients;
	}

	@Override
	public Map<ObjectReceiver, String> getClients() {
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
	@Override
	public void setSentMessageFilter(
			MessageFilter<SentMessage> theSentMessageQueuer) {
		sentMessageFilter = theSentMessageQueuer;
		sentMessageFilter.setMessageProcessor(sentMessageProcessor);
	}

	public void setReceivedMessageQueuer(
			MessageFilter<ReceivedMessage> theReceivedMessageQueuer) {
		receivedMessageQueuer = theReceivedMessageQueuer;
		receivedMessageQueuer.setMessageProcessor(receivedMessageProcessor);

	}

}
