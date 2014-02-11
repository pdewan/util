package util.javac;

import com.sun.source.tree.IfTree;
import com.sun.source.tree.MethodInvocationTree;

import demo.codeanalyzer.common.model.ClassFile;
import demo.codeanalyzer.common.model.Location;

public interface SourceIf extends CodeInfoHolder{

	ClassFile getOwningClass();
	IfTree getIfTree();
	String getText();
	public void setText(String newValue);

	void setOwningClass(ClassFile owningClass);
	void setLocationInfo(Location locationInfo);
	void setIfTree(IfTree newVal);


}
