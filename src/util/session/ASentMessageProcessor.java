package util.session;

import util.models.BoundedBuffer;
import util.trace.session.MessageInSendingQueue;

public class ASentMessageProcessor implements MessageProcessor<SentMessage> {
	BoundedBuffer<SentMessage> outBuffer;

	public ASentMessageProcessor(BoundedBuffer<SentMessage> theBoundedBuffer) {
		outBuffer = theBoundedBuffer;

	}

	@Override
	public void processMessage(SentMessage theMessage) {
		MessageInSendingQueue.newCase(theMessage, this);
		outBuffer.put(theMessage);

	}

}
