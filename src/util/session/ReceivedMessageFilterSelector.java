package util.session;

public class ReceivedMessageFilterSelector {
	static MessageFilterCreator<ReceivedMessage> filterFactory = new AMessageForwarderCreator<ReceivedMessage>();

	public static MessageFilterCreator<ReceivedMessage> getMessageFilterCreator() {
		return filterFactory;
	}

	public static void setMessageFilterCreator(
			MessageFilterCreator<ReceivedMessage> theFactory) {
		filterFactory = theFactory;
	}
}
