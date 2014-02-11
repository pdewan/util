package util.models;

import java.util.ArrayList;
import java.util.List;
// a dynamic enum in which each object is a string that is either checked or not.
// the current choice is always a template "create new list" that can be edited
// to add to the dynamic enum

public class ACheckedStringEnum extends ADynamicEnum<CheckedObject<String>>
		implements CheckedStringEnum {
	CheckedObject<String> template;

	public ACheckedStringEnum(List<CheckedObject<String>> theInitialChoices) {
		super(theInitialChoices);
		template = new ACheckedObject("Create New List", false,
				ACheckedObject.TEMPLATE_USER_DATA);
		super.setValue(template);
		// TODO Auto-generated constructor stub
	}

	public void addUserChoice(String newString) {
		CheckedObject<String> newVal = new ACheckedObject<String>(newString);
		newVal.setStatus(true);
		// choices.add(newVal);
		// do not want to negate status
		super.setValue(newVal);
		super.setValue(template);

	}

	void maybeAddChoice(CheckedObject<String> newVal) {
		if (newVal == template)
			return;
		super.maybeAddChoice(newVal);

	}

	@Override
	public void setValue(CheckedObject<String> newValue) {
		// System.out.println("ACheckedStringEnum setValue");
		newValue.setStatus(!newValue.getStatus());
		super.setValue(newValue);
		super.setValue(template);

	}

	// all the Strings that are checked
	public List<String> getCheckedStrings() {
		List<String> list = new ArrayList();
		for (int i = 0; i < choicesSize(); i++) {
			CheckedObject<String> choice = choices.get(i);
			if (choice.getStatus() && choice != template)
				list.add(choice.getObject());
		}
		return list;
	}

}
