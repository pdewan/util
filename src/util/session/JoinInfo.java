package util.session;

import java.io.Serializable;
import java.util.Map;

public class JoinInfo implements Serializable {
	public Session session;
	public ProcessGroup processGroup;
	public Map<ObjectReceiver, String> clients;
	public SerializedProcessGroups serializedProcessGroups;
	public boolean newApplication;
	public boolean newSession;
}
