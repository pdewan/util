package util.models;

import java.util.ArrayList;
import java.util.List;

public class AnAllOrSelectedList<ElementType> extends
		ArrayList<CheckedObject<ElementType>> implements
		AllOrSelectedList<ElementType> {
	List<CheckedObject<ElementType>> source;
	// List<CheckedObject<ElementType>> selected = new ArrayList();
	boolean showSelected = true;

	public AnAllOrSelectedList(List<CheckedObject<ElementType>> theSource) {
		source = theSource;
		setSelected(false);

	}

	public void setSelected(boolean newVal) {
		if (showSelected == newVal)
			return;
		showSelected = newVal;
		clear();
		for (CheckedObject<ElementType> checkedObject : source)
			if (checkedObject.getStatus() || !showSelected)
				add(checkedObject);
		// System.out.println("end of setSelected");

	}
	// public int size() {
	// if (showSelected)
	// return selected.size();
	// else
	// return source.size();
	//
	// }
	// public CheckedObject<ElementType> get(int i) {
	// if (showSelected)
	// return selected.get(i);
	// else
	// return source.get(i);
	//
	// }

}
