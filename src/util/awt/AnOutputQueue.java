package util.awt;

import java.util.ArrayList;

public class AnOutputQueue {
	static ArrayList<OutputListener> outputListeners = new ArrayList();

	public static void addOutputListener(OutputListener outputListener) {
		if (outputListeners.contains(outputListener))
			return;
		outputListeners.add(outputListener);
	}

	public static void removeListener(OutputListener graphicsListener) {
		outputListeners.remove(graphicsListener);

	}

	public static void clearListeners(OutputListener graphicsListener) {
		outputListeners.clear();
	}

	public static void notifyListeners(SerializableFrameRequest frameRequest) {
		for (OutputListener listener : outputListeners) {
			listener.newFrameRequest(frameRequest);
		}
	}

	public static void notifyListeners(
			SerializableGraphicsRequest graphicsRequest) {
		for (OutputListener listener : outputListeners) {
			listener.newGraphicsRequest(graphicsRequest);
		}
	}

}
