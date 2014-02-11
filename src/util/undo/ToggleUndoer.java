package util.undo;

import java.util.Vector;

public class ToggleUndoer implements util.undo.Undoer {
	AbstractCommand lastCommand = null;
	boolean lastCommandWasUndone = false;

	public void execute(AbstractCommand c) {
		c.execute();
		lastCommand = c;
		lastCommandWasUndone = false;
	}

	public boolean undo() {
		if ((lastCommand == null) || !lastCommand.isUndoable())
			return (false);
		else if (lastCommandWasUndone)
			lastCommand.execute();
		else
			lastCommand.undo();
		lastCommandWasUndone = !lastCommandWasUndone;
		return (true);
	}

	public boolean redo() {
		if (lastCommandWasUndone) {
			lastCommand.execute();
			return (true);
		} else
			return (false);
	}

}