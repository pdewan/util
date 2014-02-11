package util.javac;

import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

import demo.codeanalyzer.common.model.AnnotationInfo;
import demo.codeanalyzer.common.model.FieldInfo;
import demo.codeanalyzer.common.model.JavaClassInfo;
import demo.codeanalyzer.common.model.Location;
import demo.codeanalyzer.common.model.LocationInfo;

/**
 * Helper class to set the properties of 
 * fields to the java class model
 * 
 * @author Seema Richard (Seema.Richard@ust-global.com)
 * @author Deepa Sobhana (Deepa.Sobhana@ust-global.com)
 */
public class SourceFieldSetter {

    public static void populateFieldInfo(SourceClass clazzInfo,
            VariableTree variableTree, Element e, TreePath path, Trees trees) {

        if (e == null) {
            return;
        }

        SourceField fieldInfo = new ASourceField();
        String fieldName = variableTree.getName().toString();
        fieldInfo.setOwningClass(clazzInfo);
        //Set modifier details
        for (Modifier modifier : e.getModifiers()) {
            DataSetterUtil.setModifiers(modifier.toString(), (FieldInfo) fieldInfo);
        }
        List<? extends AnnotationMirror> annotations = e.getAnnotationMirrors();
        for (AnnotationMirror annotationMirror : annotations) {
            String qualifiedName = annotationMirror.toString().substring(1);
            AnnotationInfo annotationInfo = new AnnotationInfo();
            annotationInfo.setName(qualifiedName);
            fieldInfo.addAnnotation(annotationInfo);
        }
        fieldInfo.setName(fieldName);
        clazzInfo.addField(fieldInfo);
        //Set Temp LocationInfo
        Location locationInfo = DataSetterUtil.getLocationInfo(trees, path, variableTree);
        fieldInfo.setLocationInfo(locationInfo);
        DataSetterUtil.setSourceCodeInfo(fieldInfo.getCodeInfo(),  variableTree, locationInfo);

    }
}
