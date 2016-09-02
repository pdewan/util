package util.misc;

import java.io.OutputStream;
import java.io.PrintStream;

public class TeePrintStream extends PrintStream{
	PrintStream stdout;
	
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
	@Override
	public void println(double aDouble) {
		super.println(aDouble);
		stdout.println(aDouble);
	}
	@Override
	public void println(int anInt) {
		super.println(anInt);
		stdout.println(anInt);
	}
	@Override
	public void print(String newLine) {
		super.print(newLine);
		stdout.print(newLine);
	}
	@Override
	public void print(double aDouble) {
		super.print(aDouble);
		stdout.print(aDouble);
	}
	@Override
	public void print(int anInt) {
		super.print(anInt);
		stdout.print(anInt);
	}
	
//	static {
//		stdout = System.out;
//		
//	}

}
