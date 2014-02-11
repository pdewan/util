package util.javac;

import java.util.ArrayList;
import java.util.Collection;

import com.sun.source.tree.MethodInvocationTree;

import demo.codeanalyzer.common.model.BaseJavaClassModelInfo;
import demo.codeanalyzer.common.model.ClassFile;

public class ASourceMethodInvocation extends BaseJavaClassModelInfo implements SourceMethodInvocation {
	CodeInfo codeInfo = new ACodeInfo();
	String text;
	MethodInvocationTree methodInvocationTree;
	@Override
	public MethodInvocationTree getMethodInvocationTree() {
		// TODO Auto-generated method stub
		return methodInvocationTree;
	}

    @Override
	public void setMethodInvocationTree(MethodInvocationTree methodInvocationTree) {
		this.methodInvocationTree = methodInvocationTree;
	}

	public  CodeInfo getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(CodeInfo newVal) {
		codeInfo = newVal;
	}
	private ClassFile owningClass;
    
    /**
     * @return the {@link ClassFile} this method belongs to.
     */
	@Override
    public ClassFile getOwningClass() {
        return owningClass;
    }
	@Override
    public void setOwningClass(ClassFile owningClass) {
        this.owningClass = owningClass;
    }

	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String newValue) {
		text = newValue;
	}
	
	public String toString() {
		return text;
	}


}
