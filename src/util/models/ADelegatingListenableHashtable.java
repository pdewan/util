package util.models;

import java.util.Enumeration;
import java.util.Hashtable;

//import bus.uigen.HashtableChangeSupport;
//import bus.uigen.HashtableInterface;
//import bus.uigen.HashtableListenable;
//import bus.uigen.HashtableListener;

//public class ADelegatingListenableHashtable<KeyType, ElementType>   implements HashtableListenable, HashtableInterface<KeyType, ElementType>, java.io.Serializable {
public class ADelegatingListenableHashtable implements HashtableListenable,
		HashtableInterface, java.io.Serializable {
	// Hashtable<KeyType, ElementType> contents;
	Hashtable contents;
	transient HashtableChangeSupport hashtableChangeSupport;

	public ADelegatingListenableHashtable() {
		contents = new Hashtable();
		hashtableChangeSupport = new HashtableChangeSupport(this);
	}

	/*
	 * public void justATest(int key, int value){ put(new Integer(key), new
	 * Integer(value)); }
	 */
	// public ElementType get(Object key){
	public Object get(Object key) {
		// public ElementType get(KeyType key){
		// ElementType retVal = contents.get(key);
		Object retVal = contents.get(key);
		/*
		 * System.out.println ("Key: " + key + " Get:" + retVal); if (retVal ==
		 * null) System.out.println("Null retval for key" + key);
		 */

		return retVal;
		// return super.get(key);
	}

	// public ElementType put(KeyType key, ElementType value){
	public Object put(Object key, Object value) {
		// ElementType o = contents.put(key, value);
		Object o = contents.put(key, value);
		// System.out.println ("Key: " + key + " Value:" + value);
		hashtableChangeSupport.keyPut(key, value);
		return o;
	}

	// public ElementType remove(Object key){
	public Object remove(Object key) {
		// ElementType o = contents.remove(key);
		Object o = contents.remove(key);
		hashtableChangeSupport.keyRemoved(key);
		return o;
	}

	boolean keysEditable = true;

	public void setKeysEditable(boolean newVal) {
		keysEditable = newVal;
	}

	boolean elementsEditable = true;

	public void setValuesEditable(boolean newVal) {
		elementsEditable = newVal;
	}

	// public boolean isKeyEditable(KeyType key) {
	public boolean isKeyEditable(Object key) {
		return keysEditable;
	}

	// public boolean isValueEditable(KeyType key) {
	public boolean isValueEditable(Object key) {
		return elementsEditable;
	}

	Object userObject = null;

	public Object getUserObject() {
		return userObject;
	}

	public void setUserObject(Object newValue) {
		userObject = newValue;
	}

	Object expansionObject = null;

	public Object getExpansionObject() {
		return expansionObject;
	}

	public void setExpansionObject(Object newValue) {
		expansionObject = newValue;
	}

	public String toString() {
		return super.toString();
	}

	/*
	 * transient AListenableHashtable parent = null;
	 * 
	 * public AListenableHashtable getParent() { return parent; } void
	 * setParent(AListenableHashtable theParent) { parent = theParent; }
	 */

	public Enumeration elements() {
		return contents.elements();
	}

	public Enumeration keys() {
		return contents.keys();
	}

	public int size() {
		return contents.size();
	}

	public void addHashtableListener(HashtableListener hashtableListener) {
		hashtableChangeSupport.addHashtableListener(hashtableListener);
	}

	public void removeHashtableListener(HashtableListener hashtableListener) {
		hashtableChangeSupport.removeHashtableListener(hashtableListener);
	}

	public void initSerializedObject() {
		hashtableChangeSupport = new HashtableChangeSupport(this);
	}
}