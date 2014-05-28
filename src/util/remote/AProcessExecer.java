package util.remote;

import java.io.File;

import util.annotations.Visible;
import util.models.AConsoleModel;
import util.models.ConsoleModel;
import util.trace.Tracer;

public class AProcessExecer implements ProcessExecer {
	Process process;
	String className;
	String args;
	ConsoleModel consoleModel;
	String command;
	String title;
	public AProcessExecer( Class aJavaClass, String anArgs) {
		String currentDir = System.getProperty("user.dir");
		className = aJavaClass.getName();
		args = anArgs;
		String classPath = 
		           System.getProperty("java.class.path");
		classPath += ";" + currentDir;
//        command = "java -cp " + classPath + " " + className + " " + args;
        command = "java -cp \"" + classPath + "\" " + className + " " + args;

        title = aJavaClass.getSimpleName() + " " + args;
	}	
//	public AProcessExecer( String aCommand) {
//		command = aCommand;  
//		title = aCommand;
//	}
	@Visible(false)
	public Process execProcess() {
		try {
		   Runtime rt = Runtime.getRuntime();
		   Tracer.info(this, "Execing command " + command);
		   Tracer.info(this, "Working Directory = " +
		           System.getProperty("user.dir"));
		 
		   File binDirectory = new File ("bin");
		   process = rt.exec(command, null, binDirectory);
		   consoleModel = new AConsoleModel(process, title);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return process;   		
	}
	@Visible (false)
	public Process getProcess() {
			return process;
	}
	public ConsoleModel consoleModel() {
		return consoleModel;
	}
	@Override
	public void destroy() {
		process.destroy();
	}
	public String getTitle() {
		return title;
	}

}
