package util.session;

import java.util.Map;

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
		delayManager.createThread();

	}

	public ADirectCommunicator(String[] args) {

		super(args[0], args[1], args[2], args[3], false);
		delayManager.createThread();

	}

	void createOutputBufferAndThread() {
		super.createOutputBufferAndThread();
		localProcessGroup = new AProcessGroup(sessionName, applicationName, this);
		addSessionListenerLocal(localProcessGroup);
		messageSenderRunnable.setLocalProcessGroup(localProcessGroup);
	}

	public void setClients(Map<ObjectReceiver, String> theClients) {
		super.setClients(theClients);
		try {
			localProcessGroup.setClients(theClients);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
