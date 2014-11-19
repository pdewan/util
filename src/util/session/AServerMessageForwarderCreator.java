package util.session;

public class AServerMessageForwarderCreator implements
		ServerMessageFilterCreator {

	@Override
	public ServerMessageFilter getServerMessageFilter() {
		return new AServerMessageForwarder();
	}

}
