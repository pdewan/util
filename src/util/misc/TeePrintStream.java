package util.misc;

import java.io.OutputStream;
import java.io.PrintStream;

public class TeePrintStream extends PrintStream{
	static PrintStream stdout;
	
	public TeePrintStream(OutputStream outputStream, PrintStream redirected) {
		super(outputStream);
		stdout = redirected;
	}
	public TeePrintStream(OutputStream outputStream) {
		super(outputStream);
		stdout = System.out;
	}
	@Override
	public void println(String newLine) {
		super.println(newLine);
		stdout.println(newLine);
	}
	
//	static {
//		stdout = System.out;
//		
//	}

}
