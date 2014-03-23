package util.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Vector;

import util.trace.Tracer;

//import bus.uigen.DynamicEnum;
@util.annotations.StructurePattern(util.annotations.StructurePatternNames.ENUM_PATTERN)
public class ADynamicEnum<ElementType> implements java.io.Serializable,
		DynamicEnum<ElementType> {
	List<ElementType> choices = new Vector();
	ElementType currentChoice;
	public static final String UNINIT_ENUM = " ";

	public ADynamicEnum() {
//		System.out.println("ADynamicEnum created:" + this);
		// initCurrentChoice();
	}

	public ADynamicEnum(List<ElementType> theInitialChoices) {
		// init();
//		System.out.println("ADynamicEnum created:" + this);
		choices = theInitialChoices;
		initCurrentChoice();
	}

	public ADynamicEnum(ElementType[] theInitialChoices) {
		// init();
//		System.out.println("ADynamicEnum created:" + this);
		for (int i = 0; i < theInitialChoices.length; i++)
			choices.add(theInitialChoices[i]);
		initCurrentChoice();
	}

	public ADynamicEnum(ElementType theInitialChoice) {
		// choices.add(theInitialChoice);
		// choices.add(theInitialChoice);
//		System.out.println("ADynamicEnum created:" + this.hashCode());
		setValue(theInitialChoice);
	}

	void initCurrentChoice() {

		if (choices.size() > 0) {
			ElementType choice = choices.get(0);
			setValue(choice);
//			currentChoice = choice;
			// setValue (choices.get(0));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bus.uigen.DynamicEnum#choicesSize()
	 */
	public int choicesSize() {
		return choices.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bus.uigen.DynamicEnum#choiceAt(int)
	 */
	public ElementType choiceAt(int i) {
		return (ElementType) choices.get(i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bus.uigen.DynamicEnum#getValue()
	 */
	public ElementType getValue() {
		return currentChoice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bus.uigen.DynamicEnum#getChoices()
	 */
	/*
	 * public List<ElementType> getChoices() { return choices; }
	 */
	public List<ElementType> getChoices() {
		return choices;
	}
	@Override
	public void setChoices(List<ElementType> newValue) {
		List<ElementType> oldValue = choices;
		choices = newValue;
		initCurrentChoice();
		propertyChange.firePropertyChange("choices", oldValue, newValue);
	}
	@Override
	public void replaceChoices(List<ElementType> newValue) {
		choices.clear();
		for (ElementType choice:choices) {
			choices.add(choice);
		}
//		choices = newValue;
		initCurrentChoice();
		propertyChange.firePropertyChange("choices", null, newValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bus.uigen.DynamicEnum#setValue(ElementType)
	 */
	void maybeAddChoice(ElementType newVal) {
		if (!choices.contains(newVal)) {
			choices.add(newVal);
			if (propertyChange == null) {
//				Tracer.error("Java Error: property change null when it should not be:" + this.hashCode());
				propertyChange = new PropertyChangeSupport(
						this);
				
			}
//			else
				propertyChange.firePropertyChange("choices", null, choices);
		}

	}
	public void addChoice(ElementType newVal) {
		maybeAddChoice(newVal);
	}

	public void setValue(ElementType newVal) {
		Object oldChoice = currentChoice;
		currentChoice = newVal;
		maybeAddChoice(newVal);
		// if (!choices.contains(newVal)) {
		// choices.add(newVal);
		// propertyChange.firePropertyChange("choices", null,
		// choices);
		// }
		if (propertyChange != null)
		propertyChange.firePropertyChange("value", oldChoice, newVal);
	}

	public String toString() {
		if (currentChoice == null)
			// return "Uninitialized dynamic enumeration";
			return UNINIT_ENUM;
		return currentChoice.toString();
	}

//	// override it to add a choice that is not of ElementType
//	public void addUserChoice(String newValue) {
//
//	}

	public void replaceChoice(int index, ElementType newName) {
		if (index >= choices.size() - 1)
			return;
		choices.set(index, newName);
	}

	public boolean equals(ADynamicEnum otherObject) {
		return otherObject.getValue().equals(getValue())
				& otherObject.choices.equals(choices);
		// otherObject.getValue().equals(getValue());
	}

	transient PropertyChangeSupport propertyChange;
	//= new PropertyChangeSupport(			this);

	@util.annotations.ObserverRegisterer(util.annotations.ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener l) {
		if (propertyChange == null) {
			// transient field, need this
			propertyChange = new PropertyChangeSupport(
					this);

		}
		propertyChange.addPropertyChangeListener(l);
	}

}