package util.models;

public class ANamedCheckedList<ElementType> extends ACheckedList<ElementType>
		implements NamedCheckedList<ElementType> {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String theName) {
		name = theName;
	}

}
