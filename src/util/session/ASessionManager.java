package util.session;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;

public class ASessionManager implements SessionManager, SessionManagerLocal {
	Map<String, Session> sessions = new HashMap();
	Runnable messageSenderRunnable;
	BoundedBuffer<SentMessage> outputMessageQueue;
	ServerMessageFilter messageQueuer;
	MessageProcessor<SentMessage> sentMessageProcessor;

	public ASessionManager() {
	}

	void createMessageSenderRunnable() {
		outputMessageQueue = new BoundedBuffer();
		messageSenderRunnable = new AServerMessageSenderRunnable(
				outputMessageQueue, this, null);
		Thread messageSenderThread = new Thread(messageSenderRunnable);
		sentMessageProcessor = new ASentMessageProcessor(outputMessageQueue);

		messageQueuer = AServerSentMessageQueuerSelector
				.getMessageQueuerFactory().getMessageQueuer();
		messageQueuer.setMessageProcessor(sentMessageProcessor);
		messageSenderThread.setName("Session Manager");
		messageSenderThread.start();
	}

	public void join(String theSessionName, String theApplicationName,
			String clientName, MessageReceiver client) throws RemoteException {
		SentMessageCreator messageCreator = new ASentMessageCreator(clientName,
				theSessionName, theApplicationName, client);
		SentMessage sentMessage = messageCreator.asyncJoin();
		messageQueuer.put(sentMessage);
		return;
	}

	public void doJoin(String theSessionName, String theApplicationName,
			String clientName, MessageReceiver client) {
		try {
			Session session = getOrCreateSession(theSessionName);
			session.join(clientName, client, theApplicationName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public void doLeave(String theSessionName, String theApplicationName,
			String clientName, MessageReceiver client) {
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
		if (remoteSession == null)
			remoteSession = createSession(sessionName);
		return remoteSession;
	}

	@Override
	public void removeSession(String sessionName) {
		sessions.remove(sessionName);
	}

	public void newMessage(SentMessage theMessage) {
		messageQueuer.put(theMessage);
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
