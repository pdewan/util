package util.awt;

import java.awt.Graphics;

public interface DelegateFrame {

	public void addPainter(Painter painter);

	public void removePainter(Painter painter);

	public void paint(Graphics g);

	public void processEvent(SerializableEvent event);

	public void processRequest(SerializableFrameRequest request);

	public void addPainter(ListenablePainter painter);

	public void removePainter(ListenablePainter painter);

	public void addOutputListener(OutputListener graphicsListener);

	public void removeOutputListener(OutputListener outputListener);

	public void clearOutputListeners(OutputListener outputListener);

	public int getID();

}