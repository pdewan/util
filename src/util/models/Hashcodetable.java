package util.models;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class Hashcodetable<KeyType, ElemType> {
	Hashtable<Integer, ElemType> hashcodeToElement = new Hashtable();
	Hashtable<Integer, KeyType> hashcodeToKey = new Hashtable();

	public ElemType put(KeyType key, ElemType elem) {
		hashcodeToKey.put((Integer) System.identityHashCode(key), key);

		return hashcodeToElement.put((Integer) System.identityHashCode(key), elem);

	}
	
	public boolean isEmpty() {
		return hashcodeToElement.isEmpty();
	}
	
	public boolean containsKey(KeyType key) {
		return hashcodeToElement.containsKey((Integer) System.identityHashCode(key));
	}

	public ElemType get(KeyType key) {
		return hashcodeToElement.get((Integer) System.identityHashCode(key));
	}

	public ElemType remove(KeyType key) {
		Integer hashcode = System.identityHashCode(key);
		hashcodeToKey.remove(hashcode);
//		return hashcodeToElement.remove((Integer) System.identityHashCode(key));
		return hashcodeToElement.remove(hashcode);

	}

	public Enumeration<ElemType> elements() {
		return hashcodeToElement.elements();

	}

	public Enumeration<Integer> keyCodes() {
		return hashcodeToElement.keys();
	}
	
	public Set<Integer> keyCodeSet() {
		return hashcodeToElement.keySet();
	}
	
	public Collection<KeyType> keyValues() {
		return hashcodeToKey.values();
	}
	
	public Collection<ElemType> values() {
		return hashcodeToElement.values();
	}

	public ElemType get(Integer key) {
		return hashcodeToElement.get(key);
	}
	
	public void clearExcept(HashcodeSet aPresevedSet) {
		for (Integer key:hashcodeToElement.keySet()) {
			if (!aPresevedSet.contains(key)) {
				hashcodeToElement.remove(key);
				hashcodeToKey.remove(key);				
			}
		}
	}

	public void clear() {
		hashcodeToKey.clear();
		hashcodeToElement.clear();
	}

	public int size() {
		return hashcodeToElement.size();
	}

}
