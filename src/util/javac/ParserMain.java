package util.javac;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Map;

import javax.tools.JavaFileObject;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.SourcePositions;

import util.misc.Common;
import demo.codeanalyzer.common.model.ClassFile;
import demo.codeanalyzer.common.model.ClassModelMap;
import demo.codeanalyzer.common.model.JavaSourceTreeInfo;
import demo.codeanalyzer.common.model.Location;
import demo.codeanalyzer.processor.CodeAnalyzerController;

/**
 * The main class to verify java files using custom annotation processor. The
 * files to be verified can be supplied to this class as comma-separated
 * argument.
 * 
 * @author Seema Richard (Seema.Richard@ust-global.com)
 * @author Deepa Sobhana (Deepa.Sobhana@ust-global.com)
 */
public class ParserMain {

    /**
     * The main method accepts the file(s) for verification. Multiple files can
     * be verified by passing the absolute path of the files as comma-separated
     * argument. This method invokes the custom annotation processor to process
     * the annnotations in the supplied file(s). Verification results will be
     * printed on to the console.
     * 
     * @param args
     *            The java source files to be verified.
     */
	
    public static void main(String[] args) {
    	parse(args, ".");

//        try {
//
//            if (args.length < 1 || args[0] == null ||
//                    args[0].trim().length() <= 0 ) {
//				System.out.println("Please provide the java source file(s) "
//						+ "to be verified as argument");                
//                System.out.println("Usage: java Main {<comma separated list of source files>}");
//                System.out.println("Exiting from the program");
//                System.exit(0);
//            } else {
//                CodeParserController controller = new CodeParserController();
//				controller.invokeProcessor(args[0], new CodeParserProcessor());
//				ClassFile aClassFile = ClassModelMap.getInstance().getClassInfo(Algorithms.class.getName());
//				Location locationInfo = aClassFile.getLocationInfo();
//				JavaSourceTreeInfo javaSourceTreeInfo = aClassFile.getSourceTreeInfo();
//				SourcePositions classPositions = javaSourceTreeInfo.getSourcePos();
////				int startOffset = classPositions.
//				 CompilationUnitTree compileTree = javaSourceTreeInfo.getCompileTree();
//
//				JavaFileObject file = compileTree.getSourceFile();
//	            String javaFileContent = file.getCharContent(true).toString();
//	            //Convert the java file content to character buffer
//	            CharBuffer buffer = getCharacterBufferOfSource(javaFileContent);
//	            CharBuffer classCode = buffer.subSequence(locationInfo.getStartOffset(),  locationInfo.getEndOffset());
//	            System.out.println(classCode);
//            }
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
    }
    public static void parse(Class[] args, String aClassPath) {
    	String[] retVal = new String[args.length];
    	for (int i = 0; i < args.length; i++) {
    		retVal[i] = Common.classToSourceFileNameInSrc(args[i]);
    	}
    	parse(retVal, aClassPath);
    	
    }
    
    public static void parse(Class aClass, String aClassPath) {
    	parse (new Class[] {aClass}, aClassPath);
    	
    }
    
    public static void parseClass(String aClassName, String aClassPath) {
//    	parse (new String[] {Common.classNameToSourceFileNameInSrc(aClassName)});
    	parseFile(Common.classNameToSourceFileNameInSrc(aClassName), aClassPath);
    	
    }
    
    public static void parseClassNoByteCode(String aClassName, String aClassPath) {
//    	parse (new String[] {Common.classNameToSourceFileNameInSrc(aClassName)});
    	parseFileNoByteCode(Common.classNameToSourceFileNameInSrc(aClassName), aClassPath);
    	
    }
//    public static void parseClassNoByteCode(String aClassName, StringBuffer text) {
////    	parse (new String[] {Common.classNameToSourceFileNameInSrc(aClassName)});
//    	parseFileNoByteCode(Common.classNameToSourceFileNameInSrc(aClassName));
//    	
//    }
//    

    public static void parse(String[] args, String aClassPath) {

        try {

            if (args.length < 1 || args[0] == null ||
                    args[0].trim().length() <= 0 ) {
				System.out.println("Please provide the java source file(s) "
						+ "to be verified as argument");                
                System.out.println("Usage: java Main {<comma separated list of source files>}");
                System.out.println("Exiting from the program");
                System.exit(0);
            } 
            else {
            	parseFile(args[0],aClassPath);
//				ClassFile aClassFile = SourceClassManager.getInstance().getClassInfo(args[0]);
//				if (aClassFile != null) return;
//                CodeParserController controller = new CodeParserController();
//				controller.vanillaInvokeProcessor(args[0], new CodeParserProcessor());

            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static void parseFile(String aFileName, String aClassPath) {

     
				ClassFile aClassFile = SourceClassManager.getInstance().getClassInfo(aFileName);
				if (aClassFile != null) return;
                CodeParserController controller = new CodeParserController();
				controller.invokeProcessorFileByteCode(aFileName, aClassPath, new CodeParserProcessor());

            }
    
    public static void parseFileNoByteCode(String aFileName, String aClassPath) {

        
		ClassFile aClassFile = SourceClassManager.getInstance().getClassInfo(aFileName);
		if (aClassFile != null) return;
        CodeParserController controller = new CodeParserController();
		controller.invokeProcessorNoByteCodeMaybeResource(aFileName, aClassPath, new CodeParserProcessor());
		

    }
    
    public static void parseClassNoByteCode(String aClassName, StringBuffer aSource, String aClassPath) {

        
  		ClassFile aClassFile = SourceClassManager.getInstance().getClassInfo(aClassName);
  		if (aClassFile != null) return;
          CodeParserController controller = new CodeParserController();
  		controller.invokeProcessorNoByteCode(aClassName, aSource, aClassPath, new CodeParserProcessor());
  		

      }
 public static byte[] compile(String aClassName,  StringBuffer aSource, String aClassPath) {

        
//  		ClassFile aClassFile = SourceClassManager.getInstance().getClassInfo(aClassName);
//  		if (aClassFile != null) return;
          CodeParserController controller = new CodeParserController();
  		return controller.compileInMemory(aClassName, aClassPath, aSource, new CodeParserProcessor());
  		

      }
    
 public static Map<String, byte[]> compile(Map<String, StringBuffer> aClassTexts, String aClassPath) {

     
//	ClassFile aClassFile = SourceClassManager.getInstance().getClassInfo(aClassName);
//	if (aClassFile != null) return;
   CodeParserController controller = new CodeParserController();
	return controller.compileInMemory(aClassTexts, aClassPath, new CodeParserProcessor());
	

}
        
    

    private static CharBuffer getCharacterBufferOfSource(String javaFile) {
        CharBuffer charBuffer = CharBuffer.wrap (javaFile.toCharArray());
        return charBuffer;
    }    

}
