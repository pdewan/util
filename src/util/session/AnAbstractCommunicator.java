package util.session;

import util.trace.session.AddressedSentMessageInfo;
import util.trace.session.MessageGivenToFilter;
import util.trace.session.MinimumDelaySet;
import util.trace.session.SendDataRequest;
import util.trace.session.ToAllDateSendMarshalled;
import util.trace.session.ToOthersDataSendMarshalled;
import util.trace.session.ToUserDataSendMarshalled;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public abstract class AnAbstractCommunicator extends
		/* ASessionManagerClient */ASessionManagerCommunicator {
	MessageFilter<ReceivedMessage> receiveMessageFilter;
	
	public AnAbstractCommunicator(String theServerHost, String theSessionName,
			String theApplicationName, String theClientName,
			boolean theIsRelayedCommunication) {
		super(theServerHost, theSessionName, theApplicationName, theClientName,
				theIsRelayedCommunication);
	}

	public AnAbstractCommunicator(String[] args,
			boolean theIsRelayedCommunication) {

		super(args[0], args[1], args[2], args[3], theIsRelayedCommunication);
	}
	@Override
	public void toCaller(Object object) {
		toClient(getMessageReceiverRunnable().getCurrentSender(), object);
	}

	@Override
	public void toClient(String userName, Object object) {
		try {
			SendDataRequest.newCase(CommunicatorSelector.getProcessName(), object, userName, relayedCommunication, this);

			Object[] args = { userName, object, clientName,
					exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toUser(userName, object);
			ToUserDataSendMarshalled.newCase(					
					CommunicatorSelector.getProcessName(), message, 
					userName, this);
			getSentMessageFilter().filterMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void toUsers(String[] aUserNames, Object object) {
		// should send one message to server ideally
		for (String aUserName:aUserNames) {
			toClient(aUserName, object);
		}

	}

	@Override
	public void toOthers(Object object) {
		try {
			SendDataRequest.newCase(CommunicatorSelector.getProcessName(),object, AddressedSentMessageInfo.OTHERS, relayedCommunication, this);

			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toOthers(object);
			ToOthersDataSendMarshalled.newCase(
					
					CommunicatorSelector.getProcessName(), message, 
					AddressedSentMessageInfo.OTHERS, this);
			
			getSentMessageFilter().filterMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void toNonCallers(Object object) {
		try {
			SendDataRequest.newCase(CommunicatorSelector.getProcessName(),object, AddressedSentMessageInfo.NON_CALLERS, relayedCommunication, this);

			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toNonCallers(object, getMessageReceiverRunnable().getCurrentSender());
			ToOthersDataSendMarshalled.newCase(
					
					CommunicatorSelector.getProcessName(), message, 
					AddressedSentMessageInfo.OTHERS, this);
			
			getSentMessageFilter().filterMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toAll(Object object) {
		try {
			SendDataRequest.newCase(CommunicatorSelector.getProcessName(), object, AddressedSentMessageInfo.ALL, relayedCommunication, this);

			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toAll(object);
			ToAllDateSendMarshalled.newCase(CommunicatorSelector.getProcessName(), object, AddressedSentMessageInfo.ALL, this);

			getSentMessageFilter().filterMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static final String SERVER = "Server";
	public void setMinimumDelayToPeer(String thePeer, int theDelay) {
		MinimumDelaySet.newCase(CommunicatorSelector.getProcessName(),
				null, thePeer, theDelay, this);
		delayManager.setMinimumDelayToPeer(thePeer, theDelay);

	}

	public int getMinimumDelayToPeer(String thePeer) {
		return delayManager.getMinimumDelayToPeer(thePeer);

	}

	public boolean isRelayedCommunication() {
		return relayedCommunication;
	}
	public MessageFilter<ReceivedMessage> getReceivedMessageFilter() {
		return receiveMessageFilter;
	}
	public void setReceivedMessageFilter(MessageFilter<ReceivedMessage> newVal) {
		receiveMessageFilter = newVal;
	}

}
