package util.session;
/*
 * One kept per client
 */
public interface UserDelayRecord extends Comparable<UserDelayRecord> {
	public abstract MessageReceiver getClient();

	public abstract void setClient(MessageReceiver client);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract int getDelay();

	public abstract void setDelay(int delay);

}