package util.session;

public interface MessageFilterCreator<MessageType> {
	MessageFilter<MessageType> getMessageFilter();
}
