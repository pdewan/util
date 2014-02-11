package util.models;

public class AVectorEnum<ElementType> extends AListenableVector<ElementType> {
	ElementType currentChoice;

	public AVectorEnum(ElementType[] theInitialChoices) {
		// init();

		for (int i = 0; i < theInitialChoices.length; i++)
			add(theInitialChoices[i]);
		initCurrentChoice();
	}

	public AVectorEnum(ElementType theInitialChoice) {
		// choices.add(theInitialChoice);
		// choices.add(theInitialChoice);
		setValue(theInitialChoice);
	}

	void initCurrentChoice() {
		if (size() > 0)
			setValue(elementAt(0));
	}

	public ElementType getValue() {
		return currentChoice;
	}

	public void setValue(ElementType newVal) {
		currentChoice = newVal;
		if (!contains(newVal)) {
			addElement(newVal);
		}
	}

	public String toString() {
		if (currentChoice == null)
			return "Uninitialized dynamic enumeration";
		return currentChoice.toString();
	}

	public boolean equals(AVectorEnum otherObject) {

		return super.equals(otherObject)
				&& otherObject.getValue().equals(getValue());
	}

}
