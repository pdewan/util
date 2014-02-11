package util.models;

import java.util.Vector;

public class AnOldCheckedVector<ElementType> {
	Vector<CheckedObject<ElementType>> checkedVector = new Vector();
	Vector vector;
	Vector names;

	public AnOldCheckedVector(Vector<ElementType> theVector) {
		vector = theVector;
		names = theVector;
		for (int i = 0; i < theVector.size(); i++) {
			checkedVector.addElement(new ACheckedObject<ElementType>(theVector
					.elementAt(i)));
		}
	}

	public AnOldCheckedVector(Vector theVector, Vector theNames) {
		vector = theVector;
		names = theNames;
		for (int i = 0; i < theVector.size(); i++) {
			checkedVector.addElement(new ACheckedObject(theNames.elementAt(i)));
		}
	}

	public Object getCheckedList() {
		return checkedVector;
	}

	public Vector checkedElements() {
		Vector retVal = new Vector();
		for (int i = 0; i < checkedVector.size(); i++) {
			if (((ACheckedObject) checkedVector.elementAt(i)).getStatus())
				retVal.addElement(vector.elementAt(i));
		}
		return retVal;
	}

	public Vector checkedNames() {
		Vector retVal = new Vector();
		for (int i = 0; i < checkedVector.size(); i++) {
			if (((ACheckedObject) checkedVector.elementAt(i)).getStatus())
				retVal.addElement(names.elementAt(i));
		}
		return retVal;
	}

	public Vector checkedElementsInPlace() {
		Vector retVal = new Vector();
		for (int i = 0; i < checkedVector.size(); i++) {
			if (((ACheckedObject) checkedVector.elementAt(i)).getStatus())
				retVal.addElement(vector.elementAt(i));
			else
				retVal.addElement(null);
		}
		return retVal;
	}

	public Vector checkedNamesInPlace() {
		Vector retVal = new Vector();
		for (int i = 0; i < checkedVector.size(); i++) {
			if (((ACheckedObject) checkedVector.elementAt(i)).getStatus())
				retVal.addElement(names.elementAt(i));
			else
				retVal.addElement(null);
		}
		return retVal;
	}

}
