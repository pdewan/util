package util.session;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;
import util.trace.session.MessageSent;
import util.trace.session.SentMessageDelayed;

public class AMessageSenderRunnable implements MessageSenderRunnable {
	BoundedBuffer<SentMessage> outputMessageQueue;
	DelayManager delayManager;
	ProcessGroupLocal localMulticastGroup;
	ProcessGroup multicastGroup;
	SessionManager sessionManager;
	Session session;
	long delayToServer;

	public AMessageSenderRunnable(BoundedBuffer<SentMessage> theMessageQueue,
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

	public void run() {

		while (true) {
			try {
				SentMessage message = outputMessageQueue.get();
				Object[] args = message.getArgs();
				long delay = delayManager
						.calculateDelay(message.getTimeStamp());
				if (delay > 0) {
					// this seems to be the session manager delay. not client specific
					SentMessageDelayed.newCase(SessionManager.SESSION_MANAGER_NAME, message, delay, this);
				}
				switch (message.getSentMessageType()) {
				case Join:
					sleep(delay);
					sessionManager.newMessage(message); // server call
					MessageSent.newCase(SessionManager.SESSION_MANAGER_NAME, message, this);

					break;
				case Leave:
					sleep(delay);
					sessionManager.newMessage(message);
					MessageSent.newCase(SessionManager.SESSION_MANAGER_NAME, message, this);

//					session.leave((String) args[0], (MessageReceiver) args[1],
//							null); // server call
					break;
				case Others:
					if (multicastGroup == null) {
						Tracer.info("Early event, ignoring");
						break;
					}
					sleepIfRemoteMulticastGroup(delay);
					if (localMulticastGroup != null)
						localMulticastGroup.toOthers(args[0], (String) args[1],
								(MessageReceiver) args[2],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message); // sever rmi call
						MessageSent.newCase(SessionManager.SESSION_MANAGER_NAME, message, this);

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
					if (localMulticastGroup != null)
						localMulticastGroup.toAll(args[0], (String) args[1],
								(MessageReceiver) args[2],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message);
						MessageSent.newCase(SessionManager.SESSION_MANAGER_NAME, message, this);

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
								(String) args[2], (MessageReceiver) args[3],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message);
						MessageSent.newCase(SessionManager.SESSION_MANAGER_NAME, message, this);

					}
					break;
				case Hosts:
					if (multicastGroup == null) {
						Tracer.info("Early event, ignoring");
						break;
					}
					sleepIfRemoteMulticastGroup(delay);
					if (localMulticastGroup != null)
						localMulticastGroup.toUsers((String[]) args[0],
								args[1], (String) args[2],
								(MessageReceiver) args[3],
								message.getTimeStamp());
					else {
						multicastGroup.newMessage(message);
						MessageSent.newCase(SessionManager.SESSION_MANAGER_NAME, message, this);

					}
					break;

				}
//				MessageSent.newCase(message, this);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
