package util.models;

import java.util.Enumeration;

public interface HashtableInterface<KeyType, ElementType> {
	public ElementType get(Object key);
//	public ElementType get(KeyType key);


	public ElementType put(KeyType key, ElementType value);

	public ElementType remove(Object key);

//	public ElementType remove(KeyType key);

	public Enumeration elements();

	public Enumeration keys();

	public int size();
	/*
	 * public Object get(Object key); public Object put(Object key, Object
	 * value); public Object remove(Object key); public Enumeration elements();
	 * public Enumeration keys(); public int size();
	 */
}
