package util.session;

import java.rmi.RemoteException;
/*
 this should probbaly be called ClientMessageReceiver, odd that the super
 class has an extra adjective
 looks as if a MessageReceiver gets a decomposed ReceivedMessage
 the specific send user call and the send all use newObject
 while the send all call use newMessage
 sendOthers seems a more working version
 does not seem as if the user is aware of this newMessage call
 eac client is represented by a MessageReceiver
 * */
 
public interface MessageReceiver extends SessionMessageReceiver {
	public void newObject(String clientName, MessageReceiver client,
			Object value) throws RemoteException;

}
