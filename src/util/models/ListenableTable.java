package util.models;

import java.util.List;
import java.util.Map;

public interface ListenableTable<KeyType, ElementType> extends
		HashtableListenable, Map<KeyType, ElementType>, java.io.Serializable {

}
