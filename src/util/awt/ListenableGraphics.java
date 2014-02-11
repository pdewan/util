package util.awt;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

public interface ListenableGraphics {

	public abstract void clearRect(int arg0, int arg1, int arg2, int arg3);

	public abstract void clipRect(int arg0, int arg1, int arg2, int arg3);

	public abstract void copyArea(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	public abstract Graphics create();

	public abstract void dispose();

	public abstract void drawArc(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	public abstract boolean drawImage(Image arg0, int arg1, int arg2,
			ImageObserver arg3);

	public abstract boolean drawImage(Image arg0, int arg1, int arg2,
			Color arg3, ImageObserver arg4);

	public abstract boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, ImageObserver arg5);

	public abstract boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, Color arg5, ImageObserver arg6);

	public abstract boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5, int arg6, int arg7, int arg8, ImageObserver arg9);

	public abstract boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5, int arg6, int arg7, int arg8, Color arg9,
			ImageObserver arg10);

	public abstract void drawLine(int arg0, int arg1, int arg2, int arg3);

	public abstract void drawOval(int arg0, int arg1, int arg2, int arg3);

	public void drawRect(int arg0, int arg1, int arg2, int arg3);

	public abstract void drawPolygon(int[] arg0, int[] arg1, int arg2);

	public abstract void drawPolyline(int[] arg0, int[] arg1, int arg2);

	public abstract void drawRoundRect(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	public abstract void drawString(String arg0, int arg1, int arg2);

	public abstract void drawString(AttributedCharacterIterator arg0, int arg1,
			int arg2);

	public abstract void fillArc(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	public abstract void fillOval(int arg0, int arg1, int arg2, int arg3);

	public abstract void fillPolygon(int[] arg0, int[] arg1, int arg2);

	public abstract void fillRect(int arg0, int arg1, int arg2, int arg3);

	public abstract void fillRoundRect(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	public abstract Shape getClip();

	public abstract Rectangle getClipBounds();

	public abstract Color getColor();

	public abstract Font getFont();

	public abstract FontMetrics getFontMetrics(Font arg0);

	public abstract void setClip(Shape arg0);

	public abstract void setClip(int arg0, int arg1, int arg2, int arg3);

	public abstract void setColor(Color arg0);

	public abstract void setFont(Font arg0);

	public abstract void setPaintMode();

	public abstract void setXORMode(Color arg0);

	public abstract void translate(int arg0, int arg1);

	public void processRequest(SerializableGraphicsRequest theRequest);

	// public abstract void addGraphicsListener(GraphicsListener
	// graphicsListener);
	//
	// public abstract void removeGraphicsListener(
	// GraphicsListener graphicsListener);
	//
	// public abstract void clearGraphicsListeners(
	// GraphicsListener graphicsListener);
	public void setOutputListeners(ArrayList theGraphicsListeners);

	public void notifyPaintStart();

	public void notifyPaintEnd();

}