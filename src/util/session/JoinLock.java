package util.session;

public interface JoinLock {

	public abstract void waitFotJoin();

	public abstract void notifyJoined();

}