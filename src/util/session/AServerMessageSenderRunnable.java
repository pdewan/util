package util.session;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;

public class AServerMessageSenderRunnable implements Runnable {
	BoundedBuffer<SentMessage> outputMessageQueue;
	ProcessGroupLocal multicastGroup;
	SessionManagerLocal sessionManager;

	public AServerMessageSenderRunnable(
			BoundedBuffer<SentMessage> theMessageQueue,
			SessionManagerLocal theSessionManager,
			ProcessGroupLocal theProcessGroup) {
		outputMessageQueue = theMessageQueue;
		multicastGroup = theProcessGroup;
		sessionManager = theSessionManager;
	}
	
	public void run() {
		// seems to be a single thread for all clients, does the joins etc asynchronously
		// what about results. Methods called on session manager or multicast group
		while (true) {
			try {
				SentMessage message = outputMessageQueue.get();
				Object[] args = message.getArgs();

				switch (message.getSentMessageType()) {
				case Join:
					sessionManager.doJoin(message.getSessionName(),
							message.getApplicationName(),
							message.getSendingUser(),
							message.getMessageReceiver());
					break;
				case Leave:
					sessionManager.doLeave(message.getSessionName(),
							message.getApplicationName(),
							message.getSendingUser(),
							message.getMessageReceiver());
					break;
				case Others:
					multicastGroup.toOthers(args[0], (String) args[1],
							(MessageReceiver) args[2], message.getTimeStamp());
					break;
				case All:
					multicastGroup.toAll(args[0], (String) args[1],
							(MessageReceiver) args[2], message.getTimeStamp());
					break;
				case User:
					multicastGroup.toUser(args[0], args[1], (String) args[2],
							(MessageReceiver) args[3], message.getTimeStamp());
					break;
				case Hosts:
					multicastGroup.toUsers((String[]) args[0], args[1],
							(String) args[2], (MessageReceiver) args[3],
							message.getTimeStamp());
					break;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
