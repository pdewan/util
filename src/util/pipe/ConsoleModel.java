package util.pipe;

import java.awt.Frame;
import java.awt.event.WindowListener;

import util.annotations.Visible;
import util.models.LocalGlobalTranscriptManager;
import util.models.PropertyListenerRegisterer;
import util.models.TerminalModel;

public interface ConsoleModel extends TerminalModel /* PropertyListenerRegisterer/*, WindowListener*/{
	public final String OUTPUT_LINE = "outputLine";
//	public final String OUTPUT = "output";
//	public final String INPUT = "input";


//	public String getInput() ;
	public void addOutput(String newVal) ;
	public void setInput(String newVal);
//	public StringBuilder getOutput() ;
	public String getTitle() ;
	public void exit();
//	void setOutput(StringBuilder newVal);
	void initFrame(Frame aFrame);
//	String getGlobalTranscriptFile();
//	void setGlobalTranscriptFile(String transcriptFile);
	void init(Process aProcess, String aTitle, Class aClass, boolean anIsRdirectIO);
//	String getLocalTranscriptFile();
//	void setLocalTranscriptFile(String localTranscriptFile);
	void newOutput(String anOutput);
	void newError(String anError);
	String getInputPrompt();
	void setInputPrompt(String inputPrompt);
//	void setIndexAndLogDirectory(int index, String aLogDirectory);
	Class getExecedClass();
	void setExecedClass(Class aClass);
	public LocalGlobalTranscriptManager getLocalGlobalTranscriptManager() ;
	public void setLocalGlobalTranscriptManager(
			LocalGlobalTranscriptManager localGlobalTranscriptManager) ;
	String getProcessName();
	void setProcessName(String processName);

}
