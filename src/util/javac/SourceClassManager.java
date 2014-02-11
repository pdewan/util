package util.javac;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores the attribute details of all java input files
 * @author Deepa Sobhana, Seema Richard
 */
public class SourceClassManager {

    private Map<String, SourceClass> classInfoMap = new HashMap();
    private static SourceClassManager modelMap = new SourceClassManager();

    private SourceClassManager() {

    }

    public static SourceClassManager getInstance() {
        return modelMap;
    }

    public Map<String, SourceClass> getClassInfoMap() {
        return classInfoMap;
    }

    public SourceClass getClassInfo(String className) {
        return classInfoMap.get(className);
    }
    
    public SourceClass getOrCreateClassInfo(Class aClass) {
    	return getOrCreateClassInfo(aClass.getName());
//    	SourceClass aSourceClass = getClassInfo(aClass.getName());
//    	if (aSourceClass == null) {
//    		ParserMain.parse(aClass);
//    	}    		
//        return classInfoMap.get(aClass.getName());
    }
    
    public SourceClass getOrCreateClassInfo(String aClassName, StringBuffer aSource) {
    	SourceClass aSourceClass = getClassInfo(aClassName);
    	if (aSourceClass == null) {
    		ParserMain.parseClassNoByteCode(aClassName, aSource);
    	}    		
        return classInfoMap.get(aClassName);
    }
    
    
    
    public SourceClass getOrCreateClassInfo(String aClassName) {
    	SourceClass aSourceClass = getClassInfo(aClassName);
    	if (aSourceClass == null) {
    		ParserMain.parseClassNoByteCode(aClassName);
    	}    		
        return classInfoMap.get(aClassName);
    }

    public void addClassInfo(String className, SourceClass classInfo) {
        classInfoMap.put(className, classInfo);
    }
}
