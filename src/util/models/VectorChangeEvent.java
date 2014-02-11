package util.models;

// The event structure used to
// communicate the event of adding/deleting/changing
// a component of a Vector

public class VectorChangeEvent implements java.io.Serializable {

	private Object oldValue, newValue;
	private int position;
	private int eventType;
	int newSize;
	Object source;
	Object otherSource;
    // constants for event types
	public static final int AddComponentEvent = 1, DeleteComponentEvent = 2,
			ChangeComponentEvent = 3, InsertComponentEvent = 4,
			AddAllComponentsEvent = 5, ClearEvent = 6, SwapComponentsEvent = 7,
			MoveComponentEvent = 8, ReplaceComponentEvent = 9,
			CopyComponentEvent = 10, CopyToUserObjectEvent = 11, 
			CopyFromUserObjectEvent = 12, 
			ChangeUserObjectEvent = 13,
			ReadComponentEvent = 14,
					CopyToTempEvent = 15, 
					CopyFromTempEvent = 16, 
				    ChangeTempEvent = 17,
				    CopyTempToUserObjectEvent = 18,
				    CopyUserObjectToTempEvent = 19,
				    ReadUserObjectEvent = 20,
		ReadTempEvent = 21,
		PointerChangeEvent = 22,
		RemoteCopyComponentEvent = 23,
		RemoteMoveComponentEvent = 24,
		CopyInsertComponentEvent = 25,
		RemoteCopyInsertComponentEvent = 26,
				Pointer2ChangeEvent = 27,
				UserOperationEvent = 28,
			
			UndefEvent = 1000;

	boolean validType(int type) {
		return true;
		// if (type == AddComponentEvent ||
		// type == DeleteComponentEvent ||
		// type == InsertComponentEvent ||
		// type == ChangeComponentEvent ||
		// type == ClearEvent ||
		// type == AddAllComponentsEvent)
		//
		// return true;
		// else
		// return false;
	}

	public Object getSource() {
		return source;
	}

	void init(Object theSource, int type, int posn, Object oldObject,
			Object newObject, int theNewSize) {
		init(theSource, type, posn, oldObject, newObject, theNewSize, null);
	}

	void init(Object theSource, int type, int posn, Object oldObject,
			Object newObject, int theNewSize, Object theOtherSource) {
		// System.out.println("init, new size" + theNewSize);
		if (validType(type)) {
			eventType = type;
			oldValue = oldObject;
			newValue = newObject;
			position = posn;
			newSize = theNewSize;
			source = theSource;
			otherSource = theOtherSource;
		} else {
			System.out.println("E*** Unknown Event Type " + eventType);
			eventType = UndefEvent;

		}
	}

	public VectorChangeEvent(Object theSource, int type, int posn,
			Object oldObject, Object newObject, int newSize) {
		// System.out.println("got size " + newSize);
		init(theSource, type, posn, oldObject, newObject, newSize);
	}

	public VectorChangeEvent(Object theSource, int type, int posn,
			Object oldObject, Object newObject, int newSize,
			Object theOtherObject) {
		// System.out.println("got size " + newSize);
		init(theSource, type, posn, oldObject, newObject, newSize,
				theOtherObject);
	}

	public VectorChangeEvent(Object theSource, int type, int posn,
			Object oldObject, Object newObject) {
		init(theSource, type, posn, oldObject, newObject, 0);
	}

	public VectorChangeEvent(Object theSource, int type, int posn) {
		init(theSource, type, posn, null, null, 0);
	}

	public VectorChangeEvent(Object theSource, int type, Object oldObject,
			Object newObject) {
		init(theSource, type, -1, oldObject, newObject, 0);
	}

	public VectorChangeEvent(Object theSource, int type, int posn,
			Object newObject) {
		init(theSource, type, posn, null, newObject, 0);
	}

	public int getEventType() {
		return eventType;
	}

	public Object getNewValue() {
		return newValue;
	}

	public int getNewSize() {
		return newSize;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public int getPosition() {
		return position;
	}

	public String toString() {
		return super.toString() + ": Source:" + getSource() + ": EventType: "
				+ getEventType() + ": Position:" + getPosition()
				+ ": NewValue: " + getNewValue() + ": new size:" + getNewSize();
	}
}
