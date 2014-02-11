package util.javac;

import java.util.ArrayList;
import java.util.Collection;

import com.sun.source.tree.IfTree;

import demo.codeanalyzer.common.model.BaseJavaClassModelInfo;
import demo.codeanalyzer.common.model.ClassFile;

public class ASourceIf extends BaseJavaClassModelInfo implements SourceIf {
	CodeInfo codeInfo = new ACodeInfo();
	String text;
	IfTree methodInvocationTree;
	@Override
	public IfTree getIfTree() {
		// TODO Auto-generated method stub
		return methodInvocationTree;
	}

    @Override
	public void setIfTree(IfTree methodInvocationTree) {
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


}
