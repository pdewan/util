package util.awt;

import util.awt.Painter;
import util.awt.SerializableGraphicsRequest;

public interface LogPainter extends Painter {

	public void add(SerializableGraphicsRequest theRequest);

	public void clear();

}
