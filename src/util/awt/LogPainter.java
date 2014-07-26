package util.awt;

import util.awt.DelegateFramePainter;
import util.awt.SerializableGraphicsRequest;

public interface LogPainter extends DelegateFramePainter {

	public void add(SerializableGraphicsRequest theRequest);

	public void clear();

}
