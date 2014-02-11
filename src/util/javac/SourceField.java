package util.javac;

import demo.codeanalyzer.common.model.Annotation;
import demo.codeanalyzer.common.model.AnnotationInfo;
import demo.codeanalyzer.common.model.ClassFile;
import demo.codeanalyzer.common.model.Field;
import demo.codeanalyzer.common.model.Location;

public interface SourceField extends Field, CodeInfoHolder {

	void setOwningClass(ClassFile clazzInfo);

	void addAnnotation(Annotation annotationInfo);

	void setName(String fieldName);

	void setLocationInfo(Location locationInfo);

}
