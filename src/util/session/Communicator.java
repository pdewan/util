package util.session;

import java.rmi.RemoteException;
import java.util.Map;

public interface Communicator {
	public void create(String serverHost, String theSessionName,
			String theApplicationName, String theClientName);

	public void join();

	public void asyncJoin();

	public void leave();

	public void toOthers(Object object);

	public void toAll(Object object);

	public void toUser(String userName, Object object);

	public void toUsers(String[] userName, Object object);

	public String getSessionName();

	public String getUserName();

	public int getMinimumDelayToServer();

	public void setMinimumDelayToServer(int theDelay);

	public int getMinimumDelayToPeer(String thePeer);

	public void setMinimumDelayToPeer(String thePeer, int theDelay);

	public int getDelayVariation();

	public void setDelayVariation(int theDelay);

	public void addReceivedMessageListener(ReceivedMessageListener listener);

	public void removeReceivedMessageListener(ReceivedMessageListener listener);

	public void addSessionMessageListener(SessionMessageListener listener);

	public void removeSessionMessageListener(SessionMessageListener listener);

	public void addPeerMessageListener(PeerMessageListener listener);

	public void removePeerMessageListener(PeerMessageListener listener);

	public String[] getOtherUserNames();

	public Map<MessageReceiver, String> getClients();

	public void setSentMessageQueuer(MessageFilter<SentMessage> messagQueuer);

}
