package util.session;

public class AJoinLock implements JoinLock {
	boolean hasJoined;

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.JoinLock#waitFotJoin()
	 */
	public synchronized void waitFotJoin() {
		try {
			if (!hasJoined)
				wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.JoinLock#notifyJoined()
	 */
	public synchronized void notifyJoined() {
		hasJoined = true;
		notify();
	}

}
