package util.awt;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class AVerticalTextField extends JPanel implements TextComponentInterface, KeyListener  {
	int eventId;
	StringBuffer stringBuffer;
	List<ActionListener> actionListeners = new ArrayList();
	public static final int X_OFFSET = 10;
	public static final int CARAT_WIDTH = 10;
	public static final int ROUND_RECTANGLE_ARC_WIDTH = 20;
	public static final int ROUND_RECTANGLE_ARC_HEIGHT = 20;
	Color windowBackground;	
	public AVerticalTextField(String aString) {
		stringBuffer = new StringBuffer(aString);
		this.addKeyListener(this);
//		this.addMouseListener(this);
		this.setFocusable(true);
		windowBackground = this.getBackground();
//		this.setBackground(Color.WHITE);
//		this.setOpaque(false);
//		this.addMouseListener(this);
//		enableEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK);
		
	}
	public void addActionListener(ActionListener actionListener) {
		if (actionListeners.contains(actionListener)) return;
		actionListeners.add(actionListener);
	}
	public void notifyActionListeners() {
		for (ActionListener actionListener: actionListeners) {
			actionListener.actionPerformed(new ActionEvent(this, eventId, stringBuffer.toString()));
		}
		eventId++;	
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {
		if (!isEnabled()) return;
		if (keyEvent.getKeyChar() == '\n') {
			notifyActionListeners();
			return;
		}
		stringBuffer.append(keyEvent.getKeyChar());	
//		repaint();	
		getParent().repaint();		
	}
	public void paint(Graphics g) {
		super.paint(g);		
		Font font = g.getFont();
		FontMetrics metrics = g.getFontMetrics(font);
		int height = metrics.getHeight();
		if (isEnabled()) {
			g.setColor(Color.WHITE);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), ROUND_RECTANGLE_ARC_WIDTH,ROUND_RECTANGLE_ARC_WIDTH);			
			g.setColor(Color.BLACK);
			g.drawLine(X_OFFSET, height* (stringBuffer.length() + 1), X_OFFSET + CARAT_WIDTH, height* (stringBuffer.length() + 1));
		} else {

			g.setColor(windowBackground);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), ROUND_RECTANGLE_ARC_WIDTH, ROUND_RECTANGLE_ARC_WIDTH);
		}
		g.setColor(Color.BLACK);
		for(int i = 0; i < stringBuffer.length(); i++) {
			char drawnChar = stringBuffer.charAt(i);

			g.drawString("" + drawnChar, X_OFFSET, height*(i + 1));
		}	
	}
	@Override
	public String getText() {
		return stringBuffer.toString();
	}
	
	@Override
	public void setText(String newVal) {		
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append(newVal);
		if (getParent() != null)
		getParent().repaint();
		else
			repaint();
		
	}
	
	
//	@Override
//	public void processKeyEvent(KeyEvent keyEvent) {
//		if (keyEvent.isActionKey()) {
//			notifyActionListeners();
//			return;
//		}
//		stringBuffer.append(keyEvent.getKeyChar());	
//		repaint();
//	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main (String[] args) {
		Frame frame = new Frame();
		frame.setLayout(new GridLayout(1, 2));
		AVerticalTextField field1 = new AVerticalTextField("hello");
		AVerticalTextField field2 = new AVerticalTextField("goodbye");
		field1.setText("hello hello");
//		field1.setSize(100, 100);
//		field2.setSize(100,100);
		frame.add(field1);
		frame.add(field2);
		frame.setSize(100, 400);
		frame.setVisible(true);
		
	}
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
	

}
