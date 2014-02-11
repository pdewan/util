package util.models;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface AlmostList<ElementType> extends Collection<ElementType>{
	

	public boolean removeElement(Object o) ;

	

	public Object get(int index) ;

	public Object set(int index, Object element) ;

	public void add(int index, Object element);

	public Object removeElement(int index) ;

	public int lastIndexOf(Object o) ;

	public ListIterator listIterator() ;

	public ListIterator listIterator(int index) ;

	public List subList(int fromIndex, int toIndex);

}
