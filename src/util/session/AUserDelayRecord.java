package util.session;

public class AUserDelayRecord implements UserDelayRecord {
	String name;
	int delay;
	long deliveryTime;
	

	ReceivedMessage receivedMessage;
	

	ObjectReceiver client;

	public AUserDelayRecord(ObjectReceiver theClient, String theName,
			int theDelay, ReceivedMessage aReceivedMessage) {
		client = theClient;
		name = theName;
		delay = theDelay;
		receivedMessage = aReceivedMessage;
		deliveryTime = System.currentTimeMillis() + delay;
	}

	public ObjectReceiver getClient() {
		return client;
	}

	public void setClient(ObjectReceiver client) {
		this.client = client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.DelayUserRecord#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.DelayUserRecord#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.DelayUserRecord#getDelay()
	 */
	public int getDelay() {
		return delay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.DelayUserRecord#setDelay(java.lang.String)
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	@Override
	public int compareTo(UserDelayRecord otherObject) {
//		int otherDelay = otherObject.getDelay();
		long otherDeliveryTime = otherObject.getDeliveryTime();

//		if (delay < otherDelay)
//			return -1;
//		else if (delay == otherDelay)
//			return 0;
//		else
//			return +1;
		if (deliveryTime < otherDeliveryTime)
			return -1;
		else if (delay == otherDeliveryTime)
			return 0;
		else
			return +1;
	}
	
	public long getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	@Override
	public ReceivedMessage getReceivedMessage() {
		return receivedMessage;
	}
	@Override
	public void setReceivedMessage(ReceivedMessage receivedMessage) {
		this.receivedMessage = receivedMessage;
	}

}
