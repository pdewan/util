package util.assertions;

public interface ElementChecker<ElementType> {
	public boolean check(ElementType element);
}