package util.session;

import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import util.trace.Tracer;
import util.trace.session.MulticastGroupCreated;
import util.trace.session.ServerClientJoined;
import util.trace.session.ServerClientLeft;
import util.trace.session.SessionJoinInformationUpdated;
import util.trace.session.SessionLeaveInformationUpdated;

public class ASession extends ASessionListenable implements Session {
	Map<String, ProcessGroup> multicastGroups = new HashMap();
	Map<String, ProcessGroupLocal> localProcessGroups = new HashMap();

	String myName;

	public ASession(String theSessionName) {
		myName = theSessionName;
	}

	SerializedProcessGroups toSerializedProcesstGroups() {
		SerializedProcessGroups retVal = new ASerializedMulticastGroups();
		try {
			for (String appName : multicastGroups.keySet()) {
				retVal.put(appName, localProcessGroups.get(appName)
						.getClients());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return retVal;

	}

	public String[] getUserNames(ObjectReceiver theClient) {
		String[] values = {};
		values = clients.values().toArray(values);
		return values;
	}

	@Override
	public Map<ObjectReceiver, String> getClients() {
		return clients;
	}
	@Override
	public Collection<String> getClientNames() {
		return clients.values();
	}

	@Override
	public synchronized void join(String theClientName,
			ObjectReceiver theClient, String theApplicationName) {
		try {
			Tracer.info(this, "Entering Session Join");
			SessionJoinInformationUpdated.newCase(
					CommunicatorSelector.getProcessName(), 
					theClientName, theApplicationName, myName, this);
			JoinInfo retVal = new JoinInfo();
			retVal.newSession = clients.size() == 0;
			ProcessGroup processGroupRemote = multicastGroups
					.get(theApplicationName);
			ProcessGroupLocal procesGroupLocal = localProcessGroups
					.get(theApplicationName);
			retVal.newApplication = processGroupRemote == null;

			if (processGroupRemote == null) {
				MulticastGroupCreated.newCase(
						CommunicatorSelector.getProcessName(), 
						 myName, this);
				AProcessGroup processGroup = new AProcessGroup(myName,
						theApplicationName, null);
				procesGroupLocal = processGroup;
				addSessionListenerLocal(procesGroupLocal);
				processGroupRemote = (ProcessGroup) UnicastRemoteObject
						.exportObject(processGroup, 0);
				multicastGroups.put(theApplicationName, processGroupRemote);
				localProcessGroups.put(theApplicationName, procesGroupLocal);
			}
			clients.put(theClient, theClientName);
			addSessionListener(theClient);
			notifyClientJoinedRemote(processGroupRemote,
					toSerializedProcesstGroups(), clients, myName,
					theClientName, theClient, theApplicationName,
					retVal.newSession, retVal.newApplication);
			notifyClientJoinedLocal(procesGroupLocal,
					toSerializedProcesstGroups(), clients, myName,
					theClientName, theClient, theApplicationName,
					retVal.newSession, retVal.newApplication);
			Tracer.info(this, "Leaving Session Join");
			// why was this commented out?
			ServerClientJoined.newCase(CommunicatorSelector.getProcessName(), theClientName, theApplicationName, myName, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void leave(String theClientName, ObjectReceiver theClient,
			String theApplicationName) {
		SessionLeaveInformationUpdated.newCase(
				CommunicatorSelector.getProcessName(), 
				theClientName, theApplicationName, myName, this);
		clients.remove(theClient);
		removeSessionListener(theClient);
		notifyClentLeftRemote(theClientName, theClient, theApplicationName);
//		ServerClientLeft.newCase(ACommunicatorSelector.getProcessName(), theClientName, theApplicationName, myName, this);
	}

	@Override
	public ProcessGroup getProcessGroup(String theApplicationName) {
		return multicastGroups.get(theApplicationName);
	}

	@Override
	public ProcessGroupLocal getProcessGroupLocal(String theApplicationName) {
		return localProcessGroups.get(theApplicationName);
	}
}
