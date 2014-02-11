package util.awt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GlassPaneTest {
	public static void main(String[] args) {
	    JFrame frame = new JFrame("glass pane test");
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(4,1));
	    panel.add(new JButton("my button"));
	    panel.add(new JLabel("my button"));
	    panel.add(new JButton("my button"));
	    frame.getContentPane().add(panel);
	    frame.pack();
	    frame.setVisible(true);


	    LabelGlassPane glass = new LabelGlassPane(frame);
	    frame.setGlassPane(glass);
	    glass.setVisible(true);
	}


	}

	class LabelGlassPane extends JComponent {
	    public LabelGlassPane(JFrame frame) {
	      this.frame = frame;
	     //this.addMouseListener(new MouseAdapter() {         });
	    }
	    public JFrame frame;
	    public void paint(Graphics g) {
	        g.setColor(Color.red);
	        Container root = frame.getContentPane();
	        g.setColor(new Color(100,100,100,100));
	        rPaint(root,g,0,0);
	    }
	    private void rPaint(Container cont, Graphics g, int offX, int offY) {
	        for(int i=0; i<cont.getComponentCount(); i++) {
	            Component comp = cont.getComponent(i);
	            int x = offX + comp.getX();
	            int y = offY + comp.getY();
	            if(!(comp instanceof JPanel)) {
	                int w = comp.getWidth();
	                int h = comp.getHeight();
	                g.drawRect(x+4,y+4,w-8,h-8);
	                String name = comp.getClass().getName().replaceAll(".*?\\.", "");
	                g.drawString(name,x+4+6,y+g.getFontMetrics().getHeight());
	            }
	            if(comp instanceof Container) {
	                rPaint((Container)comp,g,x,y);
	            }
	        }
	    }

}
