package util.trace;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileToTraceableList {
	
	public static List<Traceable> toTraceableList(String aFileName) {
		try {
		FileInputStream fis = new FileInputStream(aFileName);
        Scanner scanner = new Scanner(fis);  
		
       List<Traceable> retVal = new ArrayList();      
        while(scanner.hasNextLine()){
        	try {
        		String aMessage = scanner.nextLine();
        		if (!aMessage.startsWith("I***"))
        			continue;
        		Class traceableClass = TraceableInfo.toEvtTypeClass(aMessage);
        		Class[] parameterTypes = {String.class}; 
        		Method parsingMethod = traceableClass.getMethod("toTraceable", parameterTypes);
        		Traceable newElement =  (Traceable) parsingMethod.invoke(traceableClass, aMessage);
        		retVal.add(newElement);
        		
        	} catch (Exception e) {
        		e.printStackTrace();
        		
        	}	
        }
        return retVal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	

}
