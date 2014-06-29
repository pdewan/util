package util.session;

public class ServerSentMessageFilterSelector {
	static ServerMessageFilterCreator queuerFactory = new AServerMessageForwarderCreator();

	public static ServerMessageFilterCreator getMessageQueuerFactory() {
		return queuerFactory;
	}

	public static void setMessageFilterFactory(
			ServerMessageFilterCreator theFactory) {
		queuerFactory = theFactory;
	}
}
