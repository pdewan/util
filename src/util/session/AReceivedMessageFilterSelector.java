package util.session;

public class AReceivedMessageFilterSelector {
	static MessageFilterCreator<ReceivedMessage> queuerFactory = new AMessageForwarderCreator<ReceivedMessage>();

	public static MessageFilterCreator<ReceivedMessage> getMessageFilterFactory() {
		return queuerFactory;
	}

	public static void setMessageFilterFactory(
			MessageFilterCreator<ReceivedMessage> theFactory) {
		queuerFactory = theFactory;
	}
}
