package util.session;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import util.models.ABoundedBuffer;
import util.trace.Tracer;
import util.trace.session.DataReceiveMarshalled;
import util.trace.session.MessagePutInQueue;
import util.trace.session.MessageReceived;
import util.trace.session.QueueCreated;
import util.trace.session.ThreadCreated;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public class AMessageReceiver implements ObjectReceiver/*
														 * ,
														 * DelayedMessageReceiver
														 */, Serializable {
	MessageDispatcher delayedMessageReceiver;
	MessageFilter<ReceivedMessage> receivedMessageQueuer;
	String clientName;
	String sessionName;
	String applicationName;
	ProcessGroup processGroup;
	MessageSenderRunnable messageSenderRunnable;
	Map<ObjectReceiver, String> clients = new HashMap();
	SerializedProcessGroups serializedMulticastGroups;
	ABoundedBuffer<ReceivedMessage> inputMessageQueue;
	MessageReceiverRunnable messageReceiverRunnable;
	Thread messageReceiverThread;
	ClientCallsMarshaller receivedMessageCreator = new AClientCallsMarshaller();
	JoinLock joinLock;
	DelayManager delayManager;
	CommunicatorInternal communicator;

	public AMessageReceiver(String theServerHost, String theSessionName,
			String theApplicationName, String theClientName,
			JoinLock theJoinLock,
			MessageDispatcher theDelayedMessageReceiver,
			MessageSenderRunnable theMessageSenderRunnable,
			DelayManager theDelayManager,
			CommunicatorInternal theCommunicatorInternal) {
		create(theServerHost, theSessionName, theApplicationName,
				theClientName, theJoinLock, theDelayedMessageReceiver,
				theMessageSenderRunnable, theDelayManager,
				theCommunicatorInternal);
	}

	void create(String serverHost, String theSessionName,
			String theApplicationName, String theClientName,
			JoinLock theJoinLock,
			MessageDispatcher theDelayedMessageReceiver,
			MessageSenderRunnable theMessageSenderRunnable,
			DelayManager theDelayManager,
			CommunicatorInternal theCommunicatorInternal) {
		try {
			clientName = theClientName;
			sessionName = theSessionName;
			applicationName = theApplicationName;
			joinLock = theJoinLock;
			delayManager = theDelayManager;
			delayedMessageReceiver = theDelayedMessageReceiver;
			messageSenderRunnable = theMessageSenderRunnable;
			communicator = theCommunicatorInternal;
			createInputBufferAndThread();
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.fatalError("Could not lookup multicast server on host:"
					+ serverHost);
		}
	}
	
	public static final String INPUT_MESSAGE_QUEUE = "Input Message Queue";

	void createInputBufferAndThread() {
		inputMessageQueue = new ABoundedBuffer(INPUT_MESSAGE_QUEUE);
		QueueCreated.newCase(CommunicatorSelector.getProcessName(), inputMessageQueue.getName(), this);


		messageReceiverRunnable = new AMessageReceiverRunnable(
				inputMessageQueue, delayManager,
				delayedMessageReceiver.getReceivedMessageQueuer());
		messageReceiverRunnable.setIsRelayedCommunication(communicator
				.isRelayedCommunication());
		messageReceiverThread = new Thread(messageReceiverRunnable);
		messageReceiverThread.setName("Message Receiver");
		ThreadCreated.newCase(messageReceiverThread.getName(), CommunicatorSelector.getProcessName(), this);
		messageReceiverThread.start();
	}

	@Override
	public synchronized void newObject(String clientName,
			ObjectReceiver client, Object value) { // rmi call
		Tracer.info(this, "Client received newObject from:" + clientName);
		ReceivedMessage receivedMesage = receivedMessageCreator.newObject(
				clientName, client, value); // just making it into a queueble entity - nice thing about gipc is that we get the queuable entity, this now goes to another thread
		DataReceiveMarshalled.newCase(
				CommunicatorSelector.getProcessName(), receivedMesage, clientName, this);
		
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), receivedMesage, receivedMesage.getClientName(), inputMessageQueue.getName(), this);
		
		inputMessageQueue.put(receivedMesage);
	}

	@Override
	public void newMessage(ReceivedMessage aReceivedMessage)
			throws RemoteException {
		MessageReceived.newCase(
				CommunicatorSelector.getProcessName(), 
				aReceivedMessage, aReceivedMessage.getClientName(), this);
		Tracer.info(this, "Client received message:" + aReceivedMessage);

		if (aReceivedMessage.getReceivedMessageType() == ReceivedMessageType.ClientJoined) { // unblocks the joiner
			processUndelayedUserJoined(aReceivedMessage.getProcessGroup(),
					aReceivedMessage.getSerializedProcessGroups(),
					aReceivedMessage.getClients());
		}
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), aReceivedMessage, aReceivedMessage.getClientName(), inputMessageQueue.getName(), this);

		inputMessageQueue.put(aReceivedMessage);
	}

	void processUndelayedUserJoined(ProcessGroup processGroup, // this is the relayer group
			SerializedProcessGroups serializedProcessGroups,
			Map<ObjectReceiver, String> theClients) {

		delayedMessageReceiver.setClients(theClients);
		setSerializedMulticastGroups(serializedProcessGroups);
		messageSenderRunnable.setProcessGroup(processGroup); // so message sender runnable is bound to relayer group or perhaps it checks for local group first
		joinLock.notifyJoined();

	}

	public synchronized void userJoined(ProcessGroup processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<ObjectReceiver, String> theClients, String clientName,
			ObjectReceiver client, String theApplicationName,
			boolean newSession, boolean newApplication) throws RemoteException {
		processUndelayedUserJoined(processGroup, serializedProcessGroups,
				theClients);
		ReceivedMessage aReceivedMessage = receivedMessageCreator.userJoined(
				processGroup, serializedProcessGroups, theClients, clientName,
				client, theApplicationName, newSession, newApplication);
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), aReceivedMessage, aReceivedMessage.getClientName(), inputMessageQueue.getName(), this);

		inputMessageQueue.put(aReceivedMessage);

	}

	public synchronized void userLeft(String theClientName,
			ObjectReceiver theClient, String theApplicationName)
			throws RemoteException {
		ReceivedMessage receivedMessage = receivedMessageCreator.userLeft(
				theClientName, theClient, theApplicationName);
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), receivedMessage, receivedMessage.getClientName(), inputMessageQueue.getName(), this);

		inputMessageQueue.put(receivedMessage);
	}

	public void setClients(Map<ObjectReceiver, String> theClients)
			throws RemoteException {
		clients = theClients;
	}

	public void setSerializedMulticastGroups(SerializedProcessGroups newVal) {
		serializedMulticastGroups = newVal;
	}

	public SerializedProcessGroups getSerializedMulticastGroups() {
		return serializedMulticastGroups;
	}

	public MessageReceiverRunnable getMessageReceiverRunnable() {
		return messageReceiverRunnable;
	}

}
