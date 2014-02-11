package util.undo;

public interface Undoer {

	public void execute(AbstractCommand c);

	public boolean undo();

	public boolean redo();
}