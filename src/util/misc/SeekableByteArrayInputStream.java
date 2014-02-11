package util.misc;

import java.io.ByteArrayInputStream;

public class SeekableByteArrayInputStream extends ByteArrayInputStream {
	public SeekableByteArrayInputStream(byte[] buf) {
		super(buf);
	}

	public SeekableByteArrayInputStream(byte[] buf, int offset, int length) {
		super(buf, offset, length);
	}

	public byte[] getBuffer() {
		return buf;
	}

	public void setBuffer(byte[] newVal) {
		buf = newVal;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int newVal) {
		count = newVal;
		;
	}

	public int getPosition() {
		return pos;
	}

	public void setPosition(int newVal) {
		pos = newVal;
		;
	}

	public void advancePosition(int increment) {
		pos += increment;
		;
	}
}
