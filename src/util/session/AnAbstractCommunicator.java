package util.session;

import util.trace.session.AddressedSentMessageInfo;
import util.trace.session.SendDataRequest;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public abstract class AnAbstractCommunicator extends
		/* ASessionManagerClient */ASessionManagerCommunicator {
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
	public void toUser(String userName, Object object) {
		try {
			SendDataRequest.newCase(ACommunicatorSelector.getProcessName(), object, userName, isRelayedCommunication, this);

			Object[] args = { userName, object, clientName,
					exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toUser(userName, object);
			getSentMessageQueuer().put(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void toUsers(String[] hostNames, Object object) {

	}

	@Override
	public void toOthers(Object object) {
		try {
			SendDataRequest.newCase(ACommunicatorSelector.getProcessName(),object, AddressedSentMessageInfo.OTHERS, isRelayedCommunication, this);

			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toOthers(object);
			getSentMessageQueuer().put(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toAll(Object object) {
		try {
			SendDataRequest.newCase(ACommunicatorSelector.getProcessName(), object, AddressedSentMessageInfo.ALL, isRelayedCommunication, this);

			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toAll(object);
			getSentMessageQueuer().put(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMinimumDelayToPeer(String thePeer, int theDelay) {
		delayManager.setMinimumDelayToPeer(thePeer, theDelay);

	}

	public int getMinimumDelayToPeer(String thePeer) {
		return delayManager.getMinimumDelayToPeer(thePeer);

	}

	public boolean isRelayedCommunication() {
		return isRelayedCommunication;
	}

}
