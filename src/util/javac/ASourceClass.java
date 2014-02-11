package util.javac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import demo.codeanalyzer.common.model.ClassFile;
import demo.codeanalyzer.common.model.JavaClassInfo;
import demo.codeanalyzer.common.model.Method;


public class ASourceClass extends JavaClassInfo implements SourceClass {
	CodeInfo codeInfo = new ACodeInfo();
	String buffer;

	public  CodeInfo getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(CodeInfo newVal) {
		codeInfo = newVal;
	}
	public Collection<? extends SourceMethod> getSourceConstructors() {
		return (Collection<? extends SourceMethod>) getConstructors();
	}
	
	public Collection<? extends SourceMethod> getSourceMethods() {
		return (Collection<? extends SourceMethod>) getMethods();
	}
	
	public Collection<? extends SourceField> getSourceFields() {
		return (Collection<? extends SourceField>) getFields();
	}

	public String getBuffer() {
		// TODO Auto-generated method stub
		return buffer;
	}

	public void setBuffer(String newVal) {
		buffer = newVal;
	}
	
	
	
	public SourceMethod getSourceMethodAtLineNumber(int aLineNumber) {
		Collection<? extends SourceMethod> sourceMethods = getSourceMethods();
		Collection<? extends SourceMethod> sourceConstructors = getSourceConstructors();
		for (SourceMethod sourceMethod:sourceMethods) {
			if (sourceMethod.getCodeInfo().isAtLineNumber(aLineNumber)) return sourceMethod;
		}
		for (SourceMethod sourceMethod:sourceConstructors) {
			if (sourceMethod.getCodeInfo().isAtLineNumber(aLineNumber)) return sourceMethod;
		}
		return null;
	}
	
	List<SourceMethodInvocation> sourceMethodInvocations = new ArrayList();
	public List<SourceMethodInvocation> getSourceMethodInvocations(){
		return sourceMethodInvocations;
	}
	public void addSourceMethodInvocation(SourceMethodInvocation newVal) {
		sourceMethodInvocations.add(newVal);
	}

	List<SourceIf> sourceIfs = new ArrayList();
	public List<SourceIf> getSourceIfs(){
		return sourceIfs;
	}
	public void addSourceIf(SourceIf newVal) {
		sourceIfs.add(newVal);
	}

}
