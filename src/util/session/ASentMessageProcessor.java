package util.session;

import util.models.BoundedBuffer;

public class ASentMessageProcessor implements MessageProcessor<SentMessage> {
	BoundedBuffer<SentMessage> outBuffer;

	public ASentMessageProcessor(BoundedBuffer<SentMessage> theBoundedBuffer) {
		outBuffer = theBoundedBuffer;

	}

	@Override
	public void processMessage(SentMessage theMessage) {
		outBuffer.put(theMessage);

	}

}
