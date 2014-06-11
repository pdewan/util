package util.models;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JTextArea;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.annotations.Position;
import util.annotations.PreferredWidgetClass;
import util.annotations.Row;
import util.annotations.Visible;
import util.misc.Common;

public class AConsoleModel implements ConsoleModel {
	String input = "";
	StringBuilder output = new StringBuilder("");
	Thread outputThread, errorThread;
	PrintStream printStream;
	Process process;
	String title;
	String globalTranscriptFile, localTranscriptFile;
	
	
	public static final int CONSOLE_WIDTH = 320;
	public static final int CONSOLE_HEIGHT =350;
	PropertyChangeSupport propertyChangeSupport;
	public AConsoleModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
		
	}
	public AConsoleModel(Process aProcess, String aTitle) {
		this();
		init(aProcess, aTitle);

//		propertyChangeSupport = new PropertyChangeSupport(this);
//		process = aProcess;
//		title = aTitle;
//		printStream = new PrintStream(
//				process.getOutputStream());
//	    outputThread = new Thread(				
//				new AConsoleModelStreamReader("out", process.getInputStream(), this));
//		errorThread = new Thread(				
//				new AConsoleModelStreamReader("error", process.getErrorStream(),  this));
//		outputThread.start();
//		errorThread.start();
	}
	@Override
	public void init(Process aProcess, String aTitle) {	
		process = aProcess;
		title = aTitle;
		setGlobalPrefix();
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
//	@ComponentWidth(1200)
	@ComponentWidth(CONSOLE_WIDTH)
//	@Position(1)
	@Row(1)
	public String getInput() {
		return input;
	}	
	public void setInput(String newVal) {
		addOutput(newVal);
		printStream.println(newVal);
		printStream.flush();		
		// fire the actual value first for other interactors
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "input", null, newVal ));
		// and then the reset value
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "input", null, input ));
		
		
		// put a sleep after each firing, some kind of deadlock occurs

	}
	String globalPrefix;
	protected String globalPrefix() {
		return  "(" + title.trim() + ") ";
	}
	protected void setGlobalPrefix() {
		globalPrefix = globalPrefix();
	}
	@Visible(false)
	public void addOutput(String newVal) {
		String actualOutput = newVal + "\n";
		if (globalTranscriptFile != null)
			try {
				Common.appendText(globalTranscriptFile, globalPrefix + actualOutput);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (localTranscriptFile != null)
			try {
				Common.appendText(localTranscriptFile, actualOutput);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		output.append(actualOutput);
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, OUTPUT_LINE, null, newVal ));

		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "output", null, output ));
	}
	@DisplayToString(true)
	@PreferredWidgetClass(JTextArea.class)
//	@ComponentWidth(1200)
	@ComponentWidth(CONSOLE_WIDTH)
//	@Position(0)
	@Row(0)
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
	@Visible(false)
	@Override
	public String getGlobalTranscriptFile() {
		return globalTranscriptFile;
	}
	@Visible(false)
	@Override
	public void setGlobalTranscriptFile(String transcriptFile) {
		this.globalTranscriptFile = transcriptFile;
	}
	@Override
	@Visible(false)
	public String getLocalTranscriptFile() {
		return localTranscriptFile;
	}
	@Override
	@Visible(false)
	public void setLocalTranscriptFile(String localTranscriptFile) {
		this.localTranscriptFile = localTranscriptFile;
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
