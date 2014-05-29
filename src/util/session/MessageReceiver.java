package util.session;

import java.rmi.RemoteException;

public interface MessageReceiver extends SessionMessageReceiver {
	public void newObject(String clientName, MessageReceiver client,
			Object value) throws RemoteException;

}
