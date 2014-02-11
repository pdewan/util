package util.undo;

import java.util.Vector;

public class HistoryUndoer implements util.undo.Undoer {
	Vector historyList = new Vector();
	int nextCommandIndex = 0;

	public void execute(AbstractCommand c) {
		// System.out.println("next command index " + nextCommandIndex);
		c.execute();
		historyList.insertElementAt(c, nextCommandIndex);
		nextCommandIndex++;
	}

	public boolean undo() {
		if (nextCommandIndex == 0)
			return (false);
		else {
			nextCommandIndex--;
			AbstractCommand c = (AbstractCommand) historyList
					.elementAt(nextCommandIndex);
			if (!c.isUndoable())
				return (false);
			else {
				c.undo();
				return (true);
			}
		}
	}

	public boolean redo() {
		if (nextCommandIndex == historyList.size())
			return (false);
		else {
			AbstractCommand c = (AbstractCommand) historyList
					.elementAt(nextCommandIndex);
			nextCommandIndex++;
			if (!c.isUndoable())
				return (false);
			else {
				c.execute();
				return (true);
			}
		}
	}

	private void writeObject(java.io.ObjectOutputStream out)
			throws java.io.IOException {
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in)
			throws java.io.IOException, ClassNotFoundException {
		in.defaultReadObject();
	}

}