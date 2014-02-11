package util.awt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public interface OutputListener {
	public void newGraphicsRequest(SerializableGraphicsRequest graphicsRequest);

	public void newFrameRequest(SerializableFrameRequest frameRequest);
}