package util.models;

import java.util.List;
import java.util.Vector;

public class ACheckedList<ElementType> implements CheckedList<ElementType> {
	List<CheckedObject<ElementType>> checkedVector = new Vector();
	List<ElementType> checkedObjects = new Vector();

	public List<ElementType> checkedElements() {
		return checkedObjects;
	}

	public void computeCheckedElements() {
		checkedObjects.clear();
		// Vector retVal = new Vector();
		for (int i = 0; i < checkedVector.size(); i++) {
			if ((checkedVector.get(i)).getStatus())
				checkedObjects.add(checkedObjects.get(i));
		}
	}

	public int size() {
		return checkedVector.size();
	}

	public CheckedObject<ElementType> get(int pos) {
		return checkedVector.get(pos);
	}

	public void set(int pos, CheckedObject<ElementType> element) {
		checkedVector.set(pos, element);
	}

}
