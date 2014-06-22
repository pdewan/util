package util.models;

import java.awt.Frame;
import java.awt.event.WindowListener;

public interface ConsoleModel extends PropertyListenerRegisterer/*, WindowListener*/{
	public final String OUTPUT_LINE = "outputLine";
	public String getInput() ;
	public void addOutput(String newVal) ;
	public void setInput(String newVal);
	public StringBuilder getOutput() ;
	public String getTitle() ;
	public void exit();
//	void setOutput(StringBuilder newVal);
	void initFrame(Frame aFrame);
	String getGlobalTranscriptFile();
	void setGlobalTranscriptFile(String transcriptFile);
	void init(Process aProcess, String aTitle);
	String getLocalTranscriptFile();
	void setLocalTranscriptFile(String localTranscriptFile);
	void newOutput(String anOutput);
	void newError(String anError);
	String getInputPrompt();
	void setInputPrompt(String inputPrompt);

}
