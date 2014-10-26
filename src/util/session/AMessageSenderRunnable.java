package util.session;

import util.models.ABoundedBuffer;
import util.trace.Tracer;
import util.trace.session.MessageRetrievedFromQueue;
import util.trace.session.MessageSent;
import util.trace.session.SentMessageDelayed;

public class AMessageSenderRunnable implements MessageSenderRunnable {
	ABoundedBuffer<SentMessage> outputMessageQueue;
	DelayManager delayManager;
	ProcessGroupLocal localMulticastGroup; // the local one
	ProcessGroup multicastGroup; // the relayer group
	SessionManager sessionManager;
	Session session;
	long delayToServer;
	boolean relayedMode; // allow the mode to be changed if communicator is direct

	

	public AMessageSenderRunnable(ABoundedBuffer<SentMessage> theMessageQueue,
			DelayManager theDelayManager, SessionManager theSessionManager) {
		outputMessageQueue = theMessageQueue;
		delayManager = theDelayManager;
		sessionManager = theSessionManager;
	}

	public void setLocalProcessGroup(ProcessGroupLocal theMulticastGroup) {
		localMulticastGroup = theMulticastGroup;
	}

	public void setProcessGroup(ProcessGroup theProcessGroup) {
		if (multicastGroup == null)
			multicastGroup = theProcessGroup;

	}

	void sleep(long delay) throws InterruptedException {
		if (delay > 0) {
			Tracer.info(this, "Sender about to sleep for: " + delay);
			Thread.sleep(delay);
			Tracer.info(this, "Sender wakes up from sleep of: " + delay);

		}
	}

	void sleepIfRemoteMulticastGroup(long delay) throws InterruptedException {
		if (localMulticastGroup == null)
			sleep(delay);
	}
	//  relayed if there is no direct communicator or if the direct communicator is
	// in the relayed mode
	public boolean isRelayed() {
		return isRelayedMode() || localMulticastGroup == null;
	}

	public void run() {

		while (true) {
			try {
				SentMessage message = outputMessageQueue.get();
				MessageRetrievedFromQueue.newCase(
						CommunicatorSelector.getProcessName(), 
						message, 
//						message.getArgs()[3].toString(),
						null,
						outputMessageQueue.getName(),
						this);
				Object[] args = message.getArgs();
				long delay = delayManager
						.calculateDelay(message.getTimeStamp());
				if (delay > 0) {
					// this seems to be the session manager delay. not client specific
					SentMessageDelayed.newCase(CommunicatorSelector.getProcessName(), message, SessionManager.SESSION_MANAGER_NAME, delay, this);
				}
				switch (message.getSentMessageType()) {
				case Join:
					sleep(delay);
					sessionManager.newMessage(message); // server call
					MessageSent.newCase(CommunicatorSelector.getProcessName(), message, SessionManager.SESSION_MANAGER_NAME, this);

					break;
				case Leave:
					sleep(delay);
					sessionManager.newMessage(message);
					MessageSent.newCase(CommunicatorSelector.getProcessName(), message, SessionManager.SESSION_MANAGER_NAME, this);

//					session.leave((String) args[0], (MessageReceiver) args[1],
//							null); // server call
					break;
				case Others:
					if (multicastGroup == null) {
						Tracer.info("Early event, ignoring");
						break;
					}
					sleepIfRemoteMulticastGroup(delay);
//					if (localMulticastGroup != null && !isRelayedMode())
					if (!isRelayed())
						localMulticastGroup.toOthers(args[0], (String) args[1],
								(ObjectReceiver) args[2],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message); // sever rmi call
						MessageSent.newCase(CommunicatorSelector.getProcessName(),  message, SessionManager.SESSION_MANAGER_NAME, this);

					}
//					System.out.println("Client sending:"
//							+ message.getUserMessage());
					Tracer.info(this, "Client sending:"
							+ message.getUserMessage());
					break;
				case NonCallers:
					if (multicastGroup == null) {
						Tracer.info("Early event, ignoring");
						break;
					}
					sleepIfRemoteMulticastGroup(delay);
//					if (localMulticastGroup != null)
					if (!isRelayed())
						localMulticastGroup.toOthers(args[0], (String) args[1],
								(ObjectReceiver) args[2],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message); // sever rmi call
						MessageSent.newCase(CommunicatorSelector.getProcessName(),  message, SessionManager.SESSION_MANAGER_NAME, this);

					}
//					System.out.println("Client sending:"
//							+ message.getUserMessage());
					Tracer.info(this, "Client sending:"
							+ message.getUserMessage());
					break;
				case All:
					if (multicastGroup == null) {
						Tracer.info("Multicast event received before join, ignoring");
						break;
					}
					sleepIfRemoteMulticastGroup(delay);
//					if (localMulticastGroup != null)
					if (!isRelayed())
						localMulticastGroup.toAll(args[0], (String) args[1],
								(ObjectReceiver) args[2],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message);
						MessageSent.newCase(CommunicatorSelector.getProcessName(),  message, SessionManager.SESSION_MANAGER_NAME, this);

					}
					break;
				case User:
					if (multicastGroup == null) {
						Tracer.info("Early event, ignoring");
						break;
					}
					sleepIfRemoteMulticastGroup(delay);
					if (localMulticastGroup != null)
						localMulticastGroup.toUser(args[0], args[1],
								(String) args[2], (ObjectReceiver) args[3],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message);
						MessageSent.newCase(CommunicatorSelector.getProcessName(), message, SessionManager.SESSION_MANAGER_NAME, this);

					}
					break;
				case Hosts:
					if (multicastGroup == null) {
						Tracer.info("Early event, ignoring");
						break;
					}
					sleepIfRemoteMulticastGroup(delay);
//					if (localMulticastGroup != null)
					if (!isRelayed())
						localMulticastGroup.toUsers((String[]) args[0],
								args[1], (String) args[2],
								(ObjectReceiver) args[3],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message);
//						MessageSentNew.newCase(SessionManager.SESSION_MANAGER_NAME, message, this);
						MessageSent.newCase(CommunicatorSelector.getProcessName(),  message, SessionManager.SESSION_MANAGER_NAME, this);


					}
					break;

				}
//				MessageSent.newCase(message, this);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	public boolean isRelayedMode() {
		return relayedMode;
	}
	@Override
	public void setRelayedMode(boolean relayedMode) {
		this.relayedMode = relayedMode;
	}

}
