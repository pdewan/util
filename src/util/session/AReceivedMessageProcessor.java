package util.session;

import util.models.BoundedBuffer;
import util.trace.session.ReceivedMessageDistributedToListeners;

public class AReceivedMessageProcessor implements
		MessageProcessor<ReceivedMessage> {
	DelayedMessageReceiver multicastClient;

	public AReceivedMessageProcessor(DelayedMessageReceiver theMulticastClient) {
		multicastClient = theMulticastClient;
	}

	@Override
	public void processMessage(ReceivedMessage message) {
		ReceivedMessageDistributedToListeners.newCase(message, this);

		switch (message.getReceivedMessageType()) {
		case ClientLeft:
			multicastClient.delayedUserLeft(message.getClientName(),
					message.getClient(), message.getApplicationName());
			;
			break;
		case ClientJoined:
			multicastClient.delayedUserJoined(message.getClients(),
					message.getClientName(), message.getClient(),
					message.getApplicationName(), message.isNewSession(),
					message.isNewApplication());
			;
			break;
		case NewObject:
			multicastClient.delayedNewObject(message.getClientName(),
					message.getUserMessage());
			break;

		}

	}

}
