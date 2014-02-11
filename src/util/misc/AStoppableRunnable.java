package util.misc;

public abstract class AStoppableRunnable implements StoppableRunnable {
	protected boolean execute = true;

	public void stop() {
		execute = false;
	}
}
