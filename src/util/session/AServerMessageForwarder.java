package util.session;


public class AServerMessageForwarder extends AMessageForwarder<SentMessage>
		implements ServerMessageFilter {

	@Override
	public void userJoined(String aSessionName, String anApplicationName, String userName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userLeft(String aSessionName, String anApplicationName,String userName) {
		// TODO Auto-generated method stub

	}

}
