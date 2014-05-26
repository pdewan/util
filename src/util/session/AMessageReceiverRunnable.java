package util.session;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;

public class AMessageReceiverRunnable implements MessageReceiverRunnable {
	BoundedBuffer<ReceivedMessage> inputMessageQueue;
	DelayManager delayManager;
	MessageFilter<ReceivedMessage> messsageQueuer;
	boolean isRelayedCommunication;
	// receives enqueued RMI calls and also delays them. So the delay is at the receiver rather than sending end
	public AMessageReceiverRunnable(
			BoundedBuffer<ReceivedMessage> theMessageQueue,
			DelayManager theDelayManager,
			MessageFilter<ReceivedMessage> theMessageProcessor) {
		inputMessageQueue = theMessageQueue;
		delayManager = theDelayManager;
		messsageQueuer = theMessageProcessor;
	}

	boolean isServerMessage(ReceivedMessage receivedMessage) {
		return !receivedMessage.isUserMessage() || isRelayedCommunication;

	}

	public void run() {
		while (true) {
			try {
				Tracer.info(this, "Receiver runnable waiting for input message queue");
				ReceivedMessage message = inputMessageQueue.get();
				Tracer.info(this, "Receiver runnable received message from input message queue:"
						+ message);
				long delay = delayManager
						.calculateDelay(message.getTimeStamp());
				if (delay > 0 && isServerMessage(message)) {
					Tracer.info(this, "Receiver runnable about to sleep for: "
							+ delay);
					Thread.sleep(delay);
					Tracer.info(this, "Receiver runnable wakes up from  sleep of: "
							+ delay);

				}
				messsageQueuer.put(message);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean isRelayedCommunication() {
		return isRelayedCommunication;
	}

	@Override
	public void setIsRelayedCommunication(boolean newVal) {
		isRelayedCommunication = newVal;
	}

}
