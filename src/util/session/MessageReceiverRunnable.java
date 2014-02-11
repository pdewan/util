package util.session;

public interface MessageReceiverRunnable extends Runnable {
	public void setIsRelayedCommunication(boolean newVal);

	public boolean isRelayedCommunication();

}
