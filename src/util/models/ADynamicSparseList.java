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
public class ADynamicSparseList<ElementType> {
	Vector<ElementType> contents = new Vector();

	public void set(int index, ElementType element) {
		if (index < 0)
			return;
		createCell(index);
		contents.set(index, element);

	}

	public boolean contains(ElementType element) {
		return contents.contains(element);
	}

	public void add(ElementType element) {
		contents.add(element);
	}

	public void remove(int index) {
		contents.remove(index);
	}

	public void remove(ElementType element) {
		contents.remove(element);
	}

	public void removeAllElements() {
		contents.removeAllElements();
	}

	public boolean setOrInsertNewElementAbove(int index, ElementType element) {
		if (index < 0) {
			add(element);
			return true;
			// return false ;
		}
		if (get(index) == null) {
			set(index, element);
			return false;
		} else {
			contents.insertElementAt(element, index);
			return true;
		}
	}

	public boolean setOrInsertNewElementBelow(int index, ElementType element) {
		if (index < 0)
			return false;
		if (get(index) == null) {
			set(index, element);
			return false;
		} else {
			contents.insertElementAt(element, index + 1);
			return true;
		}
	}

	public void insertElementAt(ElementType element, int index) {
		contents.insertElementAt(element, index);
	}

	public ElementType get(int index) {
		if (index < 0)
			return null;
		createCell(index);
		return contents.get(index);

	}

	void createCell(int index) {
		int size = contents.size();
		for (int i = size; i <= index; i++)
			contents.addElement(null);

	}

	public int size() {
		return contents.size();
	}

	public int numFilledElements() {
		int retVal = 0;
		for (int i = 0; i < contents.size(); i++)
			if (contents.elementAt(i) != null)
				retVal++;
		return retVal;

	}

	// assume actualIndex is filled
	public int filledIndexFor(int actualIndex) {
		int retVal = 0;
		for (int i = 0; i < actualIndex; i++)
			if (contents.elementAt(i) != null)
				retVal++;
		return retVal;
	}

	public int firstFilledSlot(int from) {
		for (int i = from; i < contents.size(); i++)
			if (contents.elementAt(i) != null)
				return i;
		return -1;

	}

	public int firstEmptySlot(int from) {
		for (int i = from; i < contents.size(); i++)
			if (contents.elementAt(i) == null)
				return i;
		return contents.size();

	}

	public int firstFilledSlot() {
		return firstFilledSlot(0);
	}

	public int lastFilledSlot() {

		for (int i = size() - 1; i >= 0; i--) {
			// if (childComponents[row][i] == null) continue;
			if (contents.elementAt(i) == null)
				continue;
			else
				return i;

		}
		return -1;
	}

}
