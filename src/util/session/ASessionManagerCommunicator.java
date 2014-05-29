package util.session;

import java.io.Serializable;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public abstract class ASessionManagerCommunicator extends ASessionListenable
		implements CommunicatorInternal, Communicator, /*
														 * MessageReceiver*,
														 * DelayedMessageReceiver
														 * *,
														 */Serializable {
	MessageProcessor<SentMessage> sentMessageProcessor;
	SentMessageCreator sentMessageCreator;
	MessageFilter<SentMessage> sentMessageQueuer;
	MessageReceiver messageReceiver;
	DelayedMessageReceiver delayedMessageReceiver;
	MessageReceiver exportedMessageReceiver;
	String clientName;
	String sessionName;
	String applicationName;
	ProcessGroup processGroup;
	SessionManager sessionManager;
	BoundedBuffer<SentMessage> outputMessageQueue;
	MessageSenderRunnable messageSenderRunnable;
	Thread messageSenderThread;
	Map<MessageReceiver, String> clients = new HashMap();
	SerializedProcessGroups serializedMulticastGroups;
	JoinLock joinLock = new AJoinLock();
	DelayManager delayManager;
	boolean isRelayedCommunication;

	public ASessionManagerCommunicator(String theServerHost,
			String theSessionName, String theApplicationName,
			String theClientName, boolean theIsRelayedCommunication) {
		isRelayedCommunication = theIsRelayedCommunication;
		create(theServerHost, theSessionName, theApplicationName, theClientName);
	}

	public ASessionManagerCommunicator(String[] args,
			boolean theIsRelayedCommunication) {
		if (args.length < 4) {
			Tracer.fatalError("Please supply server host name, session Name, and clientName as main argument");
		}
		isRelayedCommunication = theIsRelayedCommunication;

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

	MessageReceiver generateRemoteProxy(MessageReceiver theLocalMessageReceiver) {
		try {
			return (MessageReceiver) UnicastRemoteObject.exportObject(
					(MessageReceiver) theLocalMessageReceiver, 0);
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
		outputMessageQueue = new BoundedBuffer();
		sentMessageProcessor = new ASentMessageProcessor(outputMessageQueue);
		messageSenderRunnable = new AMessageSenderRunnable(outputMessageQueue,
				delayManager, sessionManager);
		messageSenderThread = new Thread(messageSenderRunnable);
		messageSenderThread.setName("Message Sender");
		messageSenderThread.start();
	}

	@Override
	public void create(String serverHost, String theSessionName,
			String theApplicationName, String theClientName) {
		try {
			clientName = theClientName;
			sessionManager = getSessionManagerHandle(serverHost);
			sessionName = theSessionName;
			applicationName = theApplicationName;
			delayManager = new ADelayManager(this);
			delayedMessageReceiver = new ADelayedMessageReceiver(serverHost,
					theSessionName, theApplicationName, theClientName, this);
			createOutputBufferAndThread();
			messageReceiver = new AMessageReceiver(serverHost, theSessionName,
					theApplicationName, theClientName, joinLock,
					delayedMessageReceiver, messageSenderRunnable,
					delayManager, this);
			exportedMessageReceiver = generateRemoteProxy(messageReceiver);
			sentMessageCreator = new ASentMessageCreator(clientName,
					sessionName, applicationName, exportedMessageReceiver);
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.fatalError("Could not lookup multicast server on host:"
					+ serverHost);
		}
	}

	@Override
	public synchronized void join() {
		asyncJoin();
		joinLock.waitFotJoin();
	}

	public synchronized void asyncJoin() {
//		Object[] args = { sessionName, applicationName, clientName,
//				exportedMessageReceiver };
		SentMessage message = sentMessageCreator.asyncJoin();
		getSentMessageQueuer().put(message);
	}

	@Override
	public synchronized void leave() {
		SentMessage message = sentMessageCreator.leave();
		getSentMessageQueuer().put(message);



	}

	public int getMinimumDelayToServer() {
		return delayManager.getMinimumDelayToServer();
	}

	public void setMinimumDelayToServer(int theDelay) {
		delayManager.setMinimumDelayToServer(theDelay);
	}

	public int getDelayVariation() {
		return delayManager.getDelayVariation();
	}

	public void setDelayVariation(int theDelay) {
		delayManager.setDelayVariation(theDelay);
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
		delayedMessageReceiver.addReceivedMessageListener(listener);
	}

	@Override
	public void removeReceivedMessageListener(ReceivedMessageListener listener) {
		delayedMessageReceiver.removeSessionMessageListener(listener);
		delayedMessageReceiver.removePeerMessageListener(listener);
	}

	@Override
	public void addSessionMessageListener(SessionMessageListener listener) {
		delayedMessageReceiver.addSessionMessageListener(listener);
	}

	@Override
	public void removeSessionMessageListener(SessionMessageListener listener) {
		delayedMessageReceiver.removeSessionMessageListener(listener);
	}

	@Override
	public void addPeerMessageListener(PeerMessageListener listener) {
		delayedMessageReceiver.addPeerMessageListener(listener);
	}

	@Override
	public void removePeerMessageListener(PeerMessageListener listener) {
		delayedMessageReceiver.removePeerMessageListener(listener);
	}

	@Override
	public String[] getOtherUserNames() {
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

	public void setSentMessageQueuer(
			MessageFilter<SentMessage> theSentMessageQueuer) {
		sentMessageQueuer = theSentMessageQueuer;
		sentMessageQueuer.setMessageProcessor(sentMessageProcessor);
	}

	public DelayManager getDelayManager() {
		return delayManager;
	}

	public void setDelayManager(DelayManager theDelayManager) {
		delayManager = theDelayManager;
	}

	public MessageReceiver getMessageReceiver() {
		return messageReceiver;
	}

}
