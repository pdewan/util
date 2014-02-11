package util.awt;

import java.awt.Rectangle;

public class ASerializableFrameRequest extends ASerializableRequest implements
		SerializableFrameRequest {
	public ASerializableFrameRequest(int theFrameId, String name, Object[] args) {
		super(theFrameId, name, args);
	}

}
