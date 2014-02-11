package util.awt;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import util.trace.Tracer;

public class ADelegateFrame extends Frame implements DelegateFrame {
	static ArrayList<ADelegateFrame> allFrames = new ArrayList();
	int frameId;

	public ADelegateFrame(String theTitle) {
		super(theTitle);
		ComponentRegistry.register(this);
		frameId = allFrames.size();
		allFrames.add(this);
		Object args[] = { getWidth(), getHeight(), getTitle() };
		SerializableFrameRequest request = new ASerializableFrameRequest(
				getID(), SerializableFrameRequest.CREATE_FRAME, args);
		// outputListener.newFrameRequest(request);
		AnOutputQueue.notifyListeners(request);

	}

	Map<Graphics, ListenableGraphics> toListenableGraphics = new HashMap();
	Vector<Painter> painters = new Vector();
	ArrayList<ListenablePainter> listenablePainters = new ArrayList();
	ArrayList<OutputListener> outputListeners = new ArrayList();

	public void addPainter(Painter painter) {
		if (painters.contains(painter))
			return;
		painters.add(painter);
	}

	public void removePainter(Painter painter) {
		if (painters.contains(painter))
			return;
		painters.remove(painter);

	}

	public void addPainter(ListenablePainter painter) {
		if (listenablePainters.contains(painter))
			return;
		listenablePainters.add(painter);
	}

	public void removePainter(ListenablePainter painter) {
		if (listenablePainters.contains(painter))
			return;
		listenablePainters.remove(painter);

	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < painters.size(); i++)
			painters.elementAt(i).paint(this, g);
		ListenableGraphics listenableGraphics = toListenableGraphics(g);
		listenableGraphics.notifyPaintStart();
		for (ListenablePainter painter : listenablePainters)
			painter.paint(this, listenableGraphics);
		listenableGraphics.notifyPaintEnd();
	}

	ListenableGraphics toListenableGraphics(Graphics g) {
		ListenableGraphics retVal = toListenableGraphics.get(g);
		if (retVal == null) {
			retVal = new AListenableGraphics(frameId, g);
			retVal.setOutputListeners(outputListeners);
			toListenableGraphics.put(g, retVal);
		}
		return retVal;
	}

	protected void processEvent(AWTEvent event) {
		// do not do anything by default.
		// let the method below be called for notifying.
	}

	public void processEvent(SerializableEvent event) {
		if (!event.isLocal()) {
			if (event.isResizeEvent()) {
				this.setSize(event.getSize());
				return;
			}
		}
		super.processEvent(event.getAWTEvent());
	}

	public void processRequest(SerializableFrameRequest request) {
		if (request.getName().equals(request.SET_SIZE)) {
			int width = (Integer) request.getArgs()[0];
			int height = (Integer) request.getArgs()[1];
			setSize(width, height);
		}
	}

	public void addOutputListener(OutputListener outputListener) {
		if (outputListeners.contains(outputListener))
			return;
		outputListeners.add(outputListener);
		Object args[] = { getWidth(), getHeight(), getTitle() };
		SerializableFrameRequest request = new ASerializableFrameRequest(
				getID(), SerializableFrameRequest.CREATE_FRAME, args);
		outputListener.newFrameRequest(request);
		// AnOutputQueue.notifyListeners(request);

	}

	@Override
	public void setSize(int theWidth, int theHeight) {
		Dimension currentSize = getSize();
		if (currentSize.width == theWidth && currentSize.height == theHeight)
			return;
		super.setSize(theWidth, theHeight);
		Object args[] = { getWidth(), getHeight() };
		SerializableFrameRequest request = new ASerializableFrameRequest(
				getID(), SerializableFrameRequest.SET_SIZE, args);
		for (OutputListener listener : outputListeners) {
			listener.newFrameRequest(request);
		}
		AnOutputQueue.notifyListeners(request);
	}

	public void removeOutputListener(OutputListener graphicsListener) {
		outputListeners.remove(graphicsListener);

	}

	public void clearOutputListeners(OutputListener graphicsListener) {
		outputListeners.clear();

	}

	public static List<ADelegateFrame> getAllFrames() {
		return allFrames;
	}

	public int getID() {
		return frameId;
	}

	public static ADelegateFrame getFrame(int id) {
		if (id < 0 || id >= allFrames.size()) {
			Tracer.error("invalid frame id:" + id);
			return null;
		}
		return allFrames.get(id);
	}

	static {
		AnInputQueue.getEventQueue();
	}
}
