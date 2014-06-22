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
			SendDataRequest.newCase(CommunicatorSelector.getProcessName(), object, userName, isRelayedCommunication, this);

			Object[] args = { userName, object, clientName,
					exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toUser(userName, object);
			ToUserDataSendMarshalled.newCase(					
					CommunicatorSelector.getProcessName(), message, 
					userName, this);
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
			SendDataRequest.newCase(CommunicatorSelector.getProcessName(),object, AddressedSentMessageInfo.OTHERS, isRelayedCommunication, this);

			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toOthers(object);
			ToOthersDataSendMarshalled.newCase(
					
					CommunicatorSelector.getProcessName(), message, 
					AddressedSentMessageInfo.OTHERS, this);
			
			getSentMessageQueuer().put(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toAll(Object object) {
		try {
			SendDataRequest.newCase(CommunicatorSelector.getProcessName(), object, AddressedSentMessageInfo.ALL, isRelayedCommunication, this);

			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toAll(object);
			ToAllDateSendMarshalled.newCase(CommunicatorSelector.getProcessName(), object, AddressedSentMessageInfo.ALL, this);

			getSentMessageQueuer().put(message);
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
		return isRelayedCommunication;
	}

}
