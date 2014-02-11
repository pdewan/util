package util.models;

public interface NamedCheckedList<ElementType> extends CheckedList<ElementType> {

	public String getName();

	public void setName(String theName);

}