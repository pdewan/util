package util.session;

import java.rmi.RemoteException;

public interface SessionManagerLocal {
	// why no leave, because it is invoked on process group?
	public void join(String theSessionName, String theApplicationName,
			String clientName, MessageReceiver client) throws RemoteException;

	Session createSession(String sessionName);

	void removeSession(String sessionName);

	Session getOrCreateSession(String sessionName) throws RemoteException;

	public void doJoin(String theSessionName, String theApplicationName,
			String clientName, MessageReceiver client);
	
	public void doLeave(String theSessionName, String theApplicationName,
			String clientName, MessageReceiver client);
//	public void register();


}
