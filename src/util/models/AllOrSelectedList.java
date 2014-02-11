package util.models;

import java.util.List;

public interface AllOrSelectedList<ElementType> extends
		List<CheckedObject<ElementType>> {

	public void setSelected(boolean newVal);
	// public int size();
	// public CheckedObject<ElementType> get(int i);

}