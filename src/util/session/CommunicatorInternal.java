package util.session;

import java.util.Map;

public interface CommunicatorInternal {
	public void setClients(Map<ObjectReceiver, String> theClients);

	public Map<ObjectReceiver, String> getClients();

	DelayManager getDelayManager();

	void setDelayManager(DelayManager theDelayManager);

	public boolean isRelayedCommunication();
	
	public boolean isOrderedDelivery();
	public void setOrderedDelivery(boolean newVal);

	ObjectReceiver getMessageReceiver();
	MessageReceiverRunnable getMessageReceiverRunnable();
	void setMessageReceiverRunnable(MessageReceiverRunnable aMessageReceiverRunnable);
	MessageFilter<ReceivedMessage> getReceivedMessageFilter();
	void setReceivedMessageFilter(MessageFilter<ReceivedMessage> newVal);


}
