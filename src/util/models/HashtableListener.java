package util.models;
public interface HashtableListener {
	public void keyPut(Object source, Object key, Object value, int newSize);
	public void keyRemoved(Object source, Object key, int newSize);
}
