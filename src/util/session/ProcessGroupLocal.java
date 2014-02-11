package util.session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface ProcessGroupLocal extends SessionMessageReceiverLocal {
	public void toAll(Object object, String clientName, MessageReceiver client,
			long timeStamp) throws RemoteException;

	public void toOthers(Object object, String clientName,
			MessageReceiver client, long timeStamp) throws RemoteException;

	public void toUser(Object userName, Object object, String clientName,
			MessageReceiver client, long timeStamp) throws RemoteException;

	public void toUsers(String[] userNames, Object object, String clientName,
			MessageReceiver client, long timeStamp) throws RemoteException;

	public Map<MessageReceiver, String> getClients() throws RemoteException;

	public void setSentMessageQueuer(ServerMessageFilter theServerMessageQueuer)
			throws RemoteException;

}
