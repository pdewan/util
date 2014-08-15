package util.awt;

import java.awt.AWTEvent;

public interface ExtendibleAWTEventQueue {

	public void addEventQueueHandler(AWTEventQueueHandler listener);

	public void removeEventQueueHandler(AWTEventQueueHandler listener);

	public void clearEventQueuehandlers();

	public void dispatchEvent(AWTEvent event);

	void dispatchReceivedEvent(AWTEvent anEvent);



}