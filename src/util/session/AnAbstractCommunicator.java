package util.session;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.AddressedSentMessageInfo;
import util.trace.session.MessageInfo;
import util.trace.session.MessageInSendingQueue;
import util.trace.session.SendMessageRequest;

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
			Object[] args = { userName, object, clientName,
					exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toUser(userName, object);
			getSentMessageQueuer().put(message);
			SendMessageRequest.newCase(object, userName, isRelayedCommunication, this);
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
			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toOthers(object);
			getSentMessageQueuer().put(message);
			SendMessageRequest.newCase(object, AddressedSentMessageInfo.OTHERS, isRelayedCommunication, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toAll(Object object) {
		try {
			Object[] args = { object, clientName, exportedMessageReceiver };
			SentMessage message = sentMessageCreator.toAll(object);
			getSentMessageQueuer().put(message);
			SendMessageRequest.newCase(object, AddressedSentMessageInfo.ALL, isRelayedCommunication, this);
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
