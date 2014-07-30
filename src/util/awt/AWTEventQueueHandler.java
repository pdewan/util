package util.awt;

import java.awt.AWTEvent;
import java.awt.Dimension;

public interface AWTEventQueueHandler {
//	public void newEvent(SerializableAWTEvent event);
	public void newEvent(AWTEvent anEvent);
}
