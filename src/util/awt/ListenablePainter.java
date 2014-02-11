package util.awt;

import java.awt.Graphics;

public interface ListenablePainter {
	public void paint(ADelegateFrame theFrame, ListenableGraphics g);
}
