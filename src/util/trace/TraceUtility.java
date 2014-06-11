package util.trace;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TraceUtility {
	
	public static Traceable toTraceable(String aMessage) {
//		if (!aMessage.startsWith("I***"))
//			continue;
		if (!Tracer.isInfo(aMessage))
			return null;
		try {
		Class traceableClass = TraceableInfo.toEvtTypeClass(aMessage);
		Class[] parameterTypes = {String.class}; 
		Method parsingMethod = traceableClass.getMethod("toTraceable", parameterTypes);
		return (Traceable) parsingMethod.invoke(traceableClass, aMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static List<Traceable> toTraceableList(String aFileName) {
		try {
		FileInputStream fis = new FileInputStream(aFileName);
        Scanner scanner = new Scanner(fis);  
		
       List<Traceable> retVal = new ArrayList();      
        while(scanner.hasNextLine()){
        	try {
        		String aMessage = scanner.nextLine();
        		Traceable newElement = toTraceable(aMessage);
        		if (newElement != null)
        			retVal.add(newElement);
//        		if (!aMessage.startsWith("I***"))
//        			continue;
//        		if (!Tracer.isInfo(aMessage))
//        			continue;
//        		Class traceableClass = TraceableInfo.toEvtTypeClass(aMessage);
//        		Class[] parameterTypes = {String.class}; 
//        		Method parsingMethod = traceableClass.getMethod("toTraceable", parameterTypes);
//        		Traceable newElement =  (Traceable) parsingMethod.invoke(traceableClass, aMessage);
//        		retVal.add(newElement);
        		
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
	
//	public static List<Traceable> toTraceableList(String aFileName) {
//		try {
//		FileInputStream fis = new FileInputStream(aFileName);
//        Scanner scanner = new Scanner(fis);  
//		
//       List<Traceable> retVal = new ArrayList();      
//        while(scanner.hasNextLine()){
//        	try {
//        		String aMessage = scanner.nextLine();
////        		if (!aMessage.startsWith("I***"))
////        			continue;
//        		if (!Tracer.isInfo(aMessage))
//        			continue;
//        		Class traceableClass = TraceableInfo.toEvtTypeClass(aMessage);
//        		Class[] parameterTypes = {String.class}; 
//        		Method parsingMethod = traceableClass.getMethod("toTraceable", parameterTypes);
//        		Traceable newElement =  (Traceable) parsingMethod.invoke(traceableClass, aMessage);
//        		retVal.add(newElement);
//        		
//        	} catch (Exception e) {
//        		e.printStackTrace();
//        		
//        	}	
//        }
//        return retVal;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;		
//	}
//	

}
