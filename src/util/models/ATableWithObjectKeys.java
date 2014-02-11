package util.models;

import java.util.Vector;

public class ATableWithObjectKeys<KeyType, ElementType> {
	Vector<KeyType> keys = new Vector();
	Vector<ElementType> elements = new Vector();

	public ElementType put(KeyType key, ElementType element) {
		int index = keys.indexOf(key);
		if (index == -1) {
			keys.add(key);
			elements.add(element);
			return null;
		} else {
			ElementType oldVal = elements.elementAt(index);
			elements.setElementAt(element, index);
			return oldVal;
		}

	}

	public ElementType remove(KeyType key) {
		int index = keys.indexOf(key);
		if (index == -1)
			return null;
		ElementType oldVal = elements.elementAt(index);
		keys.remove(index);
		elements.remove(index);
		return oldVal;

	}

	public ElementType get(KeyType key) {
		int index = keys.indexOf(key);
		if (index == -1)
			return null;
		ElementType val = elements.elementAt(index);
		return val;
	}

}
