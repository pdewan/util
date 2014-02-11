package util.models;

import java.util.List;

public class AListSelection<ElementType, QueryType> implements
		ListSelection<ElementType, QueryType> {
	QueryType query;
	Selector<ElementType, QueryType> selector;
	List<ElementType> selectee;
	ListenableVector<ElementType> selection;

	boolean showSelected;

	public AListSelection(List<ElementType> theSelectee,
			QueryType initialQuery, Selector<ElementType, QueryType> theSelector) {
		selection = new AListenableVector();
		selectee = theSelectee;
		query = initialQuery;
		if (theSelector == null)
			selector = new AStringSelector();
		else
			selector = theSelector;
		select(query);

	}

	void select(QueryType theQuery) {
		selection.clear();
		query = theQuery;
		for (int i = 0; i < selectee.size(); i++) {
			ElementType element = selectee.get(i);
			if (selector.match(element, theQuery))
				selection.add(element);

		}
	}

	// public void setSelected(boolean newVal) {
	// if (showSelected == newVal)
	// return;
	// showSelected = newVal;
	// select(query);
	//
	//
	//
	// }

	public void setQuery(QueryType newVal) {
		query = newVal;
		select(query);
	}

	public int size() {
		return selection.size();
	}

	public ElementType get(int i) {
		return selection.get(i);
	}

}
