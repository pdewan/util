package util.models;

import java.util.Collection;
import java.util.List;

public interface ListenableVectorTester<ElementType> {

	public void addElement(ElementType c);

	public boolean addAll(Collection<? extends ElementType> elements);

	public void insertElementAt(ElementType element, int pos);

	// public boolean removeElement(ElementType c);

	public void removeElementAt(int pos);

	public void setElementAt(ElementType element, int pos);

	public int size();

	public void removeAllElements();

	public ElementType elementAt(int i);

	public void swap(int index1, int index2);

	public void swapPeer(int index1, int index2);

	public void move(int fromIndex, int toIndex);

	public void movePeer(int fromIndex, int toIndex);

	public void copy(int fromIndex, int toIndex);

	public void copyPeer(int fromIndex, int toIndex);

	public void replace(int fromIndex, int toIndex);

	public void replacePeer(int fromIndex, int toIndex);

	void addVectorMethodsListener(VectorMethodsListener vectorListener);

	void initPeer(List<ElementType> thePeer);

}