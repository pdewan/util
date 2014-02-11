package util.session;

public class ASentMessageFilterSelector {
	static MessageFilterCreator<SentMessage> filterFactory = new AMessageForwarderCreator<SentMessage>();

	public static MessageFilterCreator<SentMessage> getMessageFilterCreator() {
		return filterFactory;
	}

	public static void setMessageFilterCreator(
			MessageFilterCreator<SentMessage> theFactory) {
		filterFactory = theFactory;
	}
}
