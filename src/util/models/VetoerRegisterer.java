package util.models;

public interface VetoerRegisterer<ElementType> {
	public void addVetoer(Vetoer<ElementType> theVetoer);
	public void removeVetoer(Vetoer<ElementType> theVetoer);

}
