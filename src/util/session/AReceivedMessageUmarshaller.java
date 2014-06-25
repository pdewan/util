package util.session;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

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
	MessageDispatcher multicastClient;
	String clientName;
	boolean joinNotificationReceived = false;

	public AReceivedMessageUmarshaller(MessageDispatcher theMulticastClient, String aClientName) {
		multicastClient = theMulticastClient;
		clientName = aClientName;
	}

	@Override
	//essentialy unmarshalls the message so calls can be made to the clients
	public void processMessage(ReceivedMessage message) {

		switch (message.getReceivedMessageType()) {
		case ClientLeft: // interesting the client is unblocked before the notification is sent
			ClientLeaveNotificationUnmarshalled.newCase(CommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedUserLeft(message.getClientName(),
					message.getClient(), message.getApplicationName());
			;
			break;
		case ClientJoined:
			ClientJoinNotificationUnmarshalled.newCase(CommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);
			// this noitification a side effect, it seems to change the message receivers accoaited with a process group in serializedprocessgroups
			multicastClient.delayedUserJoined(message.getClients(),
					message.getClientName(), message.getClient(),
					message.getApplicationName(), message.isNewSession(),
					message.isNewApplication());
			if (!joinNotificationReceived) { // first mesage. inform listeners about all process groups
				joinNotificationReceived = true;
				SerializedProcessGroups aSerializedProcessGroups = message.getSerializedProcessGroups();
				Set<String> aProcessGroupNames = aSerializedProcessGroups.keySet();
				boolean isNewSession = false;
				
				for (String aProcessGroupName:aProcessGroupNames) {
					Map<ObjectReceiver, String> aClients = aSerializedProcessGroups.get(aProcessGroupName);
					Set<ObjectReceiver> aClientReferences = aClients.keySet();
					boolean isNewApplication = true;
					for (ObjectReceiver aClientReference:aClientReferences) {
						
					String aClientName = aClients.get(aClientReference);
					// should not get the following notification, but if so, check
					if (aClientName.equals(clientName)) {
						
//						isNewSession = false;
						isNewApplication = false;
						continue;
					}
					multicastClient.delayedUserJoined(aClients,
							aClients.get(aClientReference), aClientReference,
							aProcessGroupName, isNewSession,
							isNewApplication);
					isNewSession = false;
					isNewApplication = false;
					}
					
				}
			} 
//			else { // incremental
//			multicastClient.delayedUserJoined(message.getClients(),
//					message.getClientName(), message.getClient(),
//					message.getApplicationName(), message.isNewSession(),
//					message.isNewApplication());
//			}
			
			;
			break;
		case NewObject:
			ClientReceivedObjectUnmarshalled.newCase(CommunicatorSelector.getProcessName(), message.getUserMessage(), message.getClientName(), this);

			multicastClient.delayedNewObject(message.getClientName(),
					message.getUserMessage());
			break;

		}

	}

}
