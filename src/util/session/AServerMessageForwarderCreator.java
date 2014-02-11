package util.session;

public class AServerMessageForwarderCreator implements
		ServerMessageFilterCreator {

	@Override
	public ServerMessageFilter getMessageQueuer() {
		return new AServerMessageForwarder();
	}

}
