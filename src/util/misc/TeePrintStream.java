package util.misc;

import java.io.OutputStream;
import java.io.PrintStream;

public class TeePrintStream extends PrintStream{
	PrintStream stdout;
	boolean echoedPrint;
	
	public TeePrintStream(OutputStream outputStream, PrintStream redirected) {
		super(outputStream);
		stdout = redirected;
	}
	public TeePrintStream(OutputStream outputStream) {
		super(outputStream);
		stdout = System.out;
	}
	/*
	 * println calls print
	 */
	@Override
	public void println(String newLine) {
		echoedPrint = true;
		super.println(newLine);
		stdout.println(newLine);
		
	}
	@Override
	public void println(double aDouble) {
		echoedPrint = true;
		super.println(aDouble);
		stdout.println(aDouble);
	}
	@Override
	public void println(int anInt) {
		echoedPrint = true;
		super.println(anInt);
		stdout.println(anInt);
	}
	@Override
	public void println(boolean aBoolean) {
		echoedPrint = true;
		super.println(aBoolean);
		stdout.println(aBoolean);
	}
	@Override
	public void print(String newLine) {
		super.print(newLine);
		if (!echoedPrint)
		    stdout.print(newLine);
		else 
		 echoedPrint = false;
	}
	@Override
	public void print(double aDouble) {
		super.print(aDouble);
		if (!echoedPrint)
			stdout.print(aDouble);
		else
			echoedPrint = false;

	}
	@Override
	public void print(int anInt) {
		super.print(anInt);
		if (!echoedPrint)
			stdout.print(anInt);
		else
			echoedPrint = false;
	}
	@Override
	public void print(boolean aBoolean) {
		super.print(aBoolean);
		if (!echoedPrint)
			stdout.print(aBoolean);
		else
			echoedPrint = false;
	}
	
	// do we need a println(Object)
	
//	static {
//		stdout = System.out;
//		
//	}

}
