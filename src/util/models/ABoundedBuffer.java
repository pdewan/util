package util.models;

import java.util.Vector;
// why is an interface not implemented?
public class ABoundedBuffer<ElementType> implements BoundedBuffer<ElementType>{
	Vector<ElementType> buffer = new Vector();
	final int MAXSIZE = 100;
	public static final String DEFAULT_NAME = "Generic Buffer";
	static int id;
	String name;
	
	public ABoundedBuffer (String aName) {
		name = aName + toString(id);
		id++;
	}
	
	public static String toString(int id) {
		return "(" + id + ")";
	}
	
	public ABoundedBuffer() {
		this(DEFAULT_NAME);
	}
	
	public String getName() {
		return name;
	}

	public synchronized void put(ElementType element) {
		try {
			while (buffer.size() >= MAXSIZE)
				this.wait();

			this.notify();

			buffer.addElement(element);
		} catch (Exception e) {
		}
		;
	}

	public synchronized void put(ElementType element, long timeOut) {
		try {
			while (buffer.size() >= MAXSIZE)
				this.wait(timeOut);
			this.notify();
			buffer.addElement(element);
		} catch (Exception e) {
		}
		;
	}

	public synchronized ElementType get() {
		try {
			while (buffer.size() == 0) {
				this.wait();
			}
			ElementType retVal = buffer.elementAt(0);
			if (buffer.size() >= MAXSIZE)
				this.notify();
			buffer.removeElementAt(0);
			return retVal;
		} catch (Exception e) {
			return null;
		}
	}

	public synchronized ElementType get(long timeOut) {
		try {
			boolean waited = false;
			while (buffer.size() == 0) {
				if (waited)
					return null;
				waited = true;
				this.wait(timeOut);
			}
			ElementType retVal = buffer.elementAt(0);
			if (buffer.size() >= MAXSIZE)
				this.notify();
			buffer.removeElementAt(0);
			return retVal;
		} catch (Exception e) {
			return null;
		}
	}

}