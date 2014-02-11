package util.session;

public class AMessageForwarder<MessageType> implements
		MessageFilter<MessageType> {
	MessageProcessor<MessageType> messageProcessor;

	public void put(MessageType sentMessage) {
		messageProcessor.processMessage(sentMessage);
	}

	public void setMessageProcessor(
			MessageProcessor<MessageType> theMesssageProcessor) {
		messageProcessor = theMesssageProcessor;

	}
}
