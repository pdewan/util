package util.session;

import java.io.Serializable;
import java.util.Map;
/*
 * Created by AReceivedMessageCreator
 * at the local end it seems
 */
//public interface ReceivedMessage extends Serializable {
public interface ReceivedMessage extends GroupMessage {


	public abstract ReceivedMessageType getReceivedMessageType();

	public abstract void setReceivedMessageType(
			ReceivedMessageType receivedMessageType);

	public abstract String getClientName();

	public abstract void setClientName(String clientName);

//	public abstract String getApplicationName();
//
//	public abstract void setApplicationName(String applicationName);
//
//	public abstract Object getUserMessage();
//
//	public abstract void setUserMessage(Object newObject);
//
//	public abstract long getTimeStamp();
//
//	public abstract void setTimeStamp(long timeStamp);

	public abstract ObjectReceiver getClient();

	public abstract void setClient(ObjectReceiver client);

	public abstract Map<ObjectReceiver, String> getClients();

	public abstract void setClients(Map<ObjectReceiver, String> clients);

	public abstract boolean isNewSession();

	public abstract void setNewSession(boolean newSession);

	public abstract boolean isNewApplication();

	public abstract void setNewApplication(boolean newApplication);

//	public abstract String getSessionName();
//
//	public abstract void setSessionName(String sessionName);
//
//	public boolean isUserMessage();

	public SerializedProcessGroups getSerializedProcessGroups();

	public void setSerializedProcessGroups(
			SerializedProcessGroups serializedProcessGroups);

	public ProcessGroup getProcessGroup();

	public void setProcessGroup(ProcessGroup processGroup);

}