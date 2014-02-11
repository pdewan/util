package util.models;

public abstract class Observable extends java.util.Observable implements
		java.io.Serializable, Cloneable {
	public Object clone() {
		Object observableClone = null;
		try {
			observableClone = super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return (observableClone);
		}
	}

	private void writeObject(java.io.ObjectOutputStream out)
			throws java.io.IOException {
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in)
			throws java.io.IOException, ClassNotFoundException {
		in.defaultReadObject();
	}

}
