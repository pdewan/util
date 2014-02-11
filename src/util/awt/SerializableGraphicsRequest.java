package util.awt;

import java.awt.Rectangle;

public interface SerializableGraphicsRequest extends SerializableRequest {
	public static final String DRAW_OVAL = "drawOval";
	public static final String DRAW_RECT = "drawRect";
	public static final String DRAW_STRING = "drawString";
	public static final String DRAW_LINE = "drawLine";
	public static final String PAINT_START = "paintStart";
	public static final String PAINT_END = "paintEnd";

	public Rectangle getClipBounds();

	public void setClipBounds(Rectangle theRectangle);
}
