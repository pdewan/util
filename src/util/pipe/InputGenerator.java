package util.pipe;


public interface InputGenerator extends ProcessOutputListener, ProcessInputListener {
	public final String OUTPUT_LINE = ConsoleModel.OUTPUT_LINE;
	public final String INPUT = "input";
//	 void processNewOutputLine(String aProcessName, String aNewOutputLine);
	 void notifyNewInput(String aProcessName, String aNewInput);
	 void addProcessInputListener(ProcessInputListener aListener);	 
	 void removeProcessInputListener(ProcessInputListener aListener);
	  boolean isTerminatedSuccessfully(String aProcess);
	 void setTerminatedSuccessfully(String aProcess, boolean terminatedSuccessfully) ;
	 void addProcessName(String aProcessName);

		void processNamesAdded();
		void executionStarted();

		void notifyInteractionTermination();

		void waitForInteractionTermination();
//		public List<ConsoleModel> getConsoleModels() ;



//		public void setConsoleModels(List<ConsoleModel> consoleModels) ;



		public boolean isTerminated() ;



		public void setTerminated(boolean terminated) ;

}
