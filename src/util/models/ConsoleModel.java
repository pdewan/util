package util.models;

import java.awt.Frame;
import java.awt.event.WindowListener;

public interface ConsoleModel extends PropertyListenerRegisterer/*, WindowListener*/{
	public String getInput() ;
	public void addOutput(String newVal) ;
	public void setInput(String newVal);
	public StringBuilder getOutput() ;
	public String getTitle() ;
	public void exit();
//	void setOutput(StringBuilder newVal);
	void initFrame(Frame aFrame);

}
