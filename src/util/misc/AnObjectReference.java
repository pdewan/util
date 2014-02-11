package util.misc;

public class AnObjectReference<ObjectType> implements
		ObjectReference<ObjectType> {
	ObjectType object;

	public AnObjectReference(ObjectType anObject) {
		object = anObject;
	}

	public AnObjectReference() {
	}

	public ObjectType getObject() {
		return object;
	}

	public void setObject(ObjectType anObject) {
		this.object = anObject;
	}

}
