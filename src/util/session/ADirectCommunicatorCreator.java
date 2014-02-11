package util.session;

public class ADirectCommunicatorCreator implements CommunicatorCreator {
	Communicator messageSender;

	@Override
	public Communicator getCommunicator(String serverHost,
			String theSessionName, String userName, String theApplicationName) {
		if (messageSender == null)
			messageSender = new ADirectCommunicator(serverHost, theSessionName,
					theApplicationName, userName);
		return messageSender;
	}

	public Communicator getCommunicator(String[] args) {
		if (messageSender == null)
			messageSender = new ADirectCommunicator(args);
		return messageSender;
	}
}
