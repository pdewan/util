package util.session;

import java.rmi.RemoteException;
public interface ProcessGroup extends MessageReceiver {
	public void newMessage(SentMessage theMessage) throws RemoteException;

}
