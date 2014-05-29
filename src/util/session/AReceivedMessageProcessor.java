package util.session;

import util.trace.session.ReceivedJoinNotificationDistributedToListeners;
import util.trace.session.ReceivedJoinNotificationDistributedToListenersNew;
import util.trace.session.ReceivedLeaveNotificationDistributedToListeners;
import util.trace.session.ReceivedLeaveNotificationDistributedToListenersNew;
import util.trace.session.ReceivedMessageDistributedToListenersNew;

public class AReceivedMessageProcessor implements
		MessageProcessor<ReceivedMessage> {
	DelayedMessageReceiver multicastClient;

	public AReceivedMessageProcessor(DelayedMessageReceiver theMulticastClient) {
		multicastClient = theMulticastClient;
	}

	@Override
	public void processMessage(ReceivedMessage message) {

		switch (message.getReceivedMessageType()) {
		case ClientLeft:
			ReceivedLeaveNotificationDistributedToListenersNew.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedUserLeft(message.getClientName(),
					message.getClient(), message.getApplicationName());
			;
			break;
		case ClientJoined:
//			ReceivedJoinNotificationDistributedToListeners.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), this);
			ReceivedJoinNotificationDistributedToListenersNew.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedUserJoined(message.getClients(),
					message.getClientName(), message.getClient(),
					message.getApplicationName(), message.isNewSession(),
					message.isNewApplication());
			;
			break;
		case NewObject:
			ReceivedMessageDistributedToListenersNew.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedNewObject(message.getClientName(),
					message.getUserMessage());
			break;

		}

	}

}
