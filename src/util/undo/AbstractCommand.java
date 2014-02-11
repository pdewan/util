package util.undo;

public abstract class AbstractCommand implements java.io.Serializable, Command {
	public abstract void execute();

	public abstract void undo();

	public abstract boolean isUndoable();
}