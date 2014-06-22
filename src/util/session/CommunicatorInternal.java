package util.session;

import java.util.Map;

public interface CommunicatorInternal {
	public void setClients(Map<ObjectReceiver, String> theClients);

	public Map<ObjectReceiver, String> getClients();

	DelayManager getDelayManager();

	void setDelayManager(DelayManager theDelayManager);

	public boolean isRelayedCommunication();

	ObjectReceiver getMessageReceiver();

}
