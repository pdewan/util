package util.session;

import java.io.Serializable;
import java.util.Map;

public interface ReceivedMessage extends Serializable {

	public abstract ReceivedMessageType getReceivedMessageType();

	public abstract void setReceivedMessageType(
			ReceivedMessageType receivedMessageType);

	public abstract String getClientName();

	public abstract void setClientName(String clientName);

	public abstract String getApplicationName();

	public abstract void setApplicationName(String applicationName);

	public abstract Object getUserMessage();

	public abstract void setUserMessage(Object newObject);

	public abstract long getTimeStamp();

	public abstract void setTimeStamp(long timeStamp);

	public abstract MessageReceiver getClient();

	public abstract void setClient(MessageReceiver client);

	public abstract Map<MessageReceiver, String> getClients();

	public abstract void setClients(Map<MessageReceiver, String> clients);

	public abstract boolean isNewSession();

	public abstract void setNewSession(boolean newSession);

	public abstract boolean isNewApplication();

	public abstract void setNewApplication(boolean newApplication);

	public abstract String getSessionName();

	public abstract void setSessionName(String sessionName);

	public boolean isUserMessage();

	public SerializedProcessGroups getSerializedProcessGroups();

	public void setSerializedProcessGroups(
			SerializedProcessGroups serializedProcessGroups);

	public ProcessGroup getProcessGroup();

	public void setProcessGroup(ProcessGroup processGroup);

}