package util.awt;

public class AGraphicsFrame extends ADelegateFrame {
	LogPainter logPainter = new ALogPainter();

	public AGraphicsFrame(String theTitle) {
		super(theTitle);
		addPainter(logPainter);
	}

	public void drawLine(int arg0, int arg1, int arg2, int arg3) {
		Object args[] = { arg0, arg1, arg2, arg3 };
		SerializableGraphicsRequest request = new ASerializableGraphicsRequest(
				frameId, SerializableGraphicsRequest.DRAW_LINE, args);
		logPainter.add(request);
	}

	public void drawString(String arg0, int arg1, int arg2) {
		Object args[] = { arg0, arg1, arg2 };
		SerializableGraphicsRequest request = new ASerializableGraphicsRequest(
				frameId, SerializableGraphicsRequest.DRAW_STRING, args);
		logPainter.add(request);
	}

}
