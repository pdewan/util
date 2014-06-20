package util.session;

public class ACommunicatorSelector {
	static CommunicatorCreator directFactory = new ADirectCommunicatorCreator();
	static CommunicatorCreator relayerFactory = new ARelayerCommunicatorCreator();
	static CommunicatorCreator communicatorFactory = relayerFactory;
	static Communicator communicator;

	public static CommunicatorCreator getCommunicatorFactory() {
		return communicatorFactory;
	}

	public static void setCommunicatorFactory(
			CommunicatorCreator messageSenderFactory) {
		ACommunicatorSelector.communicatorFactory = messageSenderFactory;
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
		return communicator;
	}

	public static Communicator getCommunicator(String[] args) {
		communicator = communicatorFactory.getCommunicator(args);
		return communicator;

	}
	
	public static Communicator getCommunicator() {
		return communicator;
	}
	
	public static String getProcessName() {
		if (communicator != null)
			return communicator.getUserName();
		else
			return ASessionManager.SESSION_MANAGER_NAME;
	}
	
	public static boolean isServer() {
		
			return communicator == null;
	}

}
