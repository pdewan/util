package util.session;

import java.util.Map;
// local calls that are marshalled into messages
public interface Communicator {
	public static final String DIRECT = "P2P";
	public static final String RELAYED = "Relayed";
	public void create(String serverHost, String theSessionName,
			String theApplicationName, String theClientName);

	public void join();

	public void asyncJoin();

	public void leave();

	public void toOthers(Object object);

	public void toAll(Object object);

	public void toClient(String userName, Object object);

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

	public void addSessionMessageListener(SessionMessageListener aListener);

	public void removeSessionMessageListener(SessionMessageListener listener);

	public void addPeerMessageListener(PeerMessageListener listener);

	public void removePeerMessageListener(PeerMessageListener listener);

	public String[] getUserNames();

	public Map<ObjectReceiver, String> getClients();

	public void setSentMessageQueuer(MessageFilter<SentMessage> messagQueuer);

	void toNonCallers(Object object);

	void toCaller(Object object);

}
