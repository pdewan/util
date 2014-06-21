package util.session;

import java.rmi.RemoteException;
public interface ProcessGroup extends SessionMessageReceiver {
	public void newMessage(SentMessage theMessage) throws RemoteException;

}
