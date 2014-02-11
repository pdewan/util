package util.models;

import util.trace.Tracer;

//import bus.uigen.HashtableChangeSupport;
//import bus.uigen.HashtableListener;
//import bus.uigen.ListenableHashtable;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.HASHTABLE_PATTERN)
public class AListenableHashtable<KeyType, ElementType> extends
		java.util.Hashtable<KeyType, ElementType> implements
		ListenableHashtable<KeyType, ElementType> {
	// implements HashtableListenable, HashtableInterface<KeyType, ElementType>,
	// java.io.Serializable {
	// Hashtable contents;
	transient HashtableChangeSupport hashtableChangeSupport;

	public AListenableHashtable() {
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
		/*
		 * System.out.println ("Key: " + key + " Get:" + retVal); if (retVal ==
		 * null) System.out.println("Null retval for key" + key);
		 */

		return retVal;
		// return super.get(key);
	}

	public ElementType put(KeyType key, ElementType value) {
		ElementType o = super.put(key, value);
		// System.out.println ("Key: " + key + " Value:" + value);
		hashtableChangeSupport.keyPut(key, value);
		return o;
	}

	public ElementType remove(Object key) {
		ElementType o = super.remove(key);
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

	public boolean isKeyEditable(KeyType key) {
		return keysEditable;
	}

	public boolean isValueEditable(KeyType key) {
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

	/*
	 * public Enumeration elements(){ return contents.elements(); }
	 * 
	 * public Enumeration keys(){ return contents.keys(); }
	 * 
	 * public int size(){ return contents.size(); }
	 */
	public void addHashtableListener(HashtableListener hashtableListener) {
		try {
			hashtableChangeSupport.addHashtableListener(hashtableListener);
		} catch (Exception e) {
			if (hashtableChangeSupport == null) {
				initSerializedObject();
				hashtableChangeSupport.addHashtableListener(hashtableListener);
				Tracer.warning("Define a setter for:" + this);
			} else
				e.printStackTrace();

		}
	}

	public void removeHashtableListener(HashtableListener hashtableListener) {
		hashtableChangeSupport.removeHashtableListener(hashtableListener);
	}

	public void initSerializedObject() {
		hashtableChangeSupport = new HashtableChangeSupport(this);
	}
}