package util.session;

public interface ServerMessageFilter extends MessageFilter<SentMessage> {
	public void userJoined(String aSessionName, String anApplicationName, String userName);
	public void userLeft(String aSessionName, String anApplicationName, String userName);

}
