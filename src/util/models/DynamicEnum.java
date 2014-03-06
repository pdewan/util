package util.models;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface DynamicEnum<ElementType> extends PropertyListenerRegisterer{

	public int choicesSize();

	public ElementType choiceAt(int i);

	public ElementType getValue();

	public List<ElementType> getChoices();

	// public ElementType[] getChoices();

	public void setValue(ElementType newVal);
	public void addChoice(ElementType newVal);


}