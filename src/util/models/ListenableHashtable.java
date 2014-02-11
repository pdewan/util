package util.models;

import java.util.List;
import java.util.Map;

public interface ListenableHashtable<KeyType, ElementType> extends
		ListenableTable<KeyType, ElementType>,
		HashtableInterface<KeyType, ElementType> {

}
