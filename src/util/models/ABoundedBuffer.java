package util.models;

import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
// why is an interface not implemented?
public class ABoundedBuffer<ElementType> extends ArrayBlockingQueue<ElementType> implements BoundedBuffer<ElementType>{
	Vector<ElementType> buffer = new Vector();
	public static final int MAXSIZE = 1000;
	public static final String DEFAULT_NAME = "Generic Buffer";
	static int id;
	String name;
	
	public ABoundedBuffer (String aName) {
		super (MAXSIZE);
		name = aName + toString(id);
		id++;
	}
	
	public static String toString(int id) {
		return "(" + id + ")";
	}
	
	public ABoundedBuffer() {
		this("Anonymous Bounded Buffer");
//		this(DEFAULT_NAME);
	}
	
	public String getName() {
		return name;
	}

	public  void put(ElementType element) throws InterruptedException {
		super.put(element);
//		try {
//			while (buffer.size() >= MAXSIZE)
//				this.wait();
//
//			this.notify();
//
//			buffer.addElement(element);
//		} catch (Exception e) {
//		}
//		;
	}

//	public synchronized void put(ElementType element, long timeOut) throws InterruptedException {
//		super.put(element);
//		try {
//			while (buffer.size() >= MAXSIZE)
//				this.wait(timeOut);
//			this.notify();
//			buffer.addElement(element);
//		} catch (Exception e) {
//		}
//		;
//	}

	public  ElementType get() throws InterruptedException {
		return super.take();
//		try {
//			while (buffer.size() == 0) {
//				this.wait();
//			}
//			ElementType retVal = buffer.elementAt(0);
//			if (buffer.size() >= MAXSIZE)
//				this.notify();
//			buffer.removeElementAt(0);
//			return retVal;
//		} catch (Exception e) {
//			return null;
//		}
	}

//	public synchronized ElementType get(long timeOut) throws InterruptedException {
//		return super.take();
////		try {
////			boolean waited = false;
////			while (buffer.size() == 0) {
////				if (waited)
////					return null;
////				waited = true;
////				this.wait(timeOut);
////			}
////			ElementType retVal = buffer.elementAt(0);
////			if (buffer.size() >= MAXSIZE)
////				this.notify();
////			buffer.removeElementAt(0);
////			return retVal;
////		} catch (Exception e) {
////			return null;
////		}
//	}

}