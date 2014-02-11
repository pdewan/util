package util.session;

import java.util.Collection;
import java.util.Map;

public interface PeerMessageListener {
	void objectReceived(Object message, String userName);
}
