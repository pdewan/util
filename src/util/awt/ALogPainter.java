package util.awt;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import util.awt.ADelegateFrame;
import util.awt.DelegateFramePainter;
import util.awt.SerializableGraphicsRequest;

public class ALogPainter implements LogPainter {
	List<SerializableGraphicsRequest> requests = new ArrayList();

	@Override
	public void paint(ADelegateFrame theFrame, Graphics g) {
		for (SerializableGraphicsRequest request : requests) {
			paint(theFrame, g, request);
		}

	}

	public synchronized void add(SerializableGraphicsRequest theRequest) {
		System.err.println("request added:" + theRequest);
		requests.add(theRequest);
	}

	public synchronized void clear() {
		System.err.println("Log Cleared");
		requests.clear();
	}

	synchronized void paint(ADelegateFrame theFrame, Graphics g,
			SerializableGraphicsRequest theRequest) {
		if (theRequest.getSource() != theFrame.getID())
			return;
		Object[] args = theRequest.getArgs();
		if (theRequest.getName().equals(theRequest.DRAW_LINE)) {
			g.drawLine((Integer) args[0], (Integer) args[1], (Integer) args[2],
					(Integer) args[3]);
		} else if (theRequest.getName().equals(theRequest.DRAW_RECT)) {
			g.drawRect((Integer) args[0], (Integer) args[1], (Integer) args[2],
					(Integer) args[3]);
		} else if (theRequest.getName().equals(theRequest.DRAW_OVAL)) {
			g.drawOval((Integer) args[0], (Integer) args[1], (Integer) args[2],
					(Integer) args[3]);
		} else if (theRequest.getName().equals(theRequest.DRAW_STRING)) {
			g.drawString((String) args[0], (Integer) args[1], (Integer) args[2]);
		}
	}

}
