package util.session;

import util.trace.session.ReceivedJoinNotificationDistributedToListeners;
import util.trace.session.ReceivedJoinNotificationDistributedToListeners;
import util.trace.session.ReceivedLeaveNotificationDistributedToListeners;
import util.trace.session.ReceivedLeaveNotificationDistributedToListeners;
import util.trace.session.ReceivedMessageDistributedToListeners;

public class AReceivedMessageProcessor implements
		MessageProcessor<ReceivedMessage> {
	DelayedMessageReceiver multicastClient;

	public AReceivedMessageProcessor(DelayedMessageReceiver theMulticastClient) {
		multicastClient = theMulticastClient;
	}

	@Override
	//essentialy unmarshalls the message so calls can be made to the clients
	public void processMessage(ReceivedMessage message) {

		switch (message.getReceivedMessageType()) {
		case ClientLeft: // interesting the client is unblocked before the notification is sent
			ReceivedLeaveNotificationDistributedToListeners.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedUserLeft(message.getClientName(),
					message.getClient(), message.getApplicationName());
			;
			break;
		case ClientJoined:
//			ReceivedJoinNotificationDistributedToListeners.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), this);
			ReceivedJoinNotificationDistributedToListeners.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedUserJoined(message.getClients(),
					message.getClientName(), message.getClient(),
					message.getApplicationName(), message.isNewSession(),
					message.isNewApplication());
			;
			break;
		case NewObject:
			ReceivedMessageDistributedToListeners.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedNewObject(message.getClientName(),
					message.getUserMessage());
			break;

		}

	}

}
