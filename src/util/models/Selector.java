package util.models;

public interface Selector<ElementType, QueryType> {
	public boolean match(ElementType element, QueryType query);

}
