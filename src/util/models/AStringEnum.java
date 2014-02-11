package util.models;

import java.util.Vector;

public class AStringEnum implements StringEnum {
	Vector choices = new Vector();
	String currentChoice = "Edit Me";

	public AStringEnum(String[] theInitialChoices) {
		setChoices(theInitialChoices);
	}

	public AStringEnum() {

	}

	public void setChoices(String[] theInitialChoices) {
		for (int i = 0; i < theInitialChoices.length; i++)
			choices.addElement(theInitialChoices[i]);
		if (theInitialChoices.length > 0)
			currentChoice = theInitialChoices[0];
	}

	public AStringEnum(String newName) {
		setValue(newName);

	}

	public int choicesSize() {
		return choices.size();
	}

	public String choiceAt(int i) {
		return (String) choices.elementAt(i);
	}

	public String getValue() {
		return currentChoice;
	}

	public void setValue(String newVal) {
		currentChoice = newVal;
		if (!choices.contains(newVal)) {
			choices.addElement(newVal);
		}
	}

	public String toString() {
		return currentChoice;
	}

}