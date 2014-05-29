package util.session;

import java.util.Map;

public interface SessionMessageReceiverLocal  {
	;
	public void setClients(Map<MessageReceiver, String> theClients) ;
	public void userJoined(ProcessGroupLocal processGroup, SerializedProcessGroups serializedProcessGroups, Map<MessageReceiver, String> theClients,  String clientName, MessageReceiver client, String theApplicationName, boolean newSession, boolean newApplication) ;
	public void userLeft(String theClientName, MessageReceiver theClient, String theApplicationName) ;


}
