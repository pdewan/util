package util.session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface ProcessGroup extends SessionMessageReceiver {
	public void newMessage(SentMessage theMessage) throws RemoteException;

}
