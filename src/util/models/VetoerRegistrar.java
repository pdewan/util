package util.models;

public interface VetoerRegistrar<ElementType> {
	public void addVetoer(Vetoer<ElementType> theVetoer);
	public void removeVetoer(Vetoer<ElementType> theVetoer);

}
