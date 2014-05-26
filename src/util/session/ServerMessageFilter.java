package util.session;

public interface ServerMessageFilter extends MessageFilter<SentMessage> {
	public void userJoined(String userName);
	public void userLeft(String userName);

}
