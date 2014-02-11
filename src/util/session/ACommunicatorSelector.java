package util.session;

public class ACommunicatorSelector {
	static CommunicatorCreator directFactory = new ADirectCommunicatorCreator();
	static CommunicatorCreator relayerFactory = new ARelayerCommunicatorCreator();
	static CommunicatorCreator communicatorFactory = relayerFactory;

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

}
