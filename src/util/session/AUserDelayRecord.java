package util.session;

public class AUserDelayRecord implements UserDelayRecord {
	String name;
	int delay;
	ObjectReceiver client;

	public AUserDelayRecord(ObjectReceiver theClient, String theName,
			int theDelay) {
		client = theClient;
		name = theName;
		delay = theDelay;
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
		int otherDelay = otherObject.getDelay();
		if (delay < otherDelay)
			return -1;
		else if (delay == otherDelay)
			return 0;
		else
			return +1;
	}

}
