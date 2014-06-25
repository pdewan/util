package util.session;

import util.trace.session.MessageForwarded;

/*
 * Default system filter sich simply forwards the messages to message processor
 */
public class AMessageForwarder<MessageType> implements
		MessageFilter<MessageType> {
	MessageProcessor<MessageType> messageProcessor;

	public void filterMessage(MessageType aMessage) {
		MessageForwarded.newCase(CommunicatorSelector.getProcessName(), aMessage, this);
		messageProcessor.processMessage(aMessage);
	}

	public void setMessageProcessor(
			MessageProcessor<MessageType> theMesssageProcessor) {
		messageProcessor = theMesssageProcessor;

	}
}
