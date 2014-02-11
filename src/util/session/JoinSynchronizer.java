package util.session;

public interface JoinSynchronizer {

	public abstract void notifyJoined();

	public abstract void waitFotJoin();

}