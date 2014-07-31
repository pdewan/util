package util.session;

import util.models.ABoundedBuffer;
import util.trace.session.MessagePutInQueue;
/*
 * Hides user-defined filter from internal queue
 */
public class ASentMessageQueuer implements MessageProcessor<SentMessage> {
	ABoundedBuffer<SentMessage> outBuffer;

	public ASentMessageQueuer(ABoundedBuffer<SentMessage> theBoundedBuffer) {
		outBuffer = theBoundedBuffer;

	}

	@Override
	public void processMessage(SentMessage theMessage) {
		MessagePutInQueue.newCase(CommunicatorSelector.getProcessName(), theMessage, theMessage.getSendingUser(), outBuffer.getName(), this);
		try {
			outBuffer.put(theMessage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
