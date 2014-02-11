package util.models;

import java.util.List;

public interface CheckedList<ElementType> {

	public List<ElementType> checkedElements();

	public void computeCheckedElements();

	public int size();

	public CheckedObject<ElementType> get(int pos);

	public void set(int pos, CheckedObject<ElementType> element);

}