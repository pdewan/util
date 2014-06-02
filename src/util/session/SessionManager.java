package util.session;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionManager extends Remote{
	public static String SESSION_MANAGER_NAME = "Session Manager";

	void newMessage(SentMessage theMessage) throws RemoteException;
}
