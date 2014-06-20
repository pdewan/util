package util.session;

import util.models.ABoundedBuffer;
import util.trace.session.MessagePutInQueue;

public class ASentMessageProcessor implements MessageProcessor<SentMessage> {
	ABoundedBuffer<SentMessage> outBuffer;

	public ASentMessageProcessor(ABoundedBuffer<SentMessage> theBoundedBuffer) {
		outBuffer = theBoundedBuffer;

	}

	@Override
	public void processMessage(SentMessage theMessage) {
		MessagePutInQueue.newCase(ACommunicatorSelector.getProcessName(), theMessage, theMessage.getSendingUser(), outBuffer.getName(), this);
		outBuffer.put(theMessage);

	}

}
