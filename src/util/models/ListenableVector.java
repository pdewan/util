package util.models;

import java.util.Collection;
import java.util.List;

import util.annotations.Visible;

//import bus.uigen.VectorListenable;
@util.annotations.StructurePattern(util.annotations.StructurePatternNames.VECTOR_PATTERN)
public interface ListenableVector<ElementType> extends VectorListenable,
		ChangeableVector<ElementType>, List<ElementType>, java.io.Serializable {
	ListenableVector<ElementType> deepClone();
	ListenableVector<ElementType> clone();
	
	void setVectorChangeSupport(VectorChangeSupport<ElementType> newVal);
	@Visible(false)
	VectorChangeSupport<ElementType> getVectorChangeSupport();
	ElementType observableGet(Integer pos);
	Integer lastObservableGetIndex();
	Object observableGetUserObject();
	Object observableGetTemp();
	public ListenableVector getRoot();

	
	@Visible(false)
	public String getName();
	
	public void setName(String newVal) ;
	void addVectorMethodsListeners(
			Collection<? extends VectorMethodsListener> someListeners);
	void unObservableSet(int pos, ElementType element);
	public void unObservableAdd(int aToIndex, ElementType anElement);
	public void userOperation(int aTargetIndex, Object anOperaton);



}
