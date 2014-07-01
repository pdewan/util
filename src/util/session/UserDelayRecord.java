package util.session;
/*
 * One kept per client
 */
public interface UserDelayRecord extends Comparable<UserDelayRecord> {
	public abstract ObjectReceiver getClient();

	public abstract void setClient(ObjectReceiver client);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract int getDelay();

	public abstract void setDelay(int delay);
	public long getDeliveryTime() ;

	public void setDeliveryTime(long deliveryTime) ;

	ReceivedMessage getReceivedMessage();

	void setReceivedMessage(ReceivedMessage receivedMessage);

}