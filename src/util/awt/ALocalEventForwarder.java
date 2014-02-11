package util.awt;

public class ALocalEventForwarder implements EventQueueHandler {
	public void newEvent(SerializableEvent event) {
		int frameId = event.getSource();
		ADelegateFrame frame = ADelegateFrame.getFrame(frameId);
		frame.processEvent(event);
	}
}
