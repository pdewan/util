package util.javac;

import demo.codeanalyzer.common.model.ClassFile;
import demo.codeanalyzer.common.model.Location;
import demo.codeanalyzer.common.model.Method;

public interface SourceMethod extends Method, CodeInfoHolder{

	void setOwningClass(ClassFile clazzInfo);

	void addParameters(String string);

	void setReturnType(String string);

	void setLocationInfo(Location locationInfo);

	void setName(String methodName);

}
