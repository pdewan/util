package util.models;

import java.util.List;

public interface CheckedStringEnum extends DynamicEnum<CheckedObject<String>> {
	public List<String> getCheckedStrings();

}
