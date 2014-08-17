package util.awt;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.annotations.Visible;


public class AnExtendibleTelePointerGlassPane extends JPanel implements ExtendibleTelepointerGlassPane{
	public static final int DIAMETER = 10;
	int x = 50;
	int y = 50;
	int clickX, clickY;
	boolean pointerSelected;
	JFrame frame;
	protected int pointerWidth = DIAMETER, pointerHeight = DIAMETER;
	protected List<GraphicsPainter> painters = new ArrayList();
	protected List<PointListener> telepointerListeners = new ArrayList();
	boolean showTelePointer;
	GlassPaneController glassPaneController;
	
	
	
	public AnExtendibleTelePointerGlassPane(JFrame aFrame) {
		frame = aFrame;
		this.setFocusable(false);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
//		this.addKeyListener(this);
////		this.addKeyListener(this);
//		long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK
//			    + AWTEvent.MOUSE_EVENT_MASK
//			    + AWTEvent.KEY_EVENT_MASK;
//		Toolkit.getDefaultToolkit().addAWTEventListener( this, eventMask);
		this.setOpaque(false);
		GlassPaneRedispatcher redispatcher = new AGlassPaneRedispatcher(this, aFrame);
		aFrame.setGlassPane(this);
		this.setVisible(true);
		setGlassPaneController(new AGlassPaneController(this));
	}
	@Visible(false)
	public void repaint() {
		super.repaint();
	}
	@Visible(false)
	public void paint(Graphics g) {
		super.paint(g);
//		g.setColor(Color.RED);
//		g.fillOval(x, y, DIAMETER, DIAMETER);
		notifyPainters(g);
	}
	@Override
	public int getPointerX() {
		return x;
	}
	@Visible(false)
	@Override
	public void setPointerX(int newVal) {
		x = newVal;
	}
	@Visible(false)
	@Override
	public int getPointerY() {
		return y;
	}
	@Override
	public void setPointerY(int newVal) {
		y = newVal;
	}
	@Override
	public int getPointerWidth() {
		return pointerWidth;
	}
	@Override
	public void setPointerWidth(int aWidth) {
		pointerWidth = aWidth;
//		System.out.println("aWidth:" + pointerWidth);
		repaint();
	}
	@Override
	public int getPointerHeight() {
		return pointerHeight;
	}
	@Override
	public void setPointerHeight(int aHeight) {
		pointerHeight = aHeight;
//		System.out.println("aWidth:" + pointerHeight);

		repaint();
	}
	 
	boolean inTelePointer (MouseEvent event) {
		return event.getX() >= x && event.getX() <= (x + getPointerWidth()) &&
				event.getY() >= y && event.getY() <= (y + getPointerHeight());
	}
	@Visible(false)
	@Override
	 public void eventDispatched(AWTEvent event) { 
		System.out.println("foo");
		  if (event instanceof MouseEvent) { 
	            MouseEvent me = (MouseEvent) event; 
	            if (!SwingUtilities.isDescendingFrom(me.getComponent(), frame)) { 
	                return; 
	            } 
	           
	            if (me.getID() == MouseEvent.MOUSE_EXITED && me.getComponent() == frame) { 
	                x = -1; 
	                y = -1;
	            } else { 
	                MouseEvent converted = SwingUtilities.convertMouseEvent(me.getComponent(), me, frame.getGlassPane()); 
	                Point point = converted.getPoint(); 
	                x = point.x;
	                y = point.y;
	            } 
	            repaint(); 
	        } 
	    } 
	@Visible(false)
	@Override
	protected void processEvent (AWTEvent anEvent) {
		super.processEvent(anEvent);
	}
	
	@Visible(false)
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}
	@Visible(false)
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}
	@Visible(false)
	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}
	@Visible(false)
	@Override
	public void mousePressed(MouseEvent arg0) {
		if (inTelePointer (arg0)) {
//			System.out.println("In telepointer:" + arg0);
			pointerSelected = true;
			clickX = arg0.getX();
			clickY = arg0.getY();
//			repaint(); // not really necessary
			notifyTelePointerListeners(new Point(clickX, clickY));
		}
		
	}
	@Visible(false)
	@Override
	public void mouseReleased(MouseEvent arg0) {
		pointerSelected = false;
		
	}
	@Visible(false)
	@Override
	public void mouseDragged(MouseEvent event) {
		if (!pointerSelected) return;
		int incX = event.getX() - clickX;
		int incY = event.getY() - clickY;
		clickX = event.getX();
		clickY = event.getY();
		x += incX;
		y += incY;
		repaint();
		notifyTelePointerListeners(new Point(x, y));
	}
	@Visible(false)
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//	@Override
//	public void keyPressed(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
	@Visible(false)
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key pressed in relepointer");
	}
	@Visible(false)
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Visible(false)
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Visible(false)
	@Override
	public void addPainter(GraphicsPainter aPainter) {
		painters.add(aPainter);
		
	}
	@Visible(false)
	@Override
	public void removePainter(GraphicsPainter aPainter) {
		painters.remove(aPainter);
		
	}
	
	
	@Visible(false)
	@Override
	public void addTelePointerListener(PointListener aListener) {
		telepointerListeners.add(aListener);
		
	}
	@Visible(false)
	@Override
	public void removeTelePointerListener(PointListener aListener) {
		telepointerListeners.remove(aListener);
		
	}
	@Visible(false)
	protected void notifyPainters(Graphics g) {
		for (GraphicsPainter aPainter:painters) {
			aPainter.paint(g);			
		}
	} 
	@Visible(false)
	protected void notifyTelePointerListeners(Point aPoint) {
		for (PointListener aPointListener:telepointerListeners) {
			aPointListener.newPoint(aPoint);			
		}
	}
	@Override
	public boolean isShowTelePointer() {
		return showTelePointer;
	}
	@Override
	public void setShowTelePointer(boolean showTelePointer) {
		this.showTelePointer = showTelePointer;
		repaint();
	}  
	@Override
	public GlassPaneController getGlassPaneController() {
		return glassPaneController;
	}
	@Override
	public void setGlassPaneController(GlassPaneController glassPaneController) {
		this.glassPaneController = glassPaneController;
	}
	public int getPointerSize() {
		return getPointerWidth();
	}
	public void setPointerSize(int pointerSize) {
		setPointerWidth(pointerSize);
		setPointerHeight(pointerSize);
	}
	

}
