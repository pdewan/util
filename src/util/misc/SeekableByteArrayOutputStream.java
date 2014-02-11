package util.misc;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class SeekableByteArrayOutputStream extends ByteArrayOutputStream {
	public byte[] getBuffer() {
		return buf;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int newVal) {
		count = newVal;
		;
	}

	static private byte[] headerBytes;

	public static byte[] getHeaderBytes() {
		return headerBytes;
	}

	static void initializeInitialBytes() {
		SeekableByteArrayOutputStream testStream = new SeekableByteArrayOutputStream();
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					testStream);
			headerBytes = testStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static {
		initializeInitialBytes();
	}
}
