package util.session;

import java.io.Serializable;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import util.misc.Common;
import util.models.ABoundedBuffer;
import util.trace.Tracer;
import util.trace.session.ClientJoinFinished;
import util.trace.session.ClientJoinInitiated;
import util.trace.session.JoinRequest;
import util.trace.session.JoinRequestMarshalled;
import util.trace.session.LeaveRequest;
import util.trace.session.LeaveRequestMarshalled;
import util.trace.session.QueueCreated;
import util.trace.session.ThreadCreated;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public abstract class ASessionManagerCommunicator extends ASessionListenable
		implements CommunicatorInternal, Communicator, /*
														 * MessageReceiver*,
														 * DelayedMessageReceiver
														 * *,
														 */Serializable {
	MessageProcessor<SentMessage> sentMessageProcessor;
	AServerCallsMarshaller sentMessageCreator;
	MessageFilter<SentMessage> sentMessageQueuer;
	ObjectReceiver messageReceiver;
	MessageDispatcher delayedMessageReceiver;
	ObjectReceiver exportedMessageReceiver;
	String clientName;
	String sessionName;
	String applicationName;
	ProcessGroup processGroup;
	SessionManager sessionManager;
	ABoundedBuffer<SentMessage> outputMessageQueue;
	MessageSenderRunnable messageSenderRunnable;
	Thread messageSenderThread;
	Map<ObjectReceiver, String>  messageReceiverToClientName = new HashMap();
	SerializedProcessGroups serializedMulticastGroups;
	JoinLock joinLock = new AJoinLock();
	DelayManager delayManager;
	boolean relayedCommunication;
	boolean orderedDelivery;
	MessageReceiverRunnable messageReceiverRunnable;

	

	public ASessionManagerCommunicator(String theServerHost,
			String theSessionName, String theApplicationName,
			String theClientName, boolean theIsRelayedCommunication) {
		relayedCommunication = theIsRelayedCommunication;
		create(theServerHost, theSessionName, theApplicationName, theClientName);
	}

	public ASessionManagerCommunicator(String[] args,
			boolean theIsRelayedCommunication) {
		if (args.length < 4) {
			Tracer.fatalError("Please supply server host name, session Name, and clientName as main argument");
		}
		relayedCommunication = theIsRelayedCommunication;

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

	ObjectReceiver generateRemoteProxy(ObjectReceiver theLocalMessageReceiver) {
		try {
			return (ObjectReceiver) UnicastRemoteObject.exportObject(
					(ObjectReceiver) theLocalMessageReceiver, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	MessageFilter<SentMessage> getSentMessageFilter() {
		if (sentMessageQueuer == null) {
			setSentMessageQueuer(SentMessageFilterSelector
					.getMessageFilterCreator().getMessageFilter());
		}
		return sentMessageQueuer;
	}

	void createOutputBufferAndThread() {
		outputMessageQueue = new ABoundedBuffer(AProcessGroup.OUTPUT_MESSAGE_QUEUE);
		QueueCreated.newCase(CommunicatorSelector.getProcessName(), outputMessageQueue.getName(), this);

		sentMessageProcessor = new ASentMessageQueuer(outputMessageQueue);
		messageSenderRunnable = new AMessageSenderRunnable(outputMessageQueue,
				delayManager, sessionManager);
		messageSenderThread = new Thread(messageSenderRunnable);
		messageSenderThread.setName("Message Sender");
		ThreadCreated.newCase(messageSenderThread.getName(), CommunicatorSelector.getProcessName(), this);

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
			delayedMessageReceiver = new AnUmarshalledReceivedMessageDispatcherAndSessionStateManager(serverHost,
					theSessionName, theApplicationName, theClientName, this);
			createOutputBufferAndThread();
			messageReceiver = new AMessageReceiver(serverHost, theSessionName,
					theApplicationName, theClientName, joinLock,
					delayedMessageReceiver, messageSenderRunnable,
					delayManager, this);
			exportedMessageReceiver = generateRemoteProxy(messageReceiver);
			sentMessageCreator = new ASentMessageMarshaller(clientName,
					sessionName, applicationName, exportedMessageReceiver);
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.fatalError("Could not lookup multicast server on host:"
					+ serverHost);
		}
	}

	@Override
	/*
	 *  do not want to block process, as RMI does it sends the message(non-Javadoc)
	 *  and then ublocks the waiter when responde is heard
	 * @see util.session.Communicator#join()
	 */
	
	public synchronized void join() {
//		ClientJoinInitiated.newCase(CommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);

		asyncJoin();
		joinLock.waitFotJoin();
		ClientJoinFinished.newCase(CommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);

	}
	/*
	 * instead of forking a process it sends data  to a queue served by one process
	 * @see util.session.Communicator#asyncJoin()
	 */
	public synchronized void asyncJoin() {
		ClientJoinInitiated.newCase(CommunicatorSelector.getProcessName(), clientName, applicationName, sessionName, this);

//		Object[] args = { sessionName, applicationName, clientName,
//				exportedMessageReceiver };
//		JoinRequest.newCase(CommunicatorSelector.getProcessName(), null, SessionManager.SESSION_MANAGER_NAME, this);
		SentMessage message = sentMessageCreator.asyncJoin();
		JoinRequestMarshalled.newCase(CommunicatorSelector.getProcessName(),
				message, clientName, this);
		getSentMessageFilter().filterMessage(message);
	}

	@Override
	public synchronized void leave() {
		LeaveRequest.newCase(CommunicatorSelector.getProcessName(), null, SessionManager.SESSION_MANAGER_NAME, this);

		SentMessage message = sentMessageCreator.leave();
		LeaveRequestMarshalled.newCase(CommunicatorSelector.getProcessName(),
				message, clientName, this);
		getSentMessageFilter().filterMessage(message);



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
	public String getClientName() {
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
	// seems to return all user names
	public String[] getUserNames() {
		String[] values = {};
		values = messageReceiverToClientName.values().toArray(values);
		return values;
	}

	public void setClients(Map<ObjectReceiver, String> theClients) {
		messageReceiverToClientName = theClients;
	}

	@Override
	public Map<ObjectReceiver, String> getClients() {
		return messageReceiverToClientName;
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

	public ObjectReceiver getMessageReceiver() {
		return messageReceiver;
	}
	public MessageReceiverRunnable getMessageReceiverRunnable() {
		return messageReceiverRunnable;
	}

	public void setMessageReceiverRunnable(
			MessageReceiverRunnable messageReceiverRunnable) {
		this.messageReceiverRunnable = messageReceiverRunnable;
	}
	public boolean isOrderedDelivery() {
		return orderedDelivery;
	}
	public void setOrderedDelivery(boolean newVal) {
		orderedDelivery = newVal;
	}

}
