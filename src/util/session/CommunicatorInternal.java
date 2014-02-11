package util.session;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public interface CommunicatorInternal {
	public void setClients(Map<MessageReceiver, String> theClients);

	public Map<MessageReceiver, String> getClients();

	DelayManager getDelayManager();

	void setDelayManager(DelayManager theDelayManager);

	public boolean isRelayedCommunication();

	MessageReceiver getMessageReceiver();

}
