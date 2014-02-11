/*
 * Created on Jul 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.models;

import java.util.Vector;

//import bus.uigen.oadapters.uiObjectAdapter;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ADynamicMatrix implements DynamicMatrix {
	Vector matrix = new Vector();

	public ADynamicMatrix() {
		matrix = new Vector();
	}

	public ADynamicMatrix(int numRows, int numCols) {
		matrix = new Vector(numRows);
		for (int i = 0; i < numCols; i++) {
			Vector col = new Vector(numCols);
			matrix.add(col);
		}
	}

	public void set(int rowNum, int colNum, Object element) {
		if (rowNum < 0 || colNum < 0)
			return;
		createCell(rowNum, colNum);
		Vector row = (Vector) matrix.elementAt(rowNum);
		row.setElementAt(element, colNum);

	}

	public void removeRow(int rowNum) {
		if (rowNum >= matrix.size())
			return;
		matrix.remove(rowNum);
	}

	public boolean remove(Object item) {
		for (int i = 0; i < matrix.size(); i++) {
			Vector row = (Vector) matrix.elementAt(i);
			if (row.remove(item))
				return true;
		}
		return false;

	}

	public void removeColumn(int colNum) {
		if (colNum > this.maxColNum)
			return;
		for (int i = 0; i < matrix.size(); i++) {
			Vector row = (Vector) matrix.elementAt(i);
			if (row.size() <= colNum)
				continue;
			row.remove(colNum);
		}
	}

	public void insertEmptyRow(int rowNum) {
		matrix.insertElementAt(new Vector(), rowNum);
	}

	public boolean isRowEmpty(int rowNum) {
		return lastFilledColumn(rowNum) < 0;
	}

	public boolean isColumnEmpty(int colNum) {
		return lastFilledRow(colNum) < 0;
	}

	public int firstFilledRow(int rowNum) {
		int retVal = rowNum;
		while (isRowEmpty(retVal))
			retVal++;
		return retVal;
	}

	public int firstFilledRow() {
		return firstFilledRow(0);
	}

	public int nextEmptyRow(int rowNum) {
		int retVal = rowNum;
		while (!isRowEmpty(retVal))
			retVal++;
		return retVal;
	}

	public int nextEmptyColumn(int colNum) {
		int retVal = colNum;
		while (!isColumnEmpty(retVal))
			retVal++;
		return retVal;
	}

	public int firstFilledColumn(int colNum) {
		int retVal = colNum;
		while (isColumnEmpty(retVal))
			retVal++;
		return retVal;
	}

	public int firstFilledColumn() {
		return firstFilledColumn(0);
	}

	public int nextEmptyRows(int rowNum, int numRows) {
		int nextRow = nextEmptyRow(rowNum);
		while (true) {
			boolean found = true;
			int i = 0;
			for (; i < numRows - 1; i++) {
				if (!isRowEmpty(nextRow + i + 1)) {
					found = false;
					break;
				}
			}
			if (found)
				return nextRow;
			nextRow = nextEmptyRow(nextRow + i + 1);
		}
	}

	public int nextEmptyColumns(int colNum, int numCols) {
		int nextCol = nextEmptyColumn(colNum);
		while (true) {
			boolean found = true;
			int i = 0;
			for (; i < numCols - 1; i++) {
				if (!isColumnEmpty(nextCol + i + 1)) {
					found = false;
					break;
				}
			}
			if (found)
				return nextCol;
			nextCol = nextEmptyRow(nextCol + i + 1);
		}
	}

	public boolean setOrInsertEmptyRow(int rowNum) {
		if (isRowEmpty(rowNum))
			return false;
		else {
			insertEmptyRow(rowNum);
			return true;
		}
	}

	public boolean setOrInsertNewRowNorth(int rowNum, int colNum, Object element) {
		if (rowNum < 0 || colNum < 0)
			return false;
		if (get(rowNum, colNum) == null) {
			set(rowNum, colNum, element);
			return false;
		} else {
			matrix.insertElementAt(new Vector(), rowNum);
			set(rowNum, colNum, element);
			return true;
		}

	}

	public boolean setOrInsertNewRowSouth(int rowNum, int colNum, Object element) {
		if (rowNum < 0 || colNum < 0)
			return false;
		if (element == null)
			return false;
		Object existing = get(rowNum, colNum);
		if (element.equals(existing))
			return false;
		// if (get(rowNum, colNum) == null) {
		if (existing == null) {
			set(rowNum, colNum, element);
			return false;
		} else {
			matrix.insertElementAt(new Vector(), rowNum + 1);
			set(rowNum + 1, colNum, element);
			return true;
		}

	}

	public boolean setOrInsertNewColumnWest(int rowNum, int colNum,
			Object element) {
		if (rowNum < 0 || colNum < 0)
			return false;
		if (get(rowNum, colNum) == null) {
			set(rowNum, colNum, element);
			return false;
		} else {
			Vector row = (Vector) matrix.elementAt(rowNum);
			row.insertElementAt(element, colNum);
			maxColNum = Math.max(maxColNum, row.size() - 1);
			return true;
		}

	}

	public boolean setOrInsertNewColumnEast(int rowNum, int colNum,
			Object element) {
		if (rowNum < 0 || colNum < 0)
			return false;
		if (get(rowNum, colNum) == null) {
			set(rowNum, colNum, element);
			return false;
		} else {
			Vector row = (Vector) matrix.elementAt(rowNum);
			row.insertElementAt(element, colNum + 1);
			maxColNum = Math.max(maxColNum, row.size() - 1);
			return true;
		}
	}

	int maxColNum = 0;

	public Object get(int rowNum, int colNum) {
		if (rowNum < 0 || colNum < 0)
			return null;
		createCell(rowNum, colNum);
		Vector row = (Vector) matrix.elementAt(rowNum);

		return row.elementAt(colNum);

	}

	void createCell(int rowNum, int colNum) {
		int matrixSize = matrix.size();
		for (int i = matrixSize; i <= rowNum; i++)
			matrix.addElement(new Vector());
		Vector row = (Vector) matrix.elementAt(rowNum);
		int rowSize = row.size();
		for (int i = rowSize; i <= colNum; i++)
			row.addElement(null);
		maxColNum = Math.max(maxColNum, colNum);

	}

	public int numRows() {
		return matrix.size();
	}

	public int numCols(int rowNum) {
		if (rowNum >= matrix.size())
			return 0;
		Vector row = (Vector) matrix.elementAt(rowNum);
		return row.size();

	}

	public int numCols() {
		return maxColNum + 1;
	}

	public int maxCols(int startRow, int stopRow) {
		int retVal = 0;
		for (int i = startRow; i <= stopRow; i++)
			retVal = Math.max(retVal, 1 + lastFilledColumn(i));
		return retVal;

	}

	public int lastFilledColumn(int rowNum) {
		if (rowNum >= matrix.size())
			return -1;
		// createCell (rowNum, 0);
		Object o = matrix.elementAt(rowNum);
		if (o == null)
			return -1;
		Vector row = (Vector) o;
		for (int i = row.size() - 1; i >= 0; i--) {
			// if (childComponents[row][i] == null) continue;
			if (row.elementAt(i) == null)
				continue;
			else
				return i;

		}
		return -1;
	}

	public int lastFilledColumn() {
		int retVal = 0;
		for (int i = firstFilledRow(); i < numRows(); i++) {
			retVal = Math.max(retVal, lastFilledColumn(i));
		}
		return retVal;
	}

	public int lastFilledRow(int colNum) {

		for (int i = numRows() - 1; i >= 0; i--) {
			// if (childComponents[row][i] == null) continue;
			if (get(i, colNum) == null)
				continue;
			else
				return i;

		}
		return -1;
	}

	public int lastFilledRow() {
		int retVal = 0;
		for (int i = firstFilledColumn(); i < lastFilledColumn(); i++) {
			retVal = Math.max(retVal, lastFilledRow(i));
		}
		return retVal;
	}

	public void print() {
		System.out.println("---");
		for (int i = 0; i < numRows(); i++) {
			System.out.println();
			int j = 0;
			while (true) {
				if (j == numCols(i))
					break;
				System.out.print(" " + get(i, j));
				j++;
			}
		}
	}

	@Override
	public void clear() {
		matrix.clear();
	}

}
