package util.session;

public interface MessageProcessor<MessageType> {
	public void processMessage(MessageType theMessage);

}
