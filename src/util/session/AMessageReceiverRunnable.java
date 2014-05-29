package util.session;

import util.models.BoundedBuffer;
import util.trace.Tracer;
import util.trace.session.MessageRetrievedFromReceivingQueueNew;
import util.trace.session.ReceivedMessageDelayedNew;

public class AMessageReceiverRunnable implements MessageReceiverRunnable {
	BoundedBuffer<ReceivedMessage> inputMessageQueue;
	DelayManager delayManager;
	MessageFilter<ReceivedMessage> messsageQueuer;
	boolean isRelayedCommunication;
	// receives enqueued RMI calls and also delays them. So the delay is at the receiver rather than sending end
	// it is certainly at the sending site, maybe even at the receiving site
	// looks like that server messages are delayed not the client messages
	// I guess that makes server not have to wait
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
				MessageRetrievedFromReceivingQueueNew.newCase(ACommunicatorSelector.getProcessName(), message, this);

				Tracer.info(this, "Receiver runnable received message from input message queue:"
						+ message);
				long delay = delayManager
						.calculateDelay(message.getTimeStamp());
				if (delay > 0 && isServerMessage(message)) {
					Tracer.info(this, "Receiver runnable about to sleep for: "
							+ delay);
					ReceivedMessageDelayedNew.newCase(ACommunicatorSelector.getProcessName(), message, delay, this);
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
