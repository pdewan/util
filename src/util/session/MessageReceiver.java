package util.session;

import java.rmi.Remote;
import java.rmi.RemoteException;
// actually multicast message receiver, will change later
public interface MessageReceiver extends Remote {
	;
//	public void setClients(Map<MessageReceiver, String> theClients) throws RemoteException;
//	public void userJoined(ProcessGroup processGroup, SerializedProcessGroups serializedProcessGroups, Map<MessageReceiver, String> theClients,  String clientName, MessageReceiver client, String theApplicationName, boolean newSession, boolean newApplication) throws RemoteException;
//	public void userLeft(String theClientName, MessageReceiver theClient, String theApplicationName) throws RemoteException;
	public void newMessage(ReceivedMessage theReceivedMessage) throws RemoteException;

//	MessageDispatcher getDelayedMessageReceiver();
//
//	void setDelayedMessageReceiver(MessageDispatcher delayedMessageReceiver);



}
