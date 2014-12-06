package util.session;

public class ServerSentMessageFilterSelector {
	static ServerMessageFilterCreator factory = new AServerMessageForwarderCreator();

	public static ServerMessageFilterCreator getMessageQueuerFactory() {
		return factory;
	}

	public static void setMessageFilterFactory(
			ServerMessageFilterCreator theFactory) {
		factory = theFactory;
	}
}
