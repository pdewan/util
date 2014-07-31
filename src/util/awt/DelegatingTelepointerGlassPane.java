package util.awt;

import java.awt.event.AWTEventListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public interface DelegatingTelepointerGlassPane 
        extends MouseListener, MouseMotionListener, AWTEventListener, KeyListener {

	int getPointerWidth();

	void setPointerWidth(int aWidth);

	int getPointerHeight();

	void setPointerHeight(int aHeight);
	void addPainter(GraphicsPainter aPainter);
	void removePainter(GraphicsPainter aPainter);

	int getPointerX();

	void setPointerX(int newVal);

	int getPointerY();

	void setPointerY(int newVal);

	void addTelePointerListener(PointListener aListener);

	void removeTelePointerListener(PointListener aListener);
	void repaint();

}
