/*
 * Created on Jul 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.models;

import java.util.Vector;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface DynamicMatrix {
	public void set(int rowNum, int colNum, Object element);

	public Object get(int rowNum, int colNum);

	public int numRows();

	public int numCols(int rowNum);

	public int numCols();

	public int lastFilledColumn(int rowNum);

	public int firstFilledColumn();

	public int lastFilledColumn();

	public int lastFilledRow(int colNum);

	public int lastFilledRow();

	public int firstFilledRow();

	public boolean setOrInsertNewRowNorth(int rowNum, int colNum, Object element);

	public boolean setOrInsertNewRowSouth(int rowNum, int colNum, Object element);

	public boolean setOrInsertNewColumnWest(int rowNum, int colNum,
			Object element);

	public boolean setOrInsertNewColumnEast(int rowNum, int colNum,
			Object element);

	public void insertEmptyRow(int rowNum);

	public boolean isRowEmpty(int rowNum);

	public boolean isColumnEmpty(int colNum);

	public boolean setOrInsertEmptyRow(int rowNum);

	public int firstFilledRow(int rowNum);

	public int firstFilledColumn(int colNum);

	public int nextEmptyRow(int rowNum);

	public int nextEmptyColumn(int colNum);

	public int nextEmptyRows(int rowNum, int numRows);

	public int nextEmptyColumns(int colNum, int numCols);

	public void removeRow(int rowNum);

	public void removeColumn(int colNum);

	public int maxCols(int startRow, int stopRow);

	void clear();

	boolean remove(Object item);
}