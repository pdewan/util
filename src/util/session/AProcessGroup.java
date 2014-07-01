package util.session;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.models.ABoundedBuffer;
import util.trace.Tracer;
import util.trace.session.ClientJoinInformationUpdated;
import util.trace.session.MessageGivenToFilter;
import util.trace.session.MessageSent;
import util.trace.session.MulticastGroupJoinInformationUpdated;
import util.trace.session.MulticastGroupLeaveInformationUpdated;
import util.trace.session.QueueCreated;
import util.trace.session.SentMessageDelayed;
import util.trace.session.ThreadCreated;
// looks like it does not really implement the inherited SessionMessageReceiver
// interface, has a stub method for it
public class AProcessGroup implements ProcessGroup, ProcessGroupLocal {
	Map<ObjectReceiver, String> clients = new HashMap();
	String applicationName;
	String sessionName;
	AnAbstractCommunicator localCommunicator;
	List<UserDelayRecord> sortedClients;
	MessageProcessor<SentMessage> sentMessageProcessor;

	boolean isServer;
	ABoundedBuffer<SentMessage> outputMessageQueue;
	Runnable messageSenderRunnable;
	ServerMessageFilter sentMessageQueuer;
	ClientCallsMarshaller receivedMessageCreator;
	

	public AProcessGroup(String aSessionName, String anApplicationName,
			AnAbstractCommunicator aLocalCommunicator) {
		sessionName = aSessionName;
		applicationName = anApplicationName;
		localCommunicator = aLocalCommunicator;
		isServer = localCommunicator == null;
		receivedMessageCreator = new AClientCallsMarshaller();
		createMessageSenderRunnable();

	}
	public static final String OUTPUT_MESSAGE_QUEUE = "Output Message Queue";

	void createMessageSenderRunnable() {
		if (isServer) {
			outputMessageQueue = new ABoundedBuffer(OUTPUT_MESSAGE_QUEUE);
			QueueCreated.newCase(CommunicatorSelector.getProcessName(), outputMessageQueue.getName(), this);

			sentMessageProcessor = new ASentMessageQueuer(outputMessageQueue);
			messageSenderRunnable = new AServerMessageSenderRunnable(
					outputMessageQueue, null, this);
			Thread messageSenderThread = new Thread(messageSenderRunnable);
			messageSenderThread.setName("Process Group:" + applicationName);
			ServerMessageFilter messageQueuer = ServerSentMessageFilterSelector
					.getMessageQueuerFactory().getMessageQueuer();
			ThreadCreated.newCase(messageSenderThread.getName(), CommunicatorSelector.getProcessName(), this);
			messageSenderThread.start();
			setSentMessageQueuer(messageQueuer);
		}
	}

	public List<UserDelayRecord> getSortedClients() {
		if (sortedClients == null) {
			localCommunicator.getDelayManager().refreshAndSortClients();
			sortedClients = localCommunicator.getDelayManager()
					.getSortedDelayRecords();

		} else {
			localCommunicator.getDelayManager().refreshClients();

		}
		return sortedClients;
	}

	void delay(ObjectReceiver client, Object message, long messageTime, String aClientName) {
		if (localCommunicator == null)
			return;
		int minimumDelay = localCommunicator.getMinimumDelayToPeer(clients
				.get(client));
		long actualDelay = ASessionManagerClient.calculateDelay(messageTime,
				minimumDelay, localCommunicator.getDelayVariation());
		if (actualDelay <= 0)
			return;
//		SentMessageDelayedNew.newCase(clients.get(client), message, actualDelay, this);
		SentMessageDelayed.newCase(CommunicatorSelector.getProcessName(), message, aClientName,actualDelay, this);

		Tracer.info(this, "Client delaying sending message to absolute time: " + messageTime + " and delay:" +
				  actualDelay);
		try {
			Thread.sleep(actualDelay);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void asyncDelay(ObjectReceiver aClient, ReceivedMessage aReceivedMessage, long messageTime, String aClientName) {
		if (localCommunicator == null)
			return;
		int minimumDelay = localCommunicator.getMinimumDelayToPeer(clients
				.get(aClient));
		long actualDelay = ASessionManagerClient.calculateDelay(messageTime,
				minimumDelay, localCommunicator.getDelayVariation());
		if (actualDelay <= 0) {
			try {
				MessageSent.newCase(CommunicatorSelector.getProcessName(), aReceivedMessage , clients.get(aClient),  this);

				aClient.newMessage(aReceivedMessage);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return;
		}
//		SentMessageDelayedNew.newCase(clients.get(client), message, actualDelay, this);
//		SentMessageDelayed.newCase(CommunicatorSelector.getProcessName(), aReceivedMessage, aClientName,actualDelay, this);

		Tracer.info(this, "Client delaying sending message to absolute time: " + messageTime + " and delay:" +
				  actualDelay);
		localCommunicator.getDelayManager().addMessage(aReceivedMessage, aClient);
//		try {
//			Thread.sleep(actualDelay);
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void toOthers(Object object, String theClientName,
			ObjectReceiver theClient, long timeStamp) throws RemoteException {
		Tracer.info(this, "Process group sending message from:" + theClientName
				+ " object:" + object);
		ReceivedMessage receivedMessage = receivedMessageCreator.newObject(
				clients.get(theClient), theClient, object);

		if (isServer) {
			for (ObjectReceiver client : clients.keySet()) {
				if (!client.equals(theClient)) {
					Tracer.info(this, "Server sending to: " + clients.get(client)
							+ " object:" + object);
					client.newMessage(receivedMessage);
					MessageSent.newCase(CommunicatorSelector.getProcessName(),  receivedMessage, clients.get(client), this);

//					MessageSent.newCase(theClientName, receivedMessage, this);

				}
			}
		} else {
//			List<UserDelayRecord> sortedClients = getSortedClients();
//			for (UserDelayRecord userDelayRecord : sortedClients) {
//				ObjectReceiver client = userDelayRecord.getClient();
//				if (!client.equals(theClient)) {
//					
//					delay(client, object, timeStamp, clients.get(client));
//					Tracer.info(this, "Client sending to: " + clients.get(client)
//							+ " object:" + object);
//					client.newMessage(receivedMessage);
//					MessageSent.newCase(CommunicatorSelector.getProcessName(), receivedMessage , clients.get(client),  this);
//				}
//			}
			for (ObjectReceiver client : clients.keySet()) {
				if (!client.equals(theClient)) {
					
					asyncDelay (client, receivedMessage, timeStamp, clients.get(client));
					Tracer.info(this, "Client sending to: " + clients.get(client)
							+ " object:" + object);
//					client.newMessage(receivedMessage);
//					MessageSent.newCase(CommunicatorSelector.getProcessName(), receivedMessage , clients.get(client),  this);
				}
			}
		}
	}
//	void blockingOthers(ReceivedMessage receivedMessage) {
//		
//	}
	// some day we will sare code here
	@Override
	public void toNonCallers(Object object, String theClientName,
			ObjectReceiver theClient, long timeStamp, String theCallerName) throws RemoteException {
		Tracer.info(this, "Process group sending message from:" + theClientName
				+ " object:" + object);
		ReceivedMessage receivedMessage = receivedMessageCreator.newObject(
				clients.get(theClient), theClient, object);

		if (isServer) {
			for (ObjectReceiver client : clients.keySet()) {
				if (!client.equals(theClient) && !clients.get(theClient).equals(theCallerName)) {
					Tracer.info(this, "Server sending to: " + clients.get(client)
							+ " object:" + object);
					client.newMessage(receivedMessage);
					MessageSent.newCase(CommunicatorSelector.getProcessName(),  receivedMessage, clients.get(client), this);

//					MessageSent.newCase(theClientName, receivedMessage, this);

				}
			}
		} else {
			List<UserDelayRecord> sortedClients = getSortedClients();
			for (UserDelayRecord userDelayRecord : sortedClients) {
				ObjectReceiver client = userDelayRecord.getClient();
				if (!client.equals(theClient ) && !clients.get(theClient).equals(theCallerName)) {
					
					delay(client, object, timeStamp, clients.get(client));
					Tracer.info(this, "Client sending to: " + clients.get(client)
							+ " object:" + object);
					client.newMessage(receivedMessage);
					MessageSent.newCase(CommunicatorSelector.getProcessName(), receivedMessage , clients.get(client),  this);
				}
			}
		}
	}
	// why is this sending newObject rather than newMessage
	// sould really have it create a received message and send it
	@Override
	public void toAll(Object object, String theClientName,
			ObjectReceiver theClient, long timeStamp) throws RemoteException {
		if (isServer) {
			for (ObjectReceiver client : clients.keySet()) {
				client.newObject(clients.get(theClient), theClient, object);
//				MessageSent.newCase(clients.get(client), object, this);
				MessageSent.newCase(CommunicatorSelector.getProcessName(), object , clients.get(client),  this);


			}
		} else {
			List<UserDelayRecord> sortedClients = getSortedClients();
			for (UserDelayRecord userDelayRecord : sortedClients) {
				ObjectReceiver client = userDelayRecord.getClient();
				delay(client, object, timeStamp, userDelayRecord.getName());
				client.newObject(clients.get(theClient), theClient, object);
//				MessageSent.newCase(clients.get(client), object , this);
				MessageSent.newCase(CommunicatorSelector.getProcessName(), object , clients.get(client),  this);


			}
		}
	}

	@Override
	public void toUser(Object userName, Object object, String clientName,
			ObjectReceiver theClient, long timeStamp) throws RemoteException {
		for (ObjectReceiver client : clients.keySet()) {
			if (clients.get(client).equals(userName)) {
				client.newObject(clients.get(theClient), theClient, object); // rmi call in the client object
				MessageSent.newCase(CommunicatorSelector.getProcessName(), object , clients.get(client),  this);

				return;
			}
		}
	}

	@Override
	public void toUsers(String[] userNames, Object object, String clientName,
			ObjectReceiver client, long timeStamp) throws RemoteException {
		System.out.println("to users not implemented as yet");

	}

	@Override
	public void userJoined(ProcessGroupLocal processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<ObjectReceiver, String> theClients, String clientName,
			ObjectReceiver client, String theApplicationName,
			boolean newSession, boolean newApplication)
	/* throws RemoteException */{
		if (theApplicationName != null && 
				theApplicationName.equals(applicationName)) {
			MulticastGroupJoinInformationUpdated.newCase(
					CommunicatorSelector.getProcessName(),
					clientName, theApplicationName, sessionName, this);
			clients.put(client, clientName);
			sentMessageQueuer.userJoined(sessionName, applicationName, clientName);
		}

	}

	@Override
	public void userLeft(String theClientName, ObjectReceiver theClient,
			String theApplicationName)
	/* throws RemoteException */{
		if (theApplicationName.equals(applicationName)) {
			MulticastGroupLeaveInformationUpdated.newCase(
					CommunicatorSelector.getProcessName(),
					theClientName, theApplicationName, sessionName, this);
			clients.remove(theClient);
		}

	}

	public Map<ObjectReceiver, String> getClients() throws RemoteException {
		return clients;
	}

	public void setClients(Map<ObjectReceiver, String> theClients) {
		clients = theClients;
	}
	//rmi call
	@Override
	public void newMessage(SentMessage theMessage) throws RemoteException {
		MessageGivenToFilter.newCase(CommunicatorSelector.getProcessName(), theMessage, theMessage.getSendingUser(), this);
		sentMessageQueuer.filterMessage(theMessage);
		return;
	}

	public void setSentMessageQueuer(ServerMessageFilter theSentMessageQueuer) {
		sentMessageQueuer = theSentMessageQueuer;
		sentMessageQueuer.setMessageProcessor(sentMessageProcessor);
	}
	// does nothing with this newMessage
	@Override
	public void newMessage(ReceivedMessage theReceivedMessage)
			throws RemoteException {

	}

}
