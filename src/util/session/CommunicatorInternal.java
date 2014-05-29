package util.session;

import java.util.Map;

public interface CommunicatorInternal {
	public void setClients(Map<MessageReceiver, String> theClients);

	public Map<MessageReceiver, String> getClients();

	DelayManager getDelayManager();

	void setDelayManager(DelayManager theDelayManager);

	public boolean isRelayedCommunication();

	MessageReceiver getMessageReceiver();

}
