package util.awt;

import java.awt.AWTEvent;

public interface DelegatingAWTEventQueue {

	public void addEventQueueHandler(AWTEventQueueHandler listener);

	public void removeEventQueueHandler(AWTEventQueueHandler listener);

	public void clearEventQueuehandlers();

//	public void dispatchEvent(SerializableAWTEvent event);
	public void dispatchEvent(AWTEvent event);
//	public Object getWToolkit();

	void dispatchReceivedEvent(AWTEvent anEvent);



}