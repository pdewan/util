package util.javac;

import com.sun.source.tree.MethodInvocationTree;

import demo.codeanalyzer.common.model.ClassFile;
import demo.codeanalyzer.common.model.Location;

public interface SourceMethodInvocation extends CodeInfoHolder{

	ClassFile getOwningClass();
	MethodInvocationTree getMethodInvocationTree();
	String getText();
	public void setText(String newValue);

	void setOwningClass(ClassFile owningClass);
	void setLocationInfo(Location locationInfo);
	void setMethodInvocationTree(MethodInvocationTree methodInvocationTree);


}
