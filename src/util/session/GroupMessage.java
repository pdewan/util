package util.session;

import java.io.Serializable;

public interface GroupMessage extends Serializable {
	
	public abstract long getTimeStamp();

	public abstract void setTimeStamp(long timeStamp);

	public boolean isUserMessage();

	public Object getUserMessage();

	public void setUserMessage(Object theMessage);

	
	public String getApplicationName();

	public void setApplicationName(String applicationName);

	public String getSessionName();

	public void setSessionName(String sessionName);

}
