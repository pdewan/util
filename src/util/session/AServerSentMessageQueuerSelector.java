package util.session;

public class AServerSentMessageQueuerSelector {
	static ServerMessageFilterCreator queuerFactory = new AServerMessageForwarderCreator();

	public static ServerMessageFilterCreator getMessageQueuerFactory() {
		return queuerFactory;
	}

	public static void setMessageQueuerFactory(
			ServerMessageFilterCreator theFactory) {
		queuerFactory = theFactory;
	}
}
