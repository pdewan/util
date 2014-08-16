package util.awt;

import java.awt.AWTEvent;

import util.models.PropertyVetoerRegistrar;

public interface ExtendibleAWTEventQueue extends PropertyVetoerRegistrar {
	public static final String WINDOW = "Window";
	public void addEventQueueHandler(AWTEventQueueHandler listener);

	public void removeEventQueueHandler(AWTEventQueueHandler listener);

	public void clearEventQueuehandlers();

	public void dispatchEvent(AWTEvent event);

	void dispatchReceivedEvent(AWTEvent anEvent);



}