package util.session;


public interface PeerMessageListener {
	void objectReceived(Object message, String userName);
}
