package util.pipe;


public interface OutputBasedInputGenerator extends ProcessOutputListener {
	public final String OUTPUT_LINE = ConsoleModel.OUTPUT_LINE;
	public final String INPUT = "input";
//	 void processNewOutputLine(String aProcessName, String aNewOutputLine);
	 void notifyNewInput(String aProcessName, String aNewInput);
	 void addProcessInputListener(ProcessInputListener aListener);	 
	 void removeProcessInputListener(ProcessInputListener aListener);
	  boolean isTerminatedSuccessfully(String aProcess);
	 void setTerminatedSuccessfully(String aProcess, boolean terminatedSuccessfully) ;

}
