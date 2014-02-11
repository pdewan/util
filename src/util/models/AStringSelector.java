package util.models;

public class AStringSelector<ElementType> implements
		Selector<ElementType, String> {
	public boolean match(ElementType element, String query) {
		return query == null
				|| query == ""
				|| element.toString().toLowerCase()
						.contains(query.toLowerCase());

	}

}
