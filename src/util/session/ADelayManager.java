package util.session;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public class ADelayManager implements DelayManager {

	int delayToServer, delayVariation;
	Map<String, Integer> peerDelays = new HashMap();
	List<UserDelayRecord> sortedClients = new ArrayList();
	CommunicatorInternal communicator;

	public ADelayManager(CommunicatorInternal theCommunicator) {
		communicator = theCommunicator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#getMinimumDelayToServer()
	 */
	public int getMinimumDelayToServer() {
		return delayToServer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#setMinimumDelayToServer(int)
	 */
	public void setMinimumDelayToServer(int theDelay) {
		delayToServer = theDelay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#getDelayVariation()
	 */
	public int getDelayVariation() {
		return delayVariation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#setDelayVariation(int)
	 */
	public void setDelayVariation(int theDelay) {
		delayVariation = theDelay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#calculateDelay(long)
	 */
	public long calculateDelay(long messageTime) {
		return calculateDelay(messageTime, getMinimumDelayToServer(),
				getDelayVariation());

	}

	public static long calculateDelay(long messageTime, int minimumDelay,
			int delayVariation) {
		double random = Math.random();
		long randomDelay = Math.round(delayVariation * random);
		long actualDelay = randomDelay + minimumDelay
				- (System.currentTimeMillis() - messageTime);
		return actualDelay;
	}

	public void refreshClients() {
		if (communicator.getClients().size() == sortedClients.size())
			return;
		Set<MessageReceiver> keys = communicator.getClients().keySet();
		sortedClients.clear();
		for (MessageReceiver client : keys) {
			sortedClients
					.add(new AUserDelayRecord(client, communicator.getClients()
							.get(client), getMinimumDelayToPeer(communicator
							.getClients().get(client))));
		}
	}

	public void refreshAndSortClients() {
		refreshClients();
		Collections.sort(sortedClients);
	}

	public void setMinimumDelayToPeer(String thePeer, int theDelay) {
		peerDelays.put(thePeer, theDelay);
		refreshClients();
		UserDelayRecord userDelayRecord = getUserDelayRecord(thePeer);
		if (userDelayRecord == null)
			return;
		userDelayRecord.setDelay(theDelay);
		Collections.sort(sortedClients);

	}

	public int getMinimumDelayToPeer(String thePeer) {
		Integer retVal = peerDelays.get(thePeer);
		if (retVal == null)
			return 0;
		else
			return retVal;
	}

	UserDelayRecord getUserDelayRecord(String client) {
		for (UserDelayRecord record : sortedClients) {
			if (record.getName().equals(client))
				return record;
		}
		return null;
	}

	public List<UserDelayRecord> getSortedClients() {
		return sortedClients;
	}

}
