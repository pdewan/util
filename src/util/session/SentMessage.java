package util.session;

import java.io.Serializable;

public interface SentMessage extends Serializable, util.models.CorrectCloneable {

	public abstract SentMessageType getSentMessageType();

	public abstract void setSentMessageType(SentMessageType senttMessageType);

	public abstract Object[] getArgs();

	public abstract void setArgs(Object[] args);

	public abstract long getTimeStamp();

	public abstract void setTimeStamp(long timeStamp);

	public boolean isUserMessage();

	public Object getUserMessage();

	public void setUserMessage(Object theMessage);

	public String getSendingUser();

	public void setSendingUser(String theUser);

	public String getApplicationName();

	public void setApplicationName(String applicationName);

	public String getSessionName();

	public void setSessionName(String sessionName);

	public MessageReceiver getMessageReceiver();

	public void setMessageReceiver(MessageReceiver messageReceiver);

}