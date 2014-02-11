package util.undo;

public interface Command {

	public void execute();

	public void undo();

	public boolean isUndoable();

}