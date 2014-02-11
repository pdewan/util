package util.models;

//import bus.uigen.ChangeableVector;
//import bus.uigen.IndexedElementChecker;
//import bus.uigen.VectorInterface;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.Enumeration;

public class AVector<ElementType> implements VectorInterface<ElementType>,
		java.io.Serializable {
	Vector<ElementType> contents;
	Object userObject, temp;

	public AVector() {
		contents = new Vector();
	}

	public AVector(Vector<ElementType> v) {
		contents = v;
	}

	public void addElement(ElementType element) {
		contents.addElement(element);
	}

	public synchronized boolean removeElement(ElementType element) {
		return contents.removeElement(element);

	}

	public void insertElementAt(ElementType element, int pos) {
		contents.insertElementAt(element, pos);
	}

	public void removeElementAt(int pos) {
		contents.removeElementAt(pos);
	}

	public int size() {
		return contents.size();
	}

	public Enumeration elements() {
		return contents.elements();
	}

	public void setElementAt(ElementType element, int pos) {
		contents.setElementAt(element, pos);
	}

	public ElementType elementAt(int i) {
		return contents.elementAt(i);
	}

	public int indexOf(Object o) {
		return contents.indexOf(o);
	}

	public void removeAllElements() {
		while (size() > 0)
			this.removeElementAt(0);
	}

	@Override
	public AListenableVector getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getUserObject() {
		// TODO Auto-generated method stub
		return userObject;
	}
	
	@Override
	public Object getTemp() {
		// TODO Auto-generated method stub
		return temp;
	}

	@Override
	public void initSerializedObject() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isColumnEditable(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEditable(int index) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public void open(ElementType element) {
//		// TODO Auto-generated method stub
//
//	}

//	@Override
//	public boolean preAddElement(Object element) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean preElementAt(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean preInsertElementAt(ElementType element, int pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean preRemoveElement(ElementType element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean preRemoveElementAt(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean preSetElementAt(ElementType element, int pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsEditable(int index, boolean status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserObject(Object newValue) {
		userObject = newValue;
	}
	
	@Override
	public void setTemp(Object newValue) {
		temp = newValue;
	}

//	@Override
//	public IndexedElementChecker getIndexedElementChecker() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setIndexedElementChecker(IndexedElementChecker theChecker) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public boolean addAll(Collection<ElementType> elements) {
//		// TODO Auto-generated method stub
//		return contents.addAll(elements);
//	}

	@Override
	public void move(int fromIndex, int toIndex) {
		ElementType temp = contents.get(fromIndex);
		if (toIndex > fromIndex) {
			contents.insertElementAt(temp, toIndex);
			contents.remove(fromIndex);
		} else if (toIndex < fromIndex) {
			contents.insertElementAt(temp, toIndex);
			contents.remove(fromIndex + 1);
		}

	}

	@Override
	public void move(int fromIndex, List<ElementType> to, int toIndex) {
		ElementType temp = contents.get(fromIndex);
		if (to != null)
			to.add(toIndex, temp);
		contents.remove(fromIndex);

	}

	@Override
	public void swap(int index1, int index2) {
		ElementType temp = contents.get(index2);
		contents.setElementAt(contents.get(index1), index2);
		contents.setElementAt(temp, index1);

	}

	@Override
	public void swap(int index1, List<ElementType> other, int index2) {
		ElementType temp = contents.get(index2);
		// other has already been changed so no need to do so again
		if (other != null)
			other.set(index2, contents.get(index1)); // again should this be done?
		contents.setElementAt(temp, index1);

	}

	@Override
	public void copy(int fromIndex, List<ElementType> to, int toIndex) {
		ElementType temp = contents.get(fromIndex);
		if (to != null)
			to.add(toIndex, temp); // should this be done?, actually this operation will not be called

	}

	@Override
	public void copy(int fromIndex, int toIndex) {
		ElementType temp = contents.get(fromIndex);
		setElementAt(temp, toIndex);

//		contents.insertElementAt(temp, toIndex);

	}

	@Override
	public void replace(int fromIndex, List<ElementType> to, int toIndex) {
		ElementType element = contents.get(fromIndex);
		if (to != null)
			to.set(toIndex, element);
		removeElementAt(fromIndex);

	}

	@Override
	public void replace(int fromIndex, int toIndex) {
		ElementType element = contents.get(fromIndex);
		setElementAt(element, toIndex);
		removeElementAt(fromIndex);

	}

	public void clear() {
		contents.clear();
	}

	@Override
	public boolean addAll(Collection<? extends ElementType> elements) {
		// TODO Auto-generated method stub
		return contents.addAll(elements);
	}

	@Override
	public void setIndexedElementChecker(
			IndexedElementChecker<ElementType> theChecker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open(ElementType element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preAddElement(ElementType element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void copyToUserObject(int index) {
		userObject = contents.get(index);
		
	}
	
	@Override
	public void copyToTemp(int index) {
		temp = contents.get(index);
		
	}

	@Override
	public void copyFromUserObject(int index) {
		contents.set(index, (ElementType) userObject);
		
	}
	
	@Override
	public void copyFromTemp(int index) {
		contents.set(index, (ElementType) temp);
		
	}

	@Override
	public IndexedElementChecker<ElementType> getIndexedElementChecker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void copyTempToUserObject(Object aSource) {
		userObject = temp;
	}

	@Override
	public void copyUserObjectToTemp(Object aSource) {
		temp = userObject;
		
	}

	
	@Override
	public void copyAndInsert(int fromIndex, int toIndex) {
//		if (fromIndex == toIndex)
//		return;
		ElementType temp = elementAt(fromIndex);
//		ElementType temp = get(fromIndex);

//		if (toIndex > fromIndex) {
			insertElementAt(temp, toIndex);
//			super.remove(fromIndex);
////			insertElementAt(temp, toIndex);
////			remove(fromIndex);
//		} else if (toIndex < fromIndex) {
//			super.insertElementAt(temp, toIndex);
//			super.remove(fromIndex + 1);
////			insertElementAt(temp, toIndex);
////			remove(fromIndex + 1);
//		}

	}
	@Override
	public void copyAndInsert(int fromIndex, List<ElementType> to, int toIndex) {
//		ElementType temp = super.get(fromIndex);
//		if (to instanceof ListenableVector) {
//			((ListenableVector) to).unObservableAdd(toIndex, temp);
//		} else
//		     to.add(toIndex, temp);
////		super.remove(fromIndex);
//		vectorChangeSupport.elementCopiedAndInserted(fromIndex, to, toIndex);

	}


	Integer pointer = null;

	@Override
	public void setPointer(Integer newVal) {
		pointer = newVal;
	}

	@Override
	public Integer getPointer() {
		// TODO Auto-generated method stub
		return pointer;
	}
	
	Integer pointer2 = null;

	@Override
	public void setPointer2(Integer newVal) {
		pointer2 = newVal;
	}

	@Override
	public Integer getPointer2() {
		// TODO Auto-generated method stub
		return pointer2;
	}

	@Override
	public void setParent(ListenableVector theParent) {
		// TODO Auto-generated method stub
		
	}

}