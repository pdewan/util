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
import util.trace.Tracer;
import util.trace.console.ConsoleError;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;
/*
 * Both input and output threads are accessing, so syncchronized
 */
public class ALocalGlobalTranscriptManager  implements LocalGlobalTranscriptManager  {
//	public static final String DEFAULT_INPUT_PROMPT = "$ ";
//	public static final String OUT_SUFFIX = "Output";
//	public static final String ERROR_SUFFIX = "Input";
//	String input = "";
//	StringBuilder output = new StringBuilder("");
//	Thread outputThread, errorThread;
//	PrintStream printStream;
//	Process process;
//	String title;
	String globalTranscriptFile, localTranscriptFile;
//	String inputPrompt = DEFAULT_INPUT_PROMPT;
	int index;
	String logDirectory;
	String processName;
//	Class execedClass;
	

//
//	public static final int CONSOLE_WIDTH = 320;
//	public static final int CONSOLE_HEIGHT =350;
//	PropertyChangeSupport propertyChangeSupport;
	public ALocalGlobalTranscriptManager(String aProcessName) {
		setProcessName(processName);
//		propertyChangeSupport = new PropertyChangeSupport(this);
		
	}
	public ALocalGlobalTranscriptManager() {
	
		
	}
//	public ALocalGlobalTranscriptManager( String aProcessName) {
//		this();
////		init(aProcess, aTitle, aClass);
//
////		propertyChangeSupport = new PropertyChangeSupport(this);
////		process = aProcess;
////		title = aTitle;
////		printStream = new PrintStream(
////				process.getOutputStream());
////	    outputThread = new Thread(				
////				new AConsoleModelStreamReader("out", process.getInputStream(), this));
////		errorThread = new Thread(				
////				new AConsoleModelStreamReader("error", process.getErrorStream(),  this));
////		outputThread.start();
////		errorThread.start();
//	}
//	@Override
//	public void init(Process aProcess, String aTitle, Class aClass) {
//		execedClass = aClass;
//		process = aProcess;
//		title = aTitle;
//		setLocalTranscriptFile();
//		setGlobalPrefix();
//		
//	}
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
////	@ComponentWidth(1200)
//	@ComponentWidth(CONSOLE_WIDTH)
////	@Position(1)
//	@Row(1)
//	public String getInput() {
//		return input;
//	}	
//	public synchronized void setInput(String newVal) {
//		addOutput(inputPrompt + newVal);
//		printStream.println(newVal);
//		printStream.flush();		
//		// fire the actual value first for other interactors
//		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "input", null, newVal ));
//		if (Tracer.isInfo(newVal))
//			return;
//		ConsoleInput consoleInput = ConsoleInput.newCase(newVal, this);
//		String infoString = Tracer.toInfo(consoleInput, consoleInput.getMessage());
//		if (infoString != null)
//			addOutput(infoString);
//		// and then the reset value
//		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "input", null, input ));
//		
//		
//		// put a sleep after each firing, as some kind of deadlock occurs
//
//	}
//	@Override
//	@Visible(false)
//	public synchronized void newOutput(String anOutput) {
//		addOutput(anOutput);
//		if (Tracer.isInfo(anOutput))
//			return;
//		ConsoleOutput consoleOutput = ConsoleOutput.newCase(anOutput, this);
//		String infoString = Tracer.toInfo(consoleOutput, consoleOutput.getMessage());
//		if (infoString != null)
//			addOutput(infoString);
//		
//	}
//	@Override
//	@Visible(false)
//	public synchronized void newError(String anError) {
//		addOutput(anError);
//		if (Tracer.isInfo(anError))
//			return;
//		ConsoleError consoleError = ConsoleError.newCase(anError, this);
//		String infoString = Tracer.toInfo(consoleError, consoleError.getMessage());
//		if (infoString != null)
//			addOutput(infoString);
//	}
	
	String globalPrefix;
	protected String globalPrefix() {
		return  "(" + processName.trim() + ") ";
	}
	protected void setGlobalPrefix() {
		globalPrefix = globalPrefix();
	}
	@Visible(false)
	public synchronized void addOutput(String newVal) {
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
//		output.append(actualOutput);
//		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, OUTPUT_LINE, null, newVal ));
//
//		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "output", null, output ));
	}
//	@DisplayToString(true)
//	@PreferredWidgetClass(JTextArea.class)
////	@ComponentWidth(1200)
//	@ComponentWidth(CONSOLE_WIDTH)
////	@Position(0)
//	@Row(0)
//	public StringBuilder getOutput() {
//		return output;
//	}	
//	@Override
//	@Visible(false)
//	public String getTitle() {
//		return title;
//	}
//	public void exit() {
//		process.destroy();
//	}
//	@Override
//	public void addPropertyChangeListener(PropertyChangeListener aListener) {
//		propertyChangeSupport.addPropertyChangeListener(aListener);
//	}
//	@Visible(false)
//	public void initFrame(Frame aFrame) {
////		aFrame.addWindowListener(this);
//	}	
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
//	@Override
//	@Visible(false)
//	public String getInputPrompt() {
//		return inputPrompt;
//	}
//	@Override
//	@Visible(false)
//	public void setInputPrompt(String inputPrompt) {
//		this.inputPrompt = inputPrompt;
//	}
	@Visible(false)
	public int getIndex() {
		return index;
	}
	void setLocalTranscriptFile() {
		if (processName == null || logDirectory == null) return;
		setLocalTranscriptFile (getLocalTranscriptFileName(logDirectory, index, getProcessName()));
		try {
			Common.clearOrCreateFile(getLocalTranscriptFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void setIndexAndLogDirectory(int index, String aLogDirectory) {
		this.index = index;
		logDirectory = aLogDirectory;
		setLocalTranscriptFile();

	}
	@Visible(false)
	public String getLogDirectory() {
		return logDirectory;
	}
	public void setLogDirectory(String logDirectory) {
		this.logDirectory = logDirectory;
	}
	
	public static final String INDEX_SUFFIX = "_";
	public static final String TRANSCRIPT_FILE_SUFFIX = ".txt";
	public static final String GLOBAL_FILE_NAME = "globalTranscript.txt";

	public static String getLocalTranscriptFileName(String aDirectory, Integer anIndex, Class aClass) {
		return (aDirectory + "/" + anIndex + "_" + aClass.getSimpleName() + TRANSCRIPT_FILE_SUFFIX).trim() ;
	}
	
	public static String getLocalTranscriptFileName(String aDirectory, Integer anIndex, String aTitle) {
		return ( aDirectory + "/" + anIndex + "_" + aTitle + TRANSCRIPT_FILE_SUFFIX).trim() ;
	}
	
	public static String getClassName(String aLocalTranscriptFileName) {
		int aStart = aLocalTranscriptFileName.indexOf(INDEX_SUFFIX) + 1;
		int anEnd = aLocalTranscriptFileName.indexOf(TRANSCRIPT_FILE_SUFFIX);
		return aLocalTranscriptFileName.substring(aStart, anEnd);
	}
	
	public static String getTitle(String aLocalTranscriptFileName) {
		int aStart = aLocalTranscriptFileName.indexOf(INDEX_SUFFIX) + 1;
		int anEnd = aLocalTranscriptFileName.indexOf(TRANSCRIPT_FILE_SUFFIX);
		return aLocalTranscriptFileName.substring(aStart, anEnd);
	}
	
	
	public static String getGlobalTranscriptFileName (String aDirectory) {
		return aDirectory + "/" + GLOBAL_FILE_NAME;
	}
	
//	public String toString() {
//		return title;
//	}
//	@Override
//	@Visible(false)
//	public Class getExecedClass() {
//		return execedClass;
//	}
//	@Override
//	@Visible(false) 
//	public void setExecedClass(Class aClass) {
//		execedClass = aClass;
//	}
	@Override
	public String getProcessName() {
		return processName;
	}
	@Override
	public void setProcessName(String newVal) {
		processName = newVal;
		setGlobalPrefix();
		setLocalTranscriptFile();
		
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
