package util.models;

public interface CheckedObject<ObjectType> extends CorrectCloneable {

	public ObjectType getObject();

	public boolean getStatus();

	public void setStatus(boolean newVal);

	void setUserData(Object theUserData);

	Object getUserData();

	boolean isTemplate();

}