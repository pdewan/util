package util.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
// better for it to be an observable

public class AConsoleModelStreamReader implements Runnable {
	BufferedReader bufferedReader;
	String type;
	ConsoleModel consoleModel;
	public static final String OUTPUT = "out";
	public static final String ERROR = "err";
	boolean isOutput;
	public AConsoleModelStreamReader(String aType, InputStream anInputStream, ConsoleModel aConsoleModel) {
		consoleModel = aConsoleModel;
		bufferedReader = new BufferedReader(
				new InputStreamReader(anInputStream));	
		type = aType;
		isOutput = OUTPUT.equals(type);
	}
	public void run() {
		try {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
//				consoleModel.addOutput(line);
				if (isOutput)
					consoleModel.newOutput(line);
				else
					consoleModel.newError(line);

			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
