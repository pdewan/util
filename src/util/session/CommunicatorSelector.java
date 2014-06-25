package util.session;
/*
 * selects whether a direct or relayed communicator is created
 */

public class CommunicatorSelector {
	static CommunicatorCreator directFactory = new ADirectCommunicatorCreator();
	static CommunicatorCreator relayerFactory = new ARelayerCommunicatorCreator();
	static CommunicatorCreator communicatorFactory = relayerFactory;
	static Communicator communicator;
	static Communicator directCommunicator, relayerCommunicator;

	public static CommunicatorCreator getCommunicatorFactory() {
		return communicatorFactory;
	}

	public static void setCommunicatorFactory(
			CommunicatorCreator messageSenderFactory) {
		CommunicatorSelector.communicatorFactory = messageSenderFactory;
	}

	public static void selectRelayerCommunicator() {
		communicatorFactory = relayerFactory;
	}

	public static void selectDirectCommunicator() {
		communicatorFactory = directFactory;
	}
	public static Communicator getCommunicator(String serverHost,
			String theSessionName, String userName, String theApplicationName) {
		communicator = communicatorFactory.getCommunicator(serverHost, theSessionName, userName, theApplicationName);
		if (communicator instanceof ADirectCommunicator)
			directCommunicator = communicator;
		else if (communicator instanceof ARelayerCommunicator) 
			relayerCommunicator = communicator;
		return communicator;
	}
	
	public static Communicator getCommunicator(String serverHost,
			String theSessionName, String userName, String theApplicationName, String aCommunicatorKind) {
		CommunicatorCreator aFactory;
		if (aCommunicatorKind.equals(Communicator.DIRECT))
			aFactory = directFactory;
		else
			aFactory = relayerFactory;
		return aFactory.getCommunicator(serverHost, theSessionName, userName, theApplicationName);
	}
	
	public static Communicator getDirectCommunicator(String serverHost,
			String theSessionName, String userName, String theApplicationName) {
		if (directCommunicator == null)
			directCommunicator = directFactory.getCommunicator(serverHost, theSessionName, userName, theApplicationName);
		return directCommunicator;
	}
	
	public static Communicator getRelayerCommunicator(String serverHost,
			String theSessionName, String userName, String theApplicationName) {
		if (relayerCommunicator == null)
			relayerCommunicator = relayerFactory.getCommunicator(serverHost, theSessionName, userName, theApplicationName);
		return relayerCommunicator;
	}

	public static Communicator getCommunicator(String[] args) {
		communicator = communicatorFactory.getCommunicator(args);
		return communicator;

	}
	
	public static Communicator getCommunicator() {
		return communicator;
	}
	// process and user names are synonymous
	public static String getProcessName() {
		if (communicator != null)
			return communicator.getUserName();
		else
			return ASessionManager.SESSION_MANAGER_NAME;
	}
	// added for OT so that a static method can be called to determine globally to see if a process i local or global
	// a server of session manager module is not itself a commnicator
	public static boolean isServer() {
		
			return communicator == null;
	}

}
