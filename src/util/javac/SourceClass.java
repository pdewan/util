package util.javac;

import java.util.Collection;
import java.util.List;

import demo.codeanalyzer.common.model.Annotation;
import demo.codeanalyzer.common.model.AnnotationInfo;
import demo.codeanalyzer.common.model.ClassFile;
import demo.codeanalyzer.common.model.Field;
import demo.codeanalyzer.common.model.FieldInfo;
import demo.codeanalyzer.common.model.JavaSourceTreeInfo;
import demo.codeanalyzer.common.model.Location;
import demo.codeanalyzer.common.model.LocationInfo;
import demo.codeanalyzer.common.model.Method;
import demo.codeanalyzer.common.model.MethodInfo;

public interface SourceClass extends ClassFile, CodeInfoHolder{
//	CodeInfo getCodeInfo();
//	void setCodeInfo(CodeInfo newVal);
	String getBuffer();
	void setBuffer(String newVal);
	void setName(String string);
	void setNestingKind(String string);
	void setNameOfSuperClass(String string);
	void addNameOfInterface(String string);
	void setSerializable(boolean b);
	void addAnnotation(Annotation annotationInfo);
	void setLocationInfo(Location locationInfo);
	void setSourceTreeInfo(JavaSourceTreeInfo treeInfo);
	void addConstructor(Method methodInfo);
	void addMethod(Method methodInfo);
	void addField(Field fieldInfo);
	public Collection<? extends SourceMethod> getSourceConstructors() ;
	
	public Collection<? extends SourceMethod> getSourceMethods() ;
	
	public Collection<? extends SourceField> getSourceFields() ;
	public SourceMethod getSourceMethodAtLineNumber(int aLineNumber);
	List<SourceMethodInvocation> getSourceMethodInvocations();
	public void addSourceMethodInvocation(SourceMethodInvocation newVal) ;
	
	List<SourceIf> getSourceIfs();
	public void addSourceIf(SourceIf newVal);


}
