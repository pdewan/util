package util.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashcodeSet<ElemType> implements Iterable<Integer>{
//	Map<Integer, ElemType> hashcodeToElement = new HashMap();
	Set<Integer> hashCodes = new HashSet();

	public boolean add(ElemType elem) {
		Integer hashCode = (Integer) System.identityHashCode(elem);
//		hashcodeToElement.put(hashCode, elem);

		return hashCodes.add(hashCode);

	}
	
	public boolean contains(ElemType elem) {
		Integer hashCode = (Integer) System.identityHashCode(elem);
		return hashCodes.contains(hashCode);

	}
	
	public boolean isEmpty() {
		return hashCodes.isEmpty();
	}
	
	public Iterator<Integer> iterator() {
		return hashCodes.iterator();
	}
	

	public int size() {
		return hashCodes.size();
	}
	public void addAll (HashcodeSet newElements) {
		for (Object newElement:newElements) {
			hashCodes.add((Integer) newElement);			
		}
	}

}
