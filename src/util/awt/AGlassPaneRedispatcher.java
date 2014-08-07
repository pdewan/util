package util.awt;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class AGlassPaneRedispatcher implements GlassPaneRedispatcher{
	JComponent glassPane;
	JFrame frame;
	Point lastPosition;
	boolean menuActive;
	boolean inMenu;
	JMenu menu;
	public AGlassPaneRedispatcher(JComponent aGlassPane, JFrame aFrame) {
		glassPane = aGlassPane;
		frame = aFrame;
//		glassPane.addKeyListener(this);
		glassPane.addMouseListener(this);
		glassPane.addMouseMotionListener(this);
//		glassPane.addKeyListener(this); // key events never come here
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		redispatchEvent(e, e.getPoint());

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		redispatchEvent(e, e.getPoint());

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		lastPosition = e.getPoint();		
//		if (inMenu) {
//			JPopupMenu popupMenu = menu.getPopupMenu();
//			if (popupMenu != null && popupMenu.isVisible() && !popupMenu.contains(e.getPoint())) {
//			
//					inMenu = false; 
//					System.out.println("not in menu");
//					popupMenu.setVisible(false);
//				
//			}
//			
//		}
//		if (inMenu && menu.contains(e.getPoint())) {
//			menuActive = true;
//		}
//		else if (inMenu && !menu.contains(e.getPoint())) {
////			inMenu = false; // last event will remove menu
//			menuActive = false;
//			System.out.println("not in menu");
//			
//		}
		redispatchEvent(e, e.getPoint());

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		redispatchEvent(e, e.getPoint());

	}
	@Override
	public void mouseExited(MouseEvent e) {
		redispatchEvent(e, e.getPoint());
//		glassPane.setVisible(false);


	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (inMenu) {
			JPopupMenu popupMenu = menu.getPopupMenu();
//			System.out.println("Popup menu bounds:" + popupMenu.getBounds());

		   
			if (popupMenu != null) {
				 Point offsetFromContentPane = SwingUtilities.convertPoint(
                         glassPane,
                         e.getPoint(),
                         frame.getContentPane());
				 
			
				if ( !popupMenu.contains(offsetFromContentPane)) {
			
			
					inMenu = false; 
//					System.out.println("not in menu");
					popupMenu.setVisible(false);
				}
				
			}
			
		}
		redispatchEvent(e, e.getPoint());

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		redispatchEvent(e, e.getPoint());

	}
	@Override
	public void keyPressed(KeyEvent e) {
		redispatchEvent(e, lastPosition);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		redispatchEvent(e, lastPosition);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		redispatchEvent(e, lastPosition);
		
	}
	void redispatchEvent(InputEvent anInputEvent, Point aGlassPanePoint) {
//		System.out.println ("redispatch event");
		
		if (anInputEvent.getSource() != glassPane) {
			System.out.println (anInputEvent.getSource());
		}
	    Component convertFromComponent = frame.getContentPane();

		if (inMenu && anInputEvent instanceof MouseEvent) {
//			menu.dispatchEvent(SwingUtilities.convertMouseEvent(glassPane, (MouseEvent) anInputEvent, menu));
//			System.out.println ("didpatching to menu");
//			return;
//			System.out.println("In Menu mouse event");
//			convertFromComponent = menu;
			MouseEvent mouseEvent = (MouseEvent)  anInputEvent;
//			System.out.println("Y:"  +mouseEvent.getY());
//			menu.dispatchEvent(SwingUtilities.convertMouseEvent(glassPane, mouseEvent, menu));
			JPopupMenu popupMenu = menu.getPopupMenu();
//			System.out.println("popup menu:" +popupMenu.getBounds() + "  position:" + popupMenu.getLocation());
			
//			if (popupMenu != null && !popupMenu.contains(mouseEvent.getPoint())) {
//				inMenu = false; 
//				System.out.println("not in menu");
//				if (popupMenu.isVisible())
//				popupMenu.setVisible(false);
//				
//			} else 
				if (popupMenu.isVisible()) {
					
					
				popupMenu.dispatchEvent(SwingUtilities.convertMouseEvent(glassPane, mouseEvent, frame.getContentPane()));
			
			} else {
//				System.out.println("pop up not visible");
				menu.dispatchEvent(SwingUtilities.convertMouseEvent(glassPane, mouseEvent, menu));
			}
			return;
			

		}
//	    Container convertFromContainer = frame.getContentPane();
//	    Component blockedComponent;
	  

	    Point convertFromPoint = SwingUtilities.convertPoint(
	                                    glassPane,
	                                    aGlassPanePoint,
	                                    convertFromComponent);
	   
//	    Point convertFromPoint = contentPanePoint;

	        if (convertFromPoint.y < 0) {
	        	convertFromComponent = frame.getJMenuBar();
	        	convertFromPoint = SwingUtilities.convertPoint(glassPane,
	        			aGlassPanePoint, convertFromComponent);
	        }
//	        	
//	        	blockedComponent = SwingUtilities.getDeepestComponentAt(blockedComponent,
//	        			convertFromContainer.x, convertFromContainer.y);
//	          
//	        } else {
//	        	   blockedComponent = SwingUtilities.getDeepestComponentAt(contentPane,
//	       	            contentPanePoint.x, contentPanePoint.y);
//	        }
	        if (convertFromComponent == null || convertFromPoint == null)
	        	return;
	        Component blockedComponent = SwingUtilities.getDeepestComponentAt(convertFromComponent,
        			convertFromPoint.x, convertFromPoint.y);
	        if (blockedComponent == null)
	        	return;
	        InputEvent aCopiedEvent = null;

	        if (anInputEvent instanceof KeyEvent) {
	        	KeyEvent aKeyEvent = (KeyEvent) anInputEvent;
	        	aCopiedEvent = new KeyEvent(blockedComponent,
	        			anInputEvent.getID(),
	        			anInputEvent.getWhen(), 
	        			anInputEvent.getModifiers(), 
	        			aKeyEvent.getKeyCode(),
	        			aKeyEvent.getKeyChar());   	
	        	
	        	
	        } else if (anInputEvent instanceof MouseEvent) {
	        	Point blockedComponentPoint = SwingUtilities.convertPoint(convertFromComponent,
		              convertFromPoint, blockedComponent);
	        	MouseEvent aMouseEvent = (MouseEvent) anInputEvent;
	        	aCopiedEvent = new MouseEvent(blockedComponent, 
	        			anInputEvent.getID(),
	        			anInputEvent.getWhen(), 
	        			anInputEvent.getModifiers(), 
	        			blockedComponentPoint.x,
	        			blockedComponentPoint.y, 
	        			aMouseEvent.getClickCount(),
	        			aMouseEvent.isPopupTrigger());
	        
	        //XXX: If the event is from a component in a popped-up menu,
	        //XXX: then the container should probably be the menu's
	        //XXX: JPopupMenu, and containerPoint should be adjusted
	        //XXX: accordingly.
//	        
//	        if (inMenuBar || inButton || inDrag) {
//	          Point componentPoint = SwingUtilities.convertPoint(this,
//	              glassPanePoint, blockedComponent);
	          
	        } 
//	        System.out.println("ReDispatching to:" + blockedComponent + "event " + aCopiedEvent );	 
	        if (blockedComponent instanceof JMenu) {
	        	inMenu = true;
	        	menu = (JMenu) blockedComponent;
//	        	System.out.println("inMenu:");
	        } 
//	        else {
//	        inMenu = false;
//	        }
	        blockedComponent.dispatchEvent(aCopiedEvent);
//	        if (inMenu)
//        	System.out.println("Dispatched to menu:");

		
	}
//	@Override
//	public void focusGained(FocusEvent arg0) {
//	
//		
//	}
//	@Override
//	public void focusLost(FocusEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

}
