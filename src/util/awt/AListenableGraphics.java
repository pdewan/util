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
import java.util.List;

public class AListenableGraphics implements ListenableGraphics {
	int frameId;
	Graphics graphics;
	ArrayList<OutputListener> graphicsListeners = new ArrayList();

	public AListenableGraphics(int theFrameId, Graphics g) {
		graphics = g;
		frameId = theFrameId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#clearRect(int, int, int, int)
	 */
	@Override
	public void clearRect(int arg0, int arg1, int arg2, int arg3) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#clipRect(int, int, int, int)
	 */
	@Override
	public void clipRect(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#copyArea(int, int, int, int, int, int)
	 */
	@Override
	public void copyArea(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#create()
	 */
	@Override
	public Graphics create() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawArc(int, int, int, int, int, int)
	 */
	@Override
	public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawImage(java.awt.Image, int, int,
	 * java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, ImageObserver arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawImage(java.awt.Image, int, int,
	 * java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, Color arg3,
			ImageObserver arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawImage(java.awt.Image, int, int, int,
	 * int, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, ImageObserver arg5) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawImage(java.awt.Image, int, int, int,
	 * int, java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, Color arg5, ImageObserver arg6) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawImage(java.awt.Image, int, int, int,
	 * int, int, int, int, int, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5, int arg6, int arg7, int arg8, ImageObserver arg9) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawImage(java.awt.Image, int, int, int,
	 * int, int, int, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5, int arg6, int arg7, int arg8, Color arg9,
			ImageObserver arg10) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawLine(int, int, int, int)
	 */
	@Override
	public void drawLine(int arg0, int arg1, int arg2, int arg3) {
		graphics.drawLine(arg0, arg1, arg2, arg3);
		Object args[] = { arg0, arg1, arg2, arg3 };
		SerializableGraphicsRequest request = new ASerializableGraphicsRequest(
				frameId, SerializableGraphicsRequest.DRAW_LINE, args);
		request.setClipBounds(graphics.getClipBounds());
		for (OutputListener listener : graphicsListeners) {
			listener.newGraphicsRequest(request);
		}
		AnOutputQueue.notifyListeners(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawOval(int, int, int, int)
	 */
	@Override
	public void drawOval(int arg0, int arg1, int arg2, int arg3) {
		graphics.drawOval(arg0, arg1, arg2, arg3);
		Object args[] = { arg0, arg1, arg2, arg3 };
		SerializableGraphicsRequest request = new ASerializableGraphicsRequest(
				frameId, SerializableGraphicsRequest.DRAW_OVAL, args);
		request.setClipBounds(graphics.getClipBounds());
		for (OutputListener listener : graphicsListeners) {
			listener.newGraphicsRequest(request);
		}
		AnOutputQueue.notifyListeners(request);
	}

	@Override
	public void drawRect(int arg0, int arg1, int arg2, int arg3) {
		graphics.drawOval(arg0, arg1, arg2, arg3);
		Object args[] = { arg0, arg1, arg2, arg3 };
		SerializableGraphicsRequest request = new ASerializableGraphicsRequest(
				frameId, SerializableGraphicsRequest.DRAW_RECT, args);
		request.setClipBounds(graphics.getClipBounds());
		for (OutputListener listener : graphicsListeners) {
			listener.newGraphicsRequest(request);
		}
		AnOutputQueue.notifyListeners(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawPolygon(int[], int[], int)
	 */
	@Override
	public void drawPolygon(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawPolyline(int[], int[], int)
	 */
	@Override
	public void drawPolyline(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawRoundRect(int, int, int, int, int,
	 * int)
	 */
	@Override
	public void drawRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#drawString(java.lang.String, int, int)
	 */
	@Override
	public void drawString(String arg0, int arg1, int arg2) {
		graphics.drawString(arg0, arg1, arg2);
		Object args[] = { arg0, arg1, arg2 };
		SerializableGraphicsRequest request = new ASerializableGraphicsRequest(
				frameId, SerializableGraphicsRequest.DRAW_STRING, args);
		request.setClipBounds(graphics.getClipBounds());
		for (OutputListener listener : graphicsListeners) {
			listener.newGraphicsRequest(request);
		}
		AnOutputQueue.notifyListeners(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * util.awt.ObservableGraphics#drawString(java.text.AttributedCharacterIterator
	 * , int, int)
	 */
	@Override
	public void drawString(AttributedCharacterIterator arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#fillArc(int, int, int, int, int, int)
	 */
	@Override
	public void fillArc(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#fillOval(int, int, int, int)
	 */
	@Override
	public void fillOval(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#fillPolygon(int[], int[], int)
	 */
	@Override
	public void fillPolygon(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#fillRect(int, int, int, int)
	 */
	@Override
	public void fillRect(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#fillRoundRect(int, int, int, int, int,
	 * int)
	 */
	@Override
	public void fillRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#getClip()
	 */
	@Override
	public Shape getClip() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#getClipBounds()
	 */
	@Override
	public Rectangle getClipBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#getColor()
	 */
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#getFont()
	 */
	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return graphics.getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#getFontMetrics(java.awt.Font)
	 */
	@Override
	public FontMetrics getFontMetrics(Font arg0) {
		// TODO Auto-generated method stub
		return graphics.getFontMetrics(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#setClip(java.awt.Shape)
	 */
	@Override
	public void setClip(Shape arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#setClip(int, int, int, int)
	 */
	@Override
	public void setClip(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#setFont(java.awt.Font)
	 */
	@Override
	public void setFont(Font arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#setPaintMode()
	 */
	@Override
	public void setPaintMode() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#setXORMode(java.awt.Color)
	 */
	@Override
	public void setXORMode(Color arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.awt.ObservableGraphics#translate(int, int)
	 */
	@Override
	public void translate(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * util.awt.ObservableGraphics#addGraphicsListener(util.awt.GraphicsListener
	 * )
	 */
	// @Override
	// public void addGraphicsListener(GraphicsListener graphicsListener) {
	// // TODO Auto-generated method stub
	//
	// }
	// /* (non-Javadoc)
	// * @see
	// util.awt.ObservableGraphics#removeGraphicsListener(util.awt.GraphicsListener)
	// */
	// @Override
	// public void removeGraphicsListener(GraphicsListener graphicsListener) {
	// // TODO Auto-generated method stub
	//
	// }
	// /* (non-Javadoc)
	// * @see
	// util.awt.ObservableGraphics#clearGraphicsListeners(util.awt.GraphicsListener)
	// */
	// @Override
	// public void clearGraphicsListeners(GraphicsListener graphicsListener) {
	// // TODO Auto-generated method stub
	//
	// }
	public void setOutputListeners(ArrayList theGraphicsListeners) {
		graphicsListeners = theGraphicsListeners;
	}

	public void notifyPaintStart() {
		Object args[] = {};
		SerializableGraphicsRequest request = new ASerializableGraphicsRequest(
				frameId, SerializableGraphicsRequest.PAINT_START, args);
		request.setClipBounds(graphics.getClipBounds());
		for (OutputListener listener : graphicsListeners) {
			listener.newGraphicsRequest(request);
		}
		AnOutputQueue.notifyListeners(request);
	}

	public void notifyPaintEnd() {
		Object args[] = {};
		SerializableGraphicsRequest request = new ASerializableGraphicsRequest(
				frameId, SerializableGraphicsRequest.PAINT_END, args);
		request.setClipBounds(graphics.getClipBounds());
		for (OutputListener listener : graphicsListeners) {
			listener.newGraphicsRequest(request);
		}
		AnOutputQueue.notifyListeners(request);
	}

	public void processRequest(SerializableGraphicsRequest theRequest) {
		Object[] args = theRequest.getArgs();
		if (theRequest.getName().equals(theRequest.DRAW_LINE)) {
			// System.out.println("Drawing line ");
			drawLine((Integer) args[0], (Integer) args[1], (Integer) args[2],
					(Integer) args[3]);
		} else if (theRequest.getName().equals(theRequest.DRAW_RECT)) {
			drawRect((Integer) args[0], (Integer) args[1], (Integer) args[2],
					(Integer) args[3]);
		} else if (theRequest.getName().equals(theRequest.DRAW_OVAL)) {
			drawOval((Integer) args[0], (Integer) args[1], (Integer) args[2],
					(Integer) args[3]);
		} else if (theRequest.getName().equals(theRequest.DRAW_STRING)) {
			// System.out.println("Drawing String ");
			drawString((String) args[0], (Integer) args[1], (Integer) args[2]);
		}

	}

}
