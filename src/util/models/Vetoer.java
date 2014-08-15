package util.models;

public interface Vetoer<ValueType> {
	boolean veto(ValueType theInput);
}
