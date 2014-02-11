package util.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AConsoleModelStreamReader implements Runnable {
	BufferedReader bufferedReader;
	String type;
	ConsoleModel consoleModel;
	public AConsoleModelStreamReader(String aType, InputStream anInputStream, ConsoleModel aConsoleModel) {
		consoleModel = aConsoleModel;
		bufferedReader = new BufferedReader(
				new InputStreamReader(anInputStream));		
	}
	public void run() {
		try {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				consoleModel.addOutput(line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
