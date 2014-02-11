package util.session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

// forget trying to allow Sessions to be accessed directly, makes it hard to use sockets
public interface Session /* extends Remote */{
	public void join(String clientName, MessageReceiver client,
			String theApplicationName);

	public void leave(String clientName, MessageReceiver client,
			String theApplicationName);

	public ProcessGroup getProcessGroup(String theApplicationName);

	public ProcessGroupLocal getProcessGroupLocal(String theApplicationName);

	public String[] getUserNames(MessageReceiver theClient);

	public Map<MessageReceiver, String> getClients(MessageReceiver theClient);

}
