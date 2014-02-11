package util.misc;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashIdentityMap<KeyType, ElemType> implements
		IdentityMap<KeyType, ElemType> {
	Map<Integer, ElemType> contents = new HashMap();
	Map<Integer, KeyType> keys = new HashMap();

	public ElemType put(KeyType key, ElemType elem) {
		int code = System.identityHashCode(key);
		keys.put(code, key);
		return contents.put(code, elem);
	}

	public ElemType get(KeyType key) {
		return contents.get(System.identityHashCode(key));
	}

	public ElemType remove(KeyType key) {
		return contents.remove(System.identityHashCode(key));
	}

	public Collection<ElemType> values() {
		return contents.values();

	}

	public Set<Integer> keyCodeSet() {
		return contents.keySet();
	}

	public ElemType get(Integer key) {
		return contents.get(key);
	}

	public Set<KeyType> keySet() {
		return new HashSet(keys.values());
	}
	public Collection<KeyType> keyCollection() {
		return keys.values();
	}

	public int size() {
		return contents.size();
	}

	@Override
	public void clear() {
		contents.clear();
		keys.clear();
		
	}

}
