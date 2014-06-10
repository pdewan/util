package util.remote;
import util.models.ConsoleModel;
public interface ProcessExecer {	
	public Process execProcess() ;	
	public Process getProcess();	
	public ConsoleModel consoleModel();
	void destroy();
	ConsoleModel getConsoleModel();
	void setConsoleModel(ConsoleModel consoleModel);
}
