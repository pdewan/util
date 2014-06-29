package util.session;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import util.misc.Common;
import util.models.ABoundedBuffer;
import util.trace.Tracer;
import util.trace.session.JoinRequestMarshalled;
import util.trace.session.MessageGivenToFilter;
import util.trace.session.QueueCreated;
import util.trace.session.SessionCreated;
import util.trace.session.ThreadCreated;

public class ASessionManager implements SessionManager, SessionManagerLocal {
	Map<String, Session> sessions = new HashMap();
	Runnable messageSenderRunnable;
	ABoundedBuffer<SentMessage> outputMessageQueue; // this is an input queue, output from client pt of view
	ServerMessageFilter messageQueuer;
	MessageProcessor<SentMessage> sentMessageProcessor;

	public ASessionManager() {
	}
	void createMessageSenderRunnable() {
		outputMessageQueue = new ABoundedBuffer(AMessageReceiver.INPUT_MESSAGE_QUEUE);
		QueueCreated.newCase(CommunicatorSelector.getProcessName(), outputMessageQueue.getName(), this);

		messageSenderRunnable = new AServerMessageSenderRunnable(
				outputMessageQueue, this, null);
		Thread messageSenderThread = new Thread(messageSenderRunnable);
//		messageSenderThread.setName("Message Sender");
		sentMessageProcessor = new ASentMessageQueuer(outputMessageQueue);

		messageQueuer = ServerSentMessageFilterSelector
				.getMessageQueuerFactory().getMessageQueuer();
		messageQueuer.setMessageProcessor(sentMessageProcessor);
		messageSenderThread.setName("Session Manager Message Receiver");
		ThreadCreated.newCase(CommunicatorSelector.getProcessName(), messageSenderThread.getName(), this);
		messageSenderThread.start();
	}

	public void join(String theSessionName, String theApplicationName,
			String clientName, ObjectReceiver client) throws RemoteException {
		AServerCallsMarshaller messageCreator = new ASentMessageMarshaller(clientName,
				theSessionName, theApplicationName, client);
		SentMessage sentMessage = messageCreator.asyncJoin();
		JoinRequestMarshalled.newCase(CommunicatorSelector.getProcessName(),
				sentMessage, clientName, this);
		messageQueuer.filterMessage(sentMessage);
		return;
	}

	public void doJoin(String theSessionName, String theApplicationName,
			String clientName, ObjectReceiver client) {
		try {
			Session session = getOrCreateSession(theSessionName);
			session.join(clientName, client, theApplicationName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public void doLeave(String theSessionName, String theApplicationName,
			String clientName, ObjectReceiver client) {
		try {
			Session session = getOrCreateSession(theSessionName);
			session.leave(clientName, client, theApplicationName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	public void register() {
		try {
			createMessageSenderRunnable();
			Registry registry = util.misc.Common.getOrCreateRegistry();
			SessionManager exportedServer = (SessionManager) UnicastRemoteObject
					.exportObject(this, 0);
			registry.rebind(SESSION_MANAGER_NAME, exportedServer);
			System.out.println("Registered " + SESSION_MANAGER_NAME
					+ " with RMI Server (" + Common.getHostName() + ", "
					+ Common.DEFAULT_RMI_PORT + ")");
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.fatalError("Could not register " + SESSION_MANAGER_NAME
					+ " with RMI Server (" + Common.getHostName() + ", "
					+ Common.DEFAULT_RMI_PORT + ")");
		}
	}

	@Override
	public Session createSession(String sessionName) {
		Session session = new ASession(sessionName);
		sessions.put(sessionName, session);
		return session;
	}

	@Override
	public Session getOrCreateSession(String sessionName)
			throws RemoteException {
		Session remoteSession = sessions.get(sessionName);
		if (remoteSession == null) {
			SessionCreated.newCase(CommunicatorSelector.getProcessName(), sessionName, this);
			remoteSession = createSession(sessionName);
		}
		return remoteSession;
	}

	@Override
	public void removeSession(String sessionName) {
		sessions.remove(sessionName);
	}

	public void newMessage(SentMessage theMessage) {
		MessageGivenToFilter.newCase(
				CommunicatorSelector.getProcessName(), 
				theMessage, 
				theMessage.getSendingUser(),
				this);
		messageQueuer.filterMessage(theMessage);
	}

	public void setSentMessageQueuer(String theSessionName,
			String theApplicationName, ServerMessageFilter theSentMessageQueuer)
			throws RemoteException {
		Session theSession = getOrCreateSession(theSessionName);
		ProcessGroupLocal processGroup = theSession
				.getProcessGroupLocal(theApplicationName);
		processGroup.setSentMessageQueuer(theSentMessageQueuer);
	}

}
