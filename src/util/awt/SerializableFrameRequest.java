package util.awt;

import java.awt.Rectangle;

public interface SerializableFrameRequest extends SerializableRequest {
	public static final String CREATE_FRAME = "createFrame";
	public static final String SET_SIZE = "setSize";
}
