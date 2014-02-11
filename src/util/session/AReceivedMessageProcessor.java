package util.session;

import util.models.BoundedBuffer;

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
