package util.session;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public class ADirectCommunicator extends AnAbstractCommunicator /*
																 * implements
																 * SessionListener
																 */{
	ProcessGroupLocal localProcessGroup;

	public ADirectCommunicator(String theServerHost, String theSessionName,
			String theApplicationName, String theClientName) {
		super(theServerHost, theSessionName, theApplicationName, theClientName,
				false);

	}

	public ADirectCommunicator(String[] args) {

		super(args[0], args[1], args[2], args[3], false);
	}

	void createOutputBufferAndThread() {
		super.createOutputBufferAndThread();
		localProcessGroup = new AProcessGroup(applicationName, this);
		addSessionListenerLocal(localProcessGroup);
		messageSenderRunnable.setLocalProcessGroup(localProcessGroup);
	}

	public void setClients(Map<MessageReceiver, String> theClients) {
		super.setClients(theClients);
		try {
			localProcessGroup.setClients(theClients);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
