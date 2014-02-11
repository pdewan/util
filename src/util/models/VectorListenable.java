package util.models;

import java.util.Collection;

public interface VectorListenable extends VectorListenerRegisterer {
//	public void addVectorListener(VectorListener vectorListener);

	public void removeVectorListener(VectorListener vectorListener);

	public void addVectorMethodsListener(VectorMethodsListener vectorListener);

	public void removeVectorMethodsListener(VectorMethodsListener vectorListener);

	void addVectorListeners(Collection<VectorListener> someListeners);

}
