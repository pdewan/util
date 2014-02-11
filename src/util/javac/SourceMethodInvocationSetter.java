package util.javac;

import com.sun.source.tree.MethodInvocationTree;
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
public class SourceMethodInvocationSetter {

    public static void populateMethodInvocationInfo(SourceClass clazzInfo,
           MethodInvocationTree nodeTree, Element e, TreePath path, Trees trees) {

        
        SourceMethodInvocation info = new ASourceMethodInvocation();
        info.setMethodInvocationTree(nodeTree);
        info.setText(nodeTree.toString());
        info.setOwningClass(clazzInfo);        
        //Set Temp LocationInfo
        if (e != null) { // looks like it is awlays null
        Location locationInfo = DataSetterUtil.getLocationInfo(trees, path, nodeTree);
        info.setLocationInfo(locationInfo);
        
        DataSetterUtil.setSourceCodeInfo(info.getCodeInfo(),  nodeTree, locationInfo);
        }
        clazzInfo.addSourceMethodInvocation(info);

    }
}
