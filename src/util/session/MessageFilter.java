package util.session;

public interface MessageFilter<MessageType> {
	void setMessageProcessor(MessageProcessor<MessageType> theMesssageProcessor);

	void put(MessageType message);
}
