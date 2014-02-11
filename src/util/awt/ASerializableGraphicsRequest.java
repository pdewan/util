package util.awt;

import java.awt.Rectangle;

public class ASerializableGraphicsRequest extends ASerializableRequest
		implements SerializableGraphicsRequest {
	Rectangle clipBounds;
	int frameId;

	public ASerializableGraphicsRequest(int theFrameId, String name,
			Object[] args) {
		super(theFrameId, name, args);
	}

	public Rectangle getClipBounds() {
		return clipBounds;
	}

	public void setClipBounds(Rectangle theRectangle) {
		clipBounds = theRectangle;
	}

}
