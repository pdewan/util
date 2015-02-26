package util.remote;

import java.io.File;

import util.annotations.Tags;
import util.annotations.Visible;
import util.pipe.AConsoleModel;
import util.pipe.ConsoleModel;
import util.trace.Tracer;

public class AProcessExecer implements ProcessExecer {
	Process process;
	Class execedClass;
	String className;
	String args;
	ConsoleModel consoleModel;
	
	String[] commandComponents;
	String command;
	String title;
	boolean redirectIO;
	public AProcessExecer( Class aJavaClass, String anArgs, boolean anIsRdirectIO) {
		String currentDir = System.getProperty("user.dir");
		className = aJavaClass.getName();
		execedClass = aJavaClass;
		args = anArgs;
		String classPath = 
		           System.getProperty("java.class.path");
		classPath += ";" + currentDir;
//        command = "java -cp " + classPath + " " + className + " " + args;
        command = "java -cp \"" + classPath + "\" " + className + " " + args;

//        title = aJavaClass.getSimpleName() + " " + args;
        title = toTitle (aJavaClass, args);
        redirectIO = anIsRdirectIO;
	}
	public AProcessExecer( String aCommand, boolean anIsRdirectIO) {
		command = aCommand;
        redirectIO = anIsRdirectIO;
	}
	
	public AProcessExecer( String[] aCommandComponents, boolean anIsRdirectIO) {
		commandComponents = aCommandComponents;
        redirectIO = anIsRdirectIO;
	}
	
	
	protected String toTitle(Class aJavaClass, String anArgs) {
		Tags aTags =  (Tags) aJavaClass.getAnnotation(Tags.class);
		if (aTags == null || aTags.value().length == 0)
		return aJavaClass.getSimpleName()  + anArgs;
		String aTagTitle = "";
		String[] aTagArray = aTags.value();
//		for (String aTag: aTags.value()) {
		for (int i = 0; i < aTagArray.length; i++) {
			String aTag = aTagArray[i];
			aTagTitle += 
					(i == 0)? aTag: " " + aTag;
		}
		return aTagTitle + anArgs;
	}
	
//	public AProcessExecer( String aCommand) {
//		command = aCommand;  
//		title = aCommand;
//	}
	@Visible(false)
	public Process execProcess() {
		try {
		   Runtime rt = Runtime.getRuntime();
		   String workingDirectory = System.getProperty("user.dir");
//		   Tracer.info(this, "Working Directory = " +
//		           System.getProperty("user.dir"));
		   Tracer.info(this, "Working Directory = " +
		           workingDirectory);
		 
//		   File binDirectory = null;
		   String binName = null;
		   File binDirectory = null;
		   if (workingDirectory.endsWith("bin")) {
//				   binName = ".";
//		   		    binName = workingDirectory;	
		   binDirectory = new File(workingDirectory);

		   } else {
			   binName =  workingDirectory + "/bin";	
			   binDirectory = new File(binName);
			   if (!binDirectory.exists())
				   binDirectory = new File(workingDirectory);
		   }
//		   File binDirectory = new File(binName);
//		   if (!binDirectory.exists()) {
//			   System.err.println("Working directrory does not exist!" + binDirectory);
//			   return null;
//		   }
		   if (command != null) {
			   Tracer.info(this, "Execing command " + command);
			   process = rt.exec(command, null, binDirectory);
		   } else if (commandComponents != null ) {
			   Tracer.info(this, "Execing command " + commandComponents);
			   process = rt.exec(commandComponents, null, binDirectory);

		   } else {
			   System.err.println("Null command or command components");
			   return null;
		   }
//		   consoleModel = new AConsoleModel(process, title);
		   if (consoleModel == null)
			   consoleModel = new AConsoleModel(process, title, execedClass, true);
		   else
			   consoleModel.init(process, title, execedClass, redirectIO);
			   
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
	@Override
	public ConsoleModel getConsoleModel() {
		return consoleModel;
	}
	@Override
	public void setConsoleModel(ConsoleModel consoleModel) {
		this.consoleModel = consoleModel;
	}

}
