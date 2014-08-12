package util.session;

public class ReceivedMessageFilterSelector {
	static MessageFilterCreator<ReceivedMessage> filterFactory = new AMessageForwarderCreator<ReceivedMessage>();

	public static MessageFilterCreator<ReceivedMessage> getMessageFilterFactory() {
		return filterFactory;
	}

	public static void setMessageFilterFactory(
			MessageFilterCreator<ReceivedMessage> theFactory) {
		filterFactory = theFactory;
	}
}
