package util.session;

public class AUserDelayRecord implements UserDelayRecord {
	String name;
	long delay;
	long deliveryTime;
	

	ReceivedMessage receivedMessage;
	

	ObjectReceiver client;

	public AUserDelayRecord(ObjectReceiver theClient, String theName,
			long theDelay, ReceivedMessage aReceivedMessage) {
		client = theClient;
		name = theName;
		delay = theDelay;
		setReceivedMessage(aReceivedMessage);
		setDeliveryTime (System.currentTimeMillis() + delay);
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
	public long getDelay() {
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
//		System.out.println("Delivery time set to:" + deliveryTime + " TS:" + receivedMessage.getTimeStamp());
		this.deliveryTime = deliveryTime;
	}
	@Override
	public ReceivedMessage getReceivedMessage() {
		return receivedMessage;
	}
	@Override
	public void setReceivedMessage(ReceivedMessage newVal) {
		if (newVal == null) {
			System.out.println("Null message set:" + newVal);
		}
		this.receivedMessage = newVal;
	}

}
