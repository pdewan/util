package util.models;

public interface ListSelection<ElementType, QueryType> {

	public void setQuery(QueryType newVal);

	public int size();

	public ElementType get(int i);

	// public void setSelected(boolean newVal);

}