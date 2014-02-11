package util.models;

import java.util.*;

//import bus.uigen.HashtableChangeSupport;
//import bus.uigen.HashtableListener;
//import bus.uigen.ListenableTable;
@util.annotations.StructurePattern(util.annotations.StructurePatternNames.MAP_PATTERN)
public class AListenableHashMap<KeyType, ElementType> extends
		java.util.HashMap<KeyType, ElementType> implements
		ListenableTable<KeyType, ElementType> {
	// HashtableListenable, java.io.Serializable {
	// Hashtable contents;
	transient HashtableChangeSupport hashtableChangeSupport;

	public AListenableHashMap() {
		// contents = new Hashtable();
		hashtableChangeSupport = new HashtableChangeSupport(this);
	}

	/*
	 * public void justATest(int key, int value){ put(new Integer(key), new
	 * Integer(value)); }
	 */
	public ElementType get(Object key) {
		// public ElementType get(KeyType key){
		ElementType retVal = super.get(key);

		if (retVal == null)
			System.out.println("Null retval for key" + key);

		return retVal;
		// return super.get(key);
	}

	public ElementType put(KeyType key, ElementType value) {
		ElementType o = super.put(key, value);
		hashtableChangeSupport.keyPut(key, value);
		return o;
	}

	public ElementType remove(Object key) {
		ElementType o = super.remove(key);
		hashtableChangeSupport.keyRemoved(key);
		return o;
	}

	Object userObject = null;

	public Object getUserObject() {
		return userObject;
	}

	public void setUserObject(Object newValue) {
		userObject = newValue;
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

	/*
	 * public Enumeration elements(){ return contents.elements(); }
	 * 
	 * public Enumeration keys(){ return contents.keys(); }
	 * 
	 * public int size(){ return contents.size(); }
	 */
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