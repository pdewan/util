package util.misc;

public interface ObjectReference<ObjectType> {
	ObjectType getObject();

	void setObject(ObjectType anObject);
}
