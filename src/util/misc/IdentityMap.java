package util.misc;

import java.util.Collection;
import java.util.Set;

public interface IdentityMap<KeyType, ElemType> {

	ElemType put(KeyType key, ElemType elem);

	ElemType get(KeyType key);

	ElemType remove(KeyType key);

	Collection<ElemType> values();

	Set<Integer> keyCodeSet();

	ElemType get(Integer key);

	Set<KeyType> keySet();
	
	 Collection<KeyType> keyCollection();

	int size();
	void clear();

}