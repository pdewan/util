package util.session;

public interface CommunicatorCreator {
	public Communicator getCommunicator(String serverHost,
			String theSessionName, String userName, String theApplicationName);

	public Communicator getCommunicator(String[] args);
}
