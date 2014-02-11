package util.session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface MessageReceiver extends SessionMessageReceiver {
	public void newObject(String clientName, MessageReceiver client,
			Object value) throws RemoteException;

}
