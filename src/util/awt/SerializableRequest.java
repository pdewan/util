package util.awt;

import java.io.Serializable;

public interface SerializableRequest extends Serializable {
	public int getSource();

	public String getName();

	public Object[] getArgs();
}
