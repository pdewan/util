package util.awt;

import java.awt.Dimension;

public interface EventQueueHandler {
	public void newEvent(SerializableEvent event);
}
