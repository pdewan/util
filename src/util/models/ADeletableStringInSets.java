package util.models;

import java.util.List;

public class ADeletableStringInSets extends AStringInSets implements
		DeletableStringInSets {
	List<DeletableStringInSets> container;

	public ADeletableStringInSets(String theString,
			CheckedStringEnum theCheckedStringEnum,
			List<DeletableStringInSets> theContainer) {
		super(theString, theCheckedStringEnum);
		container = theContainer;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void delete() {
		container.remove(this);

	}

}
