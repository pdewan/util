package util.session;

public class ServerSentMessageFilterSelector {
	static ServerMessageFilterCreator factory = new AServerMessageForwarderCreator();

	public static ServerMessageFilterCreator getMessageFilterFactory() {
		return factory;
	}

	public static void setMessageFilterFactory(
			ServerMessageFilterCreator theFactory) {
		factory = theFactory;
	}
}
