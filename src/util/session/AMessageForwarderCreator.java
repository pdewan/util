package util.session;

public class AMessageForwarderCreator<MessageType> implements
		MessageFilterCreator<MessageType> {

	@Override
	public MessageFilter<MessageType> getMessageFilter() {
		return new AMessageForwarder<MessageType>();
	}
}
