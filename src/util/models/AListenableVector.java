package util.models;

/*
 import bus.uigen.VectorChangeSupport;
 import bus.uigen.VectorListenable;
 import bus.uigen.VectorListener;
 import bus.uigen.VectorMethodsListener;
 import bus.uigen.ChangeableVector;
 */
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import util.annotations.Visible;
import util.trace.Tracer;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.VECTOR_PATTERN)
@util.annotations.IsCompositeShape(true)

public class AListenableVector<ElementType> extends
		java.util.Vector<ElementType> implements ListenableVector<ElementType>, Remote {
	// VectorListenable, ChangeableVector<ElementType>, List<ElementType>,
	// java.io.Serializable {

	// Vector contents = new Vector();
	/*
	 * public void justATest(){ addElement(new Integer(1)); }
	 */
	Integer lastObservableGetIndex = null;
	
	String name;
	
	@Visible(false)
	public String getName() {
		if (name != null)
		return name;
		else return toString();
	}
	
	public void setName(String newVal) {
		name = newVal;
	}

	public AListenableVector(Vector initialVector) {
		super(initialVector);
	}

	public AListenableVector() {
		super();
	}

	public synchronized void addElement(ElementType element) {
		super.addElement(element);
		maybeSetParentLinkOfDescendent(element);
		if (size() == 1)
			initFirstRow();
		vectorChangeSupport.elementAdded(element);
	}

	public synchronized boolean add(ElementType element) {
//		System.out.println("Add started");
		boolean retVal = super.add(element);
		/*
		 * if (size() == 1) initFirstRow();
		 */
		maybeSetParentLinkOfDescendent(element);
		vectorChangeSupport.elementAdded(element);
//		System.out.println("Add ended");

		return retVal;
	}

	@Override
	public synchronized boolean addAll(
			Collection<? extends ElementType> appendedCollection) {
		boolean retVal = super.addAll(appendedCollection);
		vectorChangeSupport.elementsAdded(appendedCollection);
		return retVal;

	}
	// at some point define a remove all event for efficiency
	// remove in reverse order for efficiency
	@Override
	public synchronized boolean removeAll(
			Collection<?> appendedCollection) {
		boolean retVal = true;
		List appendedList;
		if (appendedCollection instanceof List) {
			appendedList = (List) appendedCollection;
		} else {
			appendedList = new ArrayList(appendedCollection);
//		for (Object element:appendedCollection) {
//			retVal &= remove(element);
//		}
		}
		for (int i = appendedList.size() - 1; i >= 0; i--) {
		
//		for (Object element:appendedCollection) {
//			retVal &= remove(element);
			retVal &= remove(appendedList.get(i));
		}
		return retVal;
//		boolean retVal = super.addAll(appendedCollection);
//		vectorChangeSupport.elementsAdded(appendedCollection);
//		return retVal;

	}


	// //int i;
	//
	// public synchronized boolean removeElement(ElementType element) {
	// return super.removeElement(element);
	// }

	public synchronized boolean removeElement(Object element) {
		boolean retVal = super.removeElement(element);
		// this is going to call removeElementAt() - so no need to announce
		// change
		// vectorChangeSupport.elementRemoved(element);
		return retVal;
	}

	/*
	 * public boolean contains (Object element) { return
	 * super.contains(element); }
	 */

	public synchronized void insertElementAt(ElementType element, int pos) {
		super.insertElementAt(element, pos);
		maybeSetParentLinkOfDescendent(element);
		/*
		 * if (size() == 1) initFirstRow();
		 */
		vectorChangeSupport.elementInserted(element, pos);
	}
	
	public int internalSize() {
		return size();
	}
	
	public ElementType internalElementAt(int index) {
		return super.elementAt(index);
	}
	
	public int internalIndexOf(ElementType element) {
		return super.indexOf(element);
	}

	@Override
	public void open(ElementType element) {
		System.out.println("Open called in AListenableVector");
	}

	public void removeElementAt(int pos) {
		super.removeElementAt(pos);
		vectorChangeSupport.elementRemoved(pos);
	}

	public ElementType remove(int pos) {
		ElementType retVal = super.remove(pos);
		vectorChangeSupport.elementRemoved(pos);
		return retVal;
	}

	@Override
	public void clear() {
		super.clear();
		vectorChangeSupport.elementsCleared();
	}

	/*
	 * public int size() { return super.size(); }
	 */
	/*
	 * public Enumeration elements() { return super.elements(); } public Object
	 * elementAt(int i) { return super.elementAt(i); } public int indexOf(Object
	 * o) { return super.indexOf(o); }
	 * 
//	 * public void removeAllElements() { while (size() > 0)
//	 * this.removeElementAt(0); }
//	 */
//	@Override
//	public void removeAllElements() {
//		clear();
//	}
	@Override
	@util.annotations.Validate("elementAt")
	public boolean preElementAt(int index) {
		if (checker == null)
			return true;
		else
			return checker.check(index, elementAt(index));
	}

	public void setElementAt(ElementType element, int pos) {
		// System.out.println("set element At: support =" +
		// vectorChangeSupport);
		ElementType oldElement = elementAt(pos);
		super.setElementAt(element, pos);
		maybeSetParentLinkOfDescendent(element);
		// System.out.println("After set element");
		// if (element != elementAt(pos))
		if (element != oldElement)
			vectorChangeSupport.elementChanged(element, pos);
	}
	@Override
	public void unObservableSet(int pos, ElementType element) {
		ElementType oldElement = elementAt(pos);

		super.set(pos, element);
		maybeSetParentLinkOfDescendent(element);
		if (element != oldElement)
			vectorChangeSupport.unobservableElementChanged(element, pos);

		
	}

	public ElementType set(int pos, ElementType element) {
		// System.out.println("set element At: support =" +
		// vectorChangeSupport);
		ElementType retVal = super.set(pos, element);
		maybeSetParentLinkOfDescendent(element);
		// System.out.println("After set element");
		if (element != retVal)
			vectorChangeSupport.elementChanged(element, pos);
		return retVal;
	}
	
//	public ElementType unOservableSet(int pos, ElementType element) {
//		// System.out.println("set element At: support =" +
//		// vectorChangeSupport);
//		ElementType retVal = super.set(pos, element);
//		maybeSetParentLinkOfDescendent(element);
//		// System.out.println("After set element");
////		if (element != retVal)
////			vectorChangeSupport.elementChanged(element, pos);
//		return retVal;
//	}

	Object userObject = null;

	@Override
	public Object getUserObject() {
		return userObject;
	}

	@Override
	public void setUserObject(Object newValue) {
		if (userObject == newValue) return;
		userObject = newValue;
		vectorChangeSupport.userObjectChanged(newValue);
	}
	
	Object temp = null;

	@Override
	public Object getTemp() {
		return temp;
	}

	@Override
	public void setTemp(Object newValue) {
		if (temp == newValue) return;
		temp = newValue;
		vectorChangeSupport.tempChanged(newValue);
	}

	transient ListenableVector parent = null;

	@Override
	public ListenableVector getParent() {
		return parent;
	}

	public void setParent(ListenableVector theParent) {
		parent = theParent;
	}

	protected void maybeSetParentLinkOfDescendent(Object element) {
		if (element instanceof AListenableVector) {
			AListenableVector childVector = (AListenableVector) element;
			childVector.setParent(this);
		}

	}

	protected void setParentLinksOfDescendents() {
		for (int i = 0; i < this.size(); i++) {
			Object element = this.elementAt(i);
			maybeSetParentLinkOfDescendent(element);
			/*
			 * if (element instanceof AListenableVector) { AListenableVector
			 * childVector = (AListenableVector) element;
			 * childVector.setParent(this); }
			 */
		}
	}

	Vector<Boolean> childrenEditable;

	@Override
	public void setIsEditable(int index, boolean status) {
		if (childrenEditable == null)
			childrenEditable = new Vector();
		int numberOfNewElements = index + 1 - childrenEditable.size();
		for (int i = 0; i < numberOfNewElements; i++)
			childrenEditable.addElement(true);
		childrenEditable.setElementAt(status, index);
	}

	@Override
	public boolean isEditable(int index) {
		if (childrenEditable != null && childrenEditable.size() > index)
			return childrenEditable.elementAt(index);
		return true;
	}

	@Override
	public boolean isColumnEditable(int index) {
		ElementType child = elementAt(index);
		if (!(child instanceof AListenableVector)) {
			Tracer.error("isColumnEditable called on non-nested Vector");
			return true;
		}
		return ((AListenableVector) child).isEditable(0);

	}
	@Visible(false)
	public ListenableVector getRoot() {
		if (this.getParent() == null) {
			return this;
		} else {
			return getParent().getRoot();
		}
	}

	protected AListenableVector getFirstRow() {
		if (size() == 0) {
			Tracer.error("column operation called on empty matrix");
			return null;
		}
		ElementType child = elementAt(0);
		if (!(child instanceof AListenableVector)) {
			// Message.error("column operation called on non-nested Vector");
			return null;
		}
		return (AListenableVector) child;
	}

	protected void initFirstRow() {
		AListenableVector firstRow = getFirstRow();
		if (firstRow == null)
			return;
		for (int i = 0; i < columnEditable.size(); i++) {
			firstRow.setIsEditable(i, columnEditable.elementAt(i));
		}
	}

	Vector<Boolean> columnEditable = new Vector();

	protected void setIsColumnEditable(int index, boolean status) {

		int numberOfNewElements = index + 1 - columnEditable.size();
		for (int i = 0; i < numberOfNewElements; i++)
			columnEditable.addElement(true);
		columnEditable.setElementAt(status, index);

		AListenableVector child = getFirstRow();
		if (child == null)
			return;
		child.setIsEditable(index, status);

	}

	protected boolean getIsColumnEditable(int index) {
		AListenableVector child = getFirstRow();
		if (child == null)
			return true;
		return child.isEditable(index);
	}

	// public boolean validateRemoveElement(ElementType element) {
	@Override
	@util.annotations.Validate("removeElement")
	public boolean preRemoveElement(ElementType element) {
		int index = indexOf(element);
		if (index < 0)
			return false;
		return isEditable(index);
	}

	// public boolean validateRemoveElementAt(int index) {
	@Override
	@util.annotations.Validate("removeElementAt")
	public boolean preRemoveElementAt(int index) {
		return true;
	}

	// public boolean validateAddElement(ElementType element) {
	@Override
	@util.annotations.Validate("addElement")
	public boolean preAddElement(ElementType element) {
//		int index = indexOf(element);
//		if (index < 0)
//			return false;
		return isEditable(size());
	}

	// public boolean validateSetElementAt(ElementType element, int pos) {
	@Override
	@util.annotations.Validate("setElementAt")
	public boolean preSetElementAt(ElementType element, int pos) {
		return true;
	}

	// public boolean validateInsertElementAt (ElementType element, int pos) {
	@Override
	@util.annotations.Validate("insertElementAt")
	public boolean preInsertElementAt(ElementType element, int pos) {
		return true;
	}

	transient protected VectorChangeSupport<ElementType> vectorChangeSupport = new VectorChangeSupport(
			this);

	@Override
	@util.annotations.ObserverRegisterer(util.annotations.ObserverTypes.VECTOR_LISTENER)
	public void addVectorListener(VectorListener vectorListener) {

		try {
			vectorChangeSupport.addVectorListener(vectorListener);
		} catch (Exception e) {
			if (vectorChangeSupport == null) {
				initSerializedObject();
				vectorChangeSupport.addVectorListener(vectorListener);
				Tracer.warning("Define a setter for:" + this);
			} else
				e.printStackTrace();
		}
	}
	@Override
	public void addVectorListeners(Collection<VectorListener> someListeners) {
		for (VectorListener aListener:someListeners) {
			addVectorListener(aListener);
		}
		
	}

	@Override
	public void removeVectorListener(VectorListener vectorListener) {
		vectorChangeSupport.removeVectorListener(vectorListener);
	}

	@Override
	public void addVectorMethodsListener(VectorMethodsListener vectorListener) {
		vectorChangeSupport.addVectorMethodsListener(vectorListener);
	}
	@Override
	public void addVectorMethodsListeners( Collection<? extends VectorMethodsListener> someListeners) {
		for (VectorMethodsListener aListener:someListeners) {
			addVectorMethodsListener(aListener);
		}
		
	}

	@Override
	public void removeVectorMethodsListener(VectorMethodsListener vectorListener) {
		vectorChangeSupport.removeVectorMethodsListener(vectorListener);
	}

	@Override
	public void initSerializedObject() {
		// vectorChangeSupport = new VectorChangeSupport(this);
		setVectorChangeSupport(createVectorChangeSupport());
		setParentLinksOfDescendents();
	}

	protected VectorChangeSupport<ElementType> createVectorChangeSupport() {
		return new VectorChangeSupport(this);
	}

	@Override
	public void setVectorChangeSupport(VectorChangeSupport<ElementType> newVal) {
		vectorChangeSupport = newVal;
	}

	@Visible(false)
	public VectorChangeSupport<ElementType> getVectorChangeSupport() {
		return vectorChangeSupport;
	}

	IndexedElementChecker<ElementType> checker;

	@Override
	public IndexedElementChecker<ElementType> getIndexedElementChecker() {
		return checker;
	}

	@Override
	public void setIndexedElementChecker(
			IndexedElementChecker<ElementType> theChecker) {
		checker = theChecker;

	}

	protected boolean equals(Object o1, Object o2) {
		if (o1 == o2)
			return true;
		if (o1 == null || o2 == null)
			return false;
		return o1.equals(o2);
	}

	@Override
	public boolean equals(Object remoteVal) {
		if (remoteVal == this)
			return true;
		if (remoteVal == null)
			return false;
		if (!(remoteVal instanceof ListenableVector))
			return false;
		ListenableVector remoteVector = ((ListenableVector) remoteVal);
		return super.equals(remoteVector)
				&& remoteVector.toString().equals(toString())
				&& equals(remoteVector.getUserObject(), getUserObject())
				&& remoteVector.getParent() == getParent();
	}

	@Override
	public void move(int fromIndex, int toIndex) {
		if (fromIndex == toIndex)
		return;
		ElementType temp = super.get(fromIndex);
//		ElementType temp = get(fromIndex);

		if (toIndex > fromIndex) {
			super.insertElementAt(temp, toIndex);
			super.remove(fromIndex);
//			insertElementAt(temp, toIndex);
//			remove(fromIndex);
		} else if (toIndex < fromIndex) {
			super.insertElementAt(temp, toIndex);
			super.remove(fromIndex + 1);
//			insertElementAt(temp, toIndex);
//			remove(fromIndex + 1);
		}
		vectorChangeSupport.elementMoved(fromIndex, toIndex);

	}
	
	@Override
	public void copyAndInsert(int fromIndex, int toIndex) {
//		if (fromIndex == toIndex)
//		return;
		ElementType temp = super.get(fromIndex);
//		ElementType temp = get(fromIndex);

//		if (toIndex > fromIndex) {
			super.insertElementAt(temp, toIndex);
//			super.remove(fromIndex);
////			insertElementAt(temp, toIndex);
////			remove(fromIndex);
//		} else if (toIndex < fromIndex) {
//			super.insertElementAt(temp, toIndex);
//			super.remove(fromIndex + 1);
////			insertElementAt(temp, toIndex);
////			remove(fromIndex + 1);
//		}
		vectorChangeSupport.elementCopiedAndInserted(fromIndex, toIndex);

	}
	
	public void unObservableAdd(int aToIndex, ElementType anElement) {
		super.insertElementAt(anElement, aToIndex);
		maybeSetParentLinkOfDescendent(anElement);
		if (size() == 1)
			initFirstRow();
		vectorChangeSupport.unobservableElementInserted(anElement, aToIndex);

	}

	@Override
	public void move(int fromIndex, List<ElementType> to, int toIndex) {
		ElementType temp = super.get(fromIndex);
		if (to instanceof ListenableVector) {
			((ListenableVector) to).unObservableAdd(toIndex, temp);
		} else
		     to.add(toIndex, temp);
		super.remove(fromIndex);
		vectorChangeSupport.elementMoved(fromIndex, to, toIndex);

	}
	
	@Override
	public void copyAndInsert(int fromIndex, List<ElementType> to, int toIndex) {
		ElementType temp = super.get(fromIndex);
		if (to instanceof ListenableVector) {
			((ListenableVector) to).unObservableAdd(toIndex, temp);
		} else
		     to.add(toIndex, temp);
//		super.remove(fromIndex);
		vectorChangeSupport.elementCopiedAndInserted(fromIndex, to, toIndex);

	}
	
	
	
	@Override
	public void copyToUserObject(int index) {
		userObject = get(index);
		vectorChangeSupport.elementCopiedToUserObject(index);
	}
	@Override
	public void copyFromTemp(int index) {
		ElementType element = (ElementType) getTemp() ;
		ElementType retVal = super.set(index,  element);
		maybeSetParentLinkOfDescendent(element);
		// System.out.println("After set element");
		if (element != retVal)
			vectorChangeSupport.elementCopiedFromTemp(index);
//		set(index, (ElementType) getUserObject());
	}
	
	@Override
	public void copyToTemp(int index) {
		temp = get(index);
		vectorChangeSupport.elementCopiedToTemp(index);
	}
	@Override
	public void copyFromUserObject(int index) {
		ElementType element = (ElementType) getUserObject() ;
		ElementType retVal = super.set(index,  element);
		maybeSetParentLinkOfDescendent(element);
		// System.out.println("After set element");
		if (element != retVal)
			vectorChangeSupport.elementCopiedFromUserObject(index);
//		set(index, (ElementType) getUserObject());
	}
	
//	@Override
//	public void copyFromTemp(int index) {
//		ElementType element = (ElementType) getTemp() ;
//		ElementType retVal = super.set(index,  element);
//		maybeSetParentLinkOfDescendent(element);
//		// System.out.println("After set element");
//		if (element != retVal)
//			vectorChangeSupport.elementCopiedFromTemp(index);
////		set(index, (ElementType) getUserObject());
//	}

	@Override
	public void swap(int index1, int index2) {
		ElementType temp = super.get(index2);
		super.setElementAt(get(index1), index2);
		super.setElementAt(temp, index1);
		vectorChangeSupport.elementSwapped(index1, index2);

	}

	@Override
	public void swap(int index1, List<ElementType> other, int index2) {
		ElementType temp = other.get(index2);
		if (other instanceof ListenableVector) {
			((ListenableVector) other).unObservableSet(index2, get(index1));
		} else {
		other.set(index2, get(index1));
		}
		super.setElementAt(temp, index1);
		vectorChangeSupport.elementSwapped(index1, other, index2);

	}

	@Override
	public void copy(int fromIndex, List<ElementType> to, int toIndex) {
		ElementType temp = super.get(fromIndex);
		if (to instanceof ListenableVector) {
			((ListenableVector) to).unObservableSet(toIndex, temp);
		} else
		to.set(toIndex, temp);
		vectorChangeSupport.elementCopied(fromIndex, to, toIndex);

	}

	@Override
	public void copy(int fromIndex, int toIndex) {
		ElementType temp = super.get(fromIndex);
		super.setElementAt(temp, toIndex);
		vectorChangeSupport.elementCopied(fromIndex, toIndex);

	}
	@SuppressWarnings("unchecked")
	@Override
	public synchronized ElementType observableGet(Integer pos) {
		lastObservableGetIndex = pos;
		if (pos != null) {
		ElementType retVal = super.get(pos);
		

		vectorChangeSupport.elementRead(retVal, pos);
		return retVal; 
		} else {
			vectorChangeSupport.elementRead(null, null);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized Object observableGetUserObject() {
		

//		vectorChangeSupport.elementRead(null, -1);
		vectorChangeSupport.userObjectRead();

		lastObservableGetIndex = -1;
		return userObject;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized Object observableGetTemp() {
		

//		vectorChangeSupport.elementRead(null, -1);
		vectorChangeSupport.tempRead();

		lastObservableGetIndex = -2;
		return temp;
	}
	
	@Override
	public synchronized Integer lastObservableGetIndex() {
		return lastObservableGetIndex;
	}

	@Override
	public void replace(int fromIndex, List<ElementType> to, int toIndex) {
		ElementType element = get(fromIndex);
		to.set(toIndex, element);
		super.removeElementAt(fromIndex);
		vectorChangeSupport.elementReplaced(fromIndex, to, toIndex);

	}

	@Override
	public void replace(int fromIndex, int toIndex) {
		ElementType element = get(fromIndex);
		setElementAt(element, toIndex);
		super.removeElementAt(fromIndex);
		vectorChangeSupport.elementReplaced(fromIndex, toIndex);

	}

	@Override
	public ListenableVector<ElementType> deepClone() {

		// TODO Auto-generated method stub
		ListenableVector<ElementType> retVal = new AListenableVector();
		for (ElementType element : this) {
			if (!(element instanceof CorrectCloneable))
				retVal.add(element);
			else
				retVal.add((ElementType) ((CorrectCloneable) element).clone());
		}
		return retVal;
	}

	public ListenableVector<ElementType> clone() {
		return (ListenableVector<ElementType>) super.clone();

	}

	

	@Override
	public void copyTempToUserObject(Object aSource) {
		userObject = ((ListenableVector) aSource).getTemp();
		vectorChangeSupport.tempCopiedToUserObject(aSource);
		
	}

	@Override
	public void copyUserObjectToTemp(Object aSource) {
		temp = userObject;
		vectorChangeSupport.userObjectCopiedToTemp(aSource);

		
	}

	Integer pointer = null;

	@Override
	public void setPointer(Integer newVal) {
		pointer = newVal;
		vectorChangeSupport.pointerChanged(newVal);
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
		vectorChangeSupport.pointer2Changed(newVal);
	}

	@Override
	public Integer getPointer2() {
		// TODO Auto-generated method stub
		return pointer2;
	}

	@Override
	public void userOperation(int aTargetIndex, Object anOperaton) {
		vectorChangeSupport.userOperationOccured(aTargetIndex, anOperaton);
	}


	//
	// public void select (Object argument) {
	// System.out.println("Selected object:" + argument);
	// }
	// public void select (Object parent, Object argument) {
	// System.out.println("Selected object:" + argument + "from parent:" +
	// parent );
	// }

	/*
	 * public AListenableVector getParent() { return new AListenableVector(); }
	 */
}
