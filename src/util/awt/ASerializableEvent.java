package util.awt;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import util.trace.Tracer;

public class ASerializableEvent implements SerializableEvent {
	String paramString;
	AWTEvent event;
	int componentId;
	long timeStamp;

	// transient boolean isLocal = false;
	public ASerializableEvent(AWTEvent theEvent, int theFrameId) {
		event = theEvent;
		// event.setSource(null);
		paramString = theEvent.paramString();
		componentId = theFrameId;
		timeStamp = System.currentTimeMillis();
	}

	public boolean isLocal() {
		return event.getSource() != null;
	}

	public AWTEvent getAWTEvent() {
		return event;
	}

	public int getSource() {
		// return ComponentRegistry.getComponent(componentId);
		return componentId;
	}

	public boolean isMouseEvent() {
		return event instanceof MouseEvent;
	}

	public boolean isKeyEvent() {
		return event instanceof KeyEvent;
	}

	public boolean isResizeEvent() {
		if (!(event instanceof ComponentEvent))
			return false;
		ComponentEvent componentEvent = (ComponentEvent) event;
		return componentEvent.getID() == ComponentEvent.COMPONENT_RESIZED;
	}

	public boolean isMouseMovedEvent() {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_MOVED;
	}

	public boolean isMousePressedEvent() {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_PRESSED;
	}

	public boolean isMouseClickedEvent() {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_CLICKED;
	}

	public boolean isMouseReleasedEvent() {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_RELEASED;
	}

	public boolean isMouseEnteredEvent() {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_ENTERED;
	}

	public boolean isMouseExitedEvent() {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_EXITED;
	}

	public Dimension getSize() {
		if (!isResizeEvent())
			throw new RuntimeException("Event  with ID: " + event.getID()
					+ " is not a resize event");
		return getSize(paramString);
	}

	Point location;

	public Point getLocation() {
		if (location == null) {
			if (!(event instanceof MouseEvent))
				throw new RuntimeException("Event  with ID: " + event.getID()
						+ " is not a mouse event");
			location = getLocation(paramString);
		}
		return location;
	}

	public int getX() {
		return getLocation().x;
	}

	public int getY() {
		return getLocation().y;
	}

	public long getGenerationTime() {
		return timeStamp;
	}

	public static Dimension getSize(String paramString) {
		int secondSpace = paramString.lastIndexOf(' ');
		int firstX = paramString.indexOf('x');
		int firstClosedParen = paramString.indexOf(')');
		try {
			String stringWidth = paramString.substring(secondSpace + 1, firstX);
			int width = Integer.parseInt(stringWidth);
			String stringHeight = paramString.substring(firstX + 1,
					firstClosedParen);
			int height = Integer.parseInt(stringHeight);
			return new Dimension(width, height);
		} catch (Exception e) {
			return new Dimension(0, 0);
		}
	}

	public static Point getLocation(String paramString) {
		int leftPara = paramString.indexOf('(');
		int comma = paramString.indexOf(',', leftPara);
		int rightPara = paramString.indexOf(')');
		try {
			String stringX = paramString.substring(leftPara + 1, comma);
			int x = Integer.parseInt(stringX);
			String stringY = paramString.substring(comma + 1, rightPara);
			int y = Integer.parseInt(stringY);
			return new Point(x, y);
		} catch (Exception e) {
			e.printStackTrace();
			return new Point(0, 0);
		}
	}

}
