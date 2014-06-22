package util.session;

import java.util.Collection;
import java.util.Map;

// forget trying to allow Sessions to be accessed directly, makes it hard to use sockets
public interface Session /* extends Remote */{
	public void join(String clientName, ObjectReceiver client,
			String theApplicationName);

	public void leave(String clientName, ObjectReceiver client,
			String theApplicationName);

	public ProcessGroup getProcessGroup(String theApplicationName);

	public ProcessGroupLocal getProcessGroupLocal(String theApplicationName);

	public String[] getUserNames(ObjectReceiver theClient);

	public Map<ObjectReceiver, String> getClients();

	Collection<String> getClientNames();

}
