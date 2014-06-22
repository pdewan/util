package util.session;

import java.util.Map;
/*
 * Internal class with no remote methods as opposed to MessageReceiver
 * in which SentMessage captures all of the information
 */
public interface SessionMessageReceiverLocal  {
	;
	public void setClients(Map<ObjectReceiver, String> theClients) ;
	public void userJoined(ProcessGroupLocal processGroup, SerializedProcessGroups serializedProcessGroups, Map<ObjectReceiver, String> theClients,  String clientName, ObjectReceiver client, String theApplicationName, boolean newSession, boolean newApplication) ;
	public void userLeft(String theClientName, ObjectReceiver theClient, String theApplicationName) ;


}
