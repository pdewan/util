package util.session;
/*
 * This is the interface implemented by some user module that wants to
 * intercept messages. It receives messages from the delaying message
 * receiver and send it to the module that distributes information
 * to listeners. Renamed method from put to filter.
 */
public interface MessageFilter<MessageType> {
	void setMessageProcessor(MessageProcessor<MessageType> theMesssageProcessor);
	// need to call it filter
	void put(MessageType message);
}
