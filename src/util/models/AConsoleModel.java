package util.models;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.PrintStream;

import javax.swing.JTextArea;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.annotations.Position;
import util.annotations.PreferredWidgetClass;
import util.annotations.Visible;

public class AConsoleModel implements ConsoleModel {
	String input = "";
	StringBuilder output = new StringBuilder("");
	Thread outputThread, errorThread;
	PrintStream printStream;
	Process process;
	String title;
	PropertyChangeSupport propertyChangeSupport;
	public AConsoleModel(Process aProcess, String aTitle) {
		propertyChangeSupport = new PropertyChangeSupport(this);
		process = aProcess;
		title = aTitle;
		printStream = new PrintStream(
				process.getOutputStream());
	    outputThread = new Thread(				
				new AConsoleModelStreamReader("out", process.getInputStream(), this));
		errorThread = new Thread(				
				new AConsoleModelStreamReader("error", process.getErrorStream(),  this));
		outputThread.start();
		errorThread.start();
	}
//	public AConsoleModel(String aTitle) {
//		propertyChangeSupport = new PropertyChangeSupport(this);
//		title = aTitle;
//		printStream = System.out;
//	    outputThread = new Thread(				
//				new AConsoleModelStreamReader("out", System.in, this));
//		errorThread = new Thread(				
//				new AConsoleModelStreamReader("error", System.err,  this));
//		outputThread.start();
//		errorThread.start();
//	}
	@ComponentWidth(1200)
	@Position(1)
	public String getInput() {
		return input;
	}	
	public void setInput(String newVal) {
		addOutput(newVal);
		printStream.println(newVal);
		printStream.flush();
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "input", null, input ));

	}
	@Visible(false)
	public void addOutput(String newVal) {
		output.append(newVal + "\n");
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "output", null, output ));
	}
	@DisplayToString(true)
	@PreferredWidgetClass(JTextArea.class)
	@ComponentWidth(1200)
	@Position(0)
	public StringBuilder getOutput() {
		return output;
	}	
	@Override
	@Visible(false)
	public String getTitle() {
		return title;
	}
	public void exit() {
		process.destroy();
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}
	@Visible(false)
	public void initFrame(Frame aFrame) {
//		aFrame.addWindowListener(this);
	}	
//	@Visible(false)
//	@Override
//	public void windowActivated(WindowEvent arg0) {	}
//	@Visible(false)
//	@Override
//	public void windowClosed(WindowEvent arg0) {}
//	@Visible(false)
//	@Override
//	public void windowClosing(WindowEvent arg0) {
//		exit();
//	}
//	@Visible(false)
//	@Override
//	public void windowDeactivated(WindowEvent arg0) {}
//	@Visible(false)
//	@Override
//	public void windowDeiconified(WindowEvent arg0) {}
//	@Visible(false)
//	@Override
//	public void windowIconified(WindowEvent arg0) {}
//	@Visible(false)
//	@Override
//	public void windowOpened(WindowEvent arg0) {}
}
