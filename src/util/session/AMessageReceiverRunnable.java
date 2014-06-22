package util.session;

import util.models.ABoundedBuffer;
import util.trace.Tracer;
import util.trace.session.MessageGivenToFilter;
import util.trace.session.MessageRetrievedFromQueue;
import util.trace.session.ReceivedMessageDelayed;

public class AMessageReceiverRunnable implements MessageReceiverRunnable {
	ABoundedBuffer<ReceivedMessage> inputMessageQueue;
	DelayManager delayManager;
	MessageFilter<ReceivedMessage> messsageFilter;
	boolean isRelayedCommunication;
	// receives enqueued RMI calls and also delays them. So the delay is at the receiver rather than sending end
	// it is certainly at the sending site, maybe even at the receiving site
	// looks like that server messages are delayed not the client messages
	// I guess that makes server not have to wait
	public AMessageReceiverRunnable(
			ABoundedBuffer<ReceivedMessage> theMessageQueue,
			DelayManager theDelayManager,
			MessageFilter<ReceivedMessage> theMessageProcessor) {
		inputMessageQueue = theMessageQueue;
		delayManager = theDelayManager;
		messsageFilter = theMessageProcessor;
	}

	boolean isServerMessage(ReceivedMessage receivedMessage) {
		return !receivedMessage.isUserMessage() || isRelayedCommunication;

	}

	public void run() {
		while (true) {
			try {
				Tracer.info(this, "Receiver runnable waiting for input message queue");
				ReceivedMessage message = inputMessageQueue.get(); // this may be a join message
				MessageRetrievedFromQueue.newCase(
						CommunicatorSelector.getProcessName(), 
						message, 
						message.getClientName(),
						inputMessageQueue.getName(),
						this);

				Tracer.info(this, "Receiver runnable received message from input message queue:"
						+ message);
				long delay = delayManager
						.calculateDelay(message.getTimeStamp());
				if (delay > 0 && isServerMessage(message)) {
					Tracer.info(this, "Receiver runnable about to sleep for: "
							+ delay);
					ReceivedMessageDelayed.newCase(CommunicatorSelector.getProcessName(), message, message.getClientName(), delay, this);
					Thread.sleep(delay);
					Tracer.info(this, "Receiver runnable wakes up from  sleep of: "
							+ delay);

				}
				MessageGivenToFilter.newCase(
						CommunicatorSelector.getProcessName(), 
						message, 
						message.getClientName(),
						this);
				messsageFilter.put(message);

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
