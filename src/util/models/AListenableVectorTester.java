package util.models;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class AListenableVectorTester<ElementType> implements
		ListenableVectorTester<ElementType> {
	ListenableVector<ElementType> contents = new AListenableVector<ElementType>();
	List<ElementType> peer;

	@Override
	public void initPeer(List<ElementType> thePeer) {
		peer = thePeer;
	}

	public void addElement(ElementType c) {
		contents.addElement(c);
	}

	public boolean addAll(Collection<? extends ElementType> elements) {
		return contents.addAll(elements);
	}

	public void insertElementAt(ElementType element, int pos) {
		contents.insertElementAt(element, pos);
	}

	// public boolean removeElement(ElementType c) {
	// return contents.removeElement(c);
	// }
	public void removeElementAt(int pos) {
		contents.removeElementAt(pos);
	}

	public void setElementAt(ElementType element, int pos) {
		contents.setElementAt(element, pos);
	}

	public int size() {
		return contents.size();
	}

	public void removeAllElements() {
		contents.removeAllElements();
	}

	public ElementType elementAt(int i) {
		return contents.elementAt(i);
	}

	// void setParent(AListenableVector theParent);

	public void swap(int index1, int index2) {
		contents.swap(index1, index2);
	}

	// public void swap (int index1, List<ElementType> other, int index2) {
	// contents.swap(index1, other, index2);
	// }
	public void swapPeer(int index1, int index2) {
		contents.swap(index1, peer, index2);
	}

	public void move(int fromIndex, int toIndex) {
		contents.move(fromIndex, toIndex);
	}

	// public void move (int fromIndex,List<ElementType> to, int toIndex) {
	// contents.move(fromIndex, to, toIndex);
	// }
	public void movePeer(int fromIndex, int toIndex) {
		contents.move(fromIndex, peer, toIndex);
	}

	public void copy(int fromIndex, int toIndex) {
		contents.copy(fromIndex, toIndex);
	}

	public void copyPeer(int fromIndex, int toIndex) {
		contents.copy(fromIndex, peer, toIndex);
	}

	public void replace(int fromIndex, int toIndex) {
		contents.replace(fromIndex, toIndex);
	}

	public void replacePeer(int fromIndex, int toIndex) {
		contents.replace(fromIndex, peer, toIndex);
	}

	@Override
	public void addVectorMethodsListener(VectorMethodsListener vectorListener) {
		contents.addVectorMethodsListener(vectorListener);
	}

}
