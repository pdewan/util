package util.session;

import java.awt.Frame;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;

public class AProcessGroup implements ProcessGroup, ProcessGroupLocal {
	Map<MessageReceiver, String> clients = new HashMap();
	String applicationName;
	AnAbstractCommunicator localCommunicator;
	List<UserDelayRecord> sortedClients;
	MessageProcessor<SentMessage> sentMessageProcessor;

	boolean isServer;
	BoundedBuffer<SentMessage> outputMessageQueue;
	Runnable messageSenderRunnable;
	ServerMessageFilter sentMessageQueuer;
	ReceivedMessageCreator receivedMessageCreator;

	public AProcessGroup(String theApplicationName,
			AnAbstractCommunicator theLocalCommunicator) {
		applicationName = theApplicationName;
		localCommunicator = theLocalCommunicator;
		isServer = localCommunicator == null;
		receivedMessageCreator = new AReceivedMessageCreator();
		createMessageSenderRunnable();

	}

	void createMessageSenderRunnable() {
		if (isServer) {
			outputMessageQueue = new BoundedBuffer();
			sentMessageProcessor = new ASentMessageProcessor(outputMessageQueue);
			messageSenderRunnable = new AServerMessageSenderRunnable(
					outputMessageQueue, null, this);
			Thread messageSenderThread = new Thread(messageSenderRunnable);
			messageSenderThread.setName("Process Group:" + applicationName);
			ServerMessageFilter messageQueuer = AServerSentMessageQueuerSelector
					.getMessageQueuerFactory().getMessageQueuer();

			messageSenderThread.start();
			setSentMessageQueuer(messageQueuer);
		}
	}

	public List<UserDelayRecord> getSortedClients() {
		if (sortedClients == null) {
			localCommunicator.getDelayManager().refreshAndSortClients();
			sortedClients = localCommunicator.getDelayManager()
					.getSortedClients();

		} else {
			localCommunicator.getDelayManager().refreshClients();

		}
		return sortedClients;
	}

	void delay(MessageReceiver client, long messageTime) {
		if (localCommunicator == null)
			return;
		int minimumDelay = localCommunicator.getMinimumDelayToPeer(clients
				.get(client));
		long actualDelay = ASessionManagerClient.calculateDelay(messageTime,
				minimumDelay, localCommunicator.getDelayVariation());
		if (actualDelay <= 0)
			return;
		Tracer.info(this, "Client delaying sending message to absolute time: " + messageTime + " and delay:" +
				  actualDelay);
		try {
			Thread.sleep(actualDelay);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toOthers(Object object, String theClientName,
			MessageReceiver theClient, long timeStamp) throws RemoteException {
		Tracer.info(this, "Process group sending message from:" + theClientName
				+ " object:" + object);
		ReceivedMessage receivedMessage = receivedMessageCreator.newObject(
				clients.get(theClient), theClient, object);

		if (isServer) {
			for (MessageReceiver client : clients.keySet()) {
				if (!client.equals(theClient)) {
					Tracer.info(this, "Server sending to: " + clients.get(client)
							+ " object:" + object);
					client.newMessage(receivedMessage);
				}
			}
		} else {
			List<UserDelayRecord> sortedClients = getSortedClients();
			for (UserDelayRecord userDelayRecord : sortedClients) {
				MessageReceiver client = userDelayRecord.getClient();
				if (!client.equals(theClient)) {
					
					delay(client, timeStamp);
					Tracer.info(this, "Client sending to: " + clients.get(client)
							+ " object:" + object);
					client.newMessage(receivedMessage);
				}
			}
		}
	}

	@Override
	public void toAll(Object object, String theClientName,
			MessageReceiver theClient, long timeStamp) throws RemoteException {
		if (isServer) {
			for (MessageReceiver client : clients.keySet()) {
				client.newObject(clients.get(theClient), theClient, object);

			}
		} else {
			List<UserDelayRecord> sortedClients = getSortedClients();
			for (UserDelayRecord userDelayRecord : sortedClients) {
				MessageReceiver client = userDelayRecord.getClient();
				delay(client, timeStamp);
				client.newObject(clients.get(theClient), theClient, object);
			}
		}
	}

	@Override
	public void toUser(Object userName, Object object, String clientName,
			MessageReceiver theClient, long timeStamp) throws RemoteException {
		for (MessageReceiver client : clients.keySet()) {
			if (clients.get(client).equals(userName)) {
				client.newObject(clients.get(theClient), theClient, object); // rmi call in the client object
				return;
			}
		}
	}

	@Override
	public void toUsers(String[] userNames, Object object, String clientName,
			MessageReceiver client, long timeStamp) throws RemoteException {
		System.out.println("to users not implemented as yet");

	}

	@Override
	public void userJoined(ProcessGroupLocal processGroup,
			SerializedProcessGroups serializedProcessGroups,
			Map<MessageReceiver, String> theClients, String clientName,
			MessageReceiver client, String theApplicationName,
			boolean newSession, boolean newApplication)
	/* throws RemoteException */{
		if (theApplicationName.equals(applicationName)) {
			clients.put(client, clientName);
			sentMessageQueuer.userJoined(clientName);
		}

	}

	@Override
	public void userLeft(String theClientName, MessageReceiver theClient,
			String theApplicationName)
	/* throws RemoteException */{
		if (theApplicationName.equals(applicationName))
			clients.remove(theClient);

	}

	public Map<MessageReceiver, String> getClients() throws RemoteException {
		return clients;
	}

	public void setClients(Map<MessageReceiver, String> theClients) {
		clients = theClients;
	}
	//rmi call
	@Override
	public void newMessage(SentMessage theMessage) throws RemoteException {
		sentMessageQueuer.put(theMessage);
		return;
	}

	public void setSentMessageQueuer(ServerMessageFilter theSentMessageQueuer) {
		sentMessageQueuer = theSentMessageQueuer;
		sentMessageQueuer.setMessageProcessor(sentMessageProcessor);
	}

	@Override
	public void newMessage(ReceivedMessage theReceivedMessage)
			throws RemoteException {

	}

}
