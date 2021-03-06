package util.session;

import util.models.ABoundedBuffer;
import util.trace.session.MessageRetrievedFromQueue;
import util.trace.session.ServerJoinRequestUnmarshalled;
import util.trace.session.ServerLeaveRequestUnmarshalled;

public class AServerMessageSenderRunnable implements Runnable {
	ABoundedBuffer<SentMessage> outputMessageQueue; // incoming messages, so why is this called an output messge queue?
	ProcessGroupLocal multicastGroup; // are either sent to a relayer
	SessionManagerLocal sessionManager; // or do joins

	public AServerMessageSenderRunnable(
			ABoundedBuffer<SentMessage> theMessageQueue,
			SessionManagerLocal theSessionManager,
			ProcessGroupLocal theProcessGroup) {
		outputMessageQueue = theMessageQueue;
		multicastGroup = theProcessGroup;
		sessionManager = theSessionManager;
		if (sessionManager == null) {
			sessionManager = ASessionManagerSelector.getSessionManager();
		}
	}
	
	public void run() {
		// seems to be a single thread for all clients, does the joins etc asynchronously
		// what about results. Methods called on session manager or multicast group
		while (true) {
			try {
				SentMessage message = outputMessageQueue.get();
				MessageRetrievedFromQueue.newCase(
						CommunicatorSelector.getProcessName(), 
						message, 
						null,
						outputMessageQueue.getName(),
						this);
				Object[] args = message.getArgs();

				switch (message.getSentMessageType()) {
				case Join:
					ServerJoinRequestUnmarshalled.newCase(CommunicatorSelector.getProcessName(), message, message.getSendingUser(), this);
					sessionManager.doJoin(message.getSessionName(),
							message.getApplicationName(),
							message.getSendingUser(),
							message.getMessageReceiver());
					break;
				case Leave:
					ServerLeaveRequestUnmarshalled.newCase(CommunicatorSelector.getProcessName(), message, message.getSendingUser(), this);

					sessionManager.doLeave(message.getSessionName(),
							message.getApplicationName(),
							message.getSendingUser(),
							message.getMessageReceiver());
					break;
				case Others:
					multicastGroup.toOthers(args[0], (String) args[1],
							(ObjectReceiver) args[2], message.getTimeStamp());
					break;
				case All:
					multicastGroup.toAll(args[0], (String) args[1],
							(ObjectReceiver) args[2], message.getTimeStamp());
					break;
				case User:
					multicastGroup.toUser(args[0], args[1], (String) args[2],
							(ObjectReceiver) args[3], message.getTimeStamp());
					break;
				case Hosts:
					multicastGroup.toUsers((String[]) args[0], args[1],
							(String) args[2], (ObjectReceiver) args[3],
							message.getTimeStamp());
					break;

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
