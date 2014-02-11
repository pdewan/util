package util.undo;

public interface ExecutedCommand {
	public void undo();

	public void redo();
}