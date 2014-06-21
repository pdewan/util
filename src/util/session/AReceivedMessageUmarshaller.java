package util.session;

import util.trace.session.ClientJoinNotificationUnmarshalled;
import util.trace.session.ClientJoinNotificationUnmarshalled;
import util.trace.session.ClientLeaveNotificationUnmarshalled;
import util.trace.session.ClientReceivedObjectUnmarshalled;
import util.trace.session.ReceivedLeaveNotificationUnmarshalled;
import util.trace.session.ReceivedLeaveNotificationUnmarshalled;
import util.trace.session.ReceivedMessageDistributedToListeners;
/*
 * Should be called message unmarshaller
 */
public class AReceivedMessageUmarshaller implements
		MessageProcessor<ReceivedMessage> {
	DelayedMessageReceiver multicastClient;

	public AReceivedMessageUmarshaller(DelayedMessageReceiver theMulticastClient) {
		multicastClient = theMulticastClient;
	}

	@Override
	//essentialy unmarshalls the message so calls can be made to the clients
	public void processMessage(ReceivedMessage message) {

		switch (message.getReceivedMessageType()) {
		case ClientLeft: // interesting the client is unblocked before the notification is sent
			ClientLeaveNotificationUnmarshalled.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedUserLeft(message.getClientName(),
					message.getClient(), message.getApplicationName());
			;
			break;
		case ClientJoined:
			ClientJoinNotificationUnmarshalled.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

//			ReceivedJoinNotificationDistributedToListeners.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), this);

			multicastClient.delayedUserJoined(message.getClients(),
					message.getClientName(), message.getClient(),
					message.getApplicationName(), message.isNewSession(),
					message.isNewApplication());
			;
			break;
		case NewObject:
			ClientReceivedObjectUnmarshalled.newCase(ACommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedNewObject(message.getClientName(),
					message.getUserMessage());
			break;

		}

	}

}
