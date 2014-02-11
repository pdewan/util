package util.javac;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.ConditionalExpressionTree;
import com.sun.source.tree.IfTree;
import com.sun.source.tree.ImportTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

import javax.lang.model.element.Element;

import demo.codeanalyzer.common.model.JavaClassInfo;
import demo.codeanalyzer.helper.ClassInfoDataSetter;
import demo.codeanalyzer.helper.FieldInfoDataSetter;
import demo.codeanalyzer.helper.MethodInfoDataSetter;

/**
 * Visitor class which visits different nodes of the input source file, 
 * extracts the required atribute of the visiting class, its mehods, 
 * fields, annotations etc and set it in the java class model.
 * 
 * @author Seema Richard (Seema.Richard@ust-global.com)
 * @author Deepa Sobhana (Deepa.Sobhana@ust-global.com)
 */
public class CodeParserTreeVisitor extends TreePathScanner<Object, Trees> {

    //Model class stores the details of the visiting class
    SourceClass clazzInfo = new ASourceClass();

    /**
     * Visits the class
     * @param classTree
     * @param trees
     * @return
     */
    @Override
    public Object visitClass(ClassTree classTree, Trees trees) {

        TreePath path = getCurrentPath();
        //populate required class information to model
        SourceClassSetter.populateClassInfo(clazzInfo, classTree, 
                                              path, trees);
        return super.visitClass(classTree, trees);
//        return null;
    }
    @Override
    public Object visitAssignment(AssignmentTree node,
            Trees p) {
    	return super.visitAssignment(node, p);
    	
    }
   public Object visitConditionalExpression(ConditionalExpressionTree node, Trees p)  {
	   return super.visitConditionalExpression(node, p);
   }

    /**
     * Visits all methods of the input java source file
     * @param methodTree
     * @param trees
     * @return
     */
    @Override
    public Object visitMethod(MethodTree methodTree, Trees trees) {
        TreePath path = getCurrentPath();
        //populate required method information to model
        SourceMethodSetter.populateMethodInfo(clazzInfo, methodTree, 
                                                path, trees);
//        return null; // will not go into method and generate code
        return super.visitMethod(methodTree, trees);
    }
    @Override
    public Object visitIf(IfTree node, Trees trees) {
//    	System.out.println(node);
    	TreePath path = getCurrentPath();
        Element e = trees.getElement(path);

        //populate required method information to model
        SourceIfSetter.populateIfInfo(clazzInfo, node, e, 
                                              path, trees);
    	
    	return super.visitIf(node, trees);
    }
    @Override
    public Object visitImport (ImportTree anImportTree, Trees trees) {
    	System.out.println(anImportTree);

    	return super.visitImport(anImportTree, trees);
    }

    /**
     * Visits all variables of the java source file
     * @param variableTree
     * @param trees
     * @return
     */
    @Override
    public Object visitVariable(VariableTree variableTree, Trees trees) {
        TreePath path = getCurrentPath();
        Element e = trees.getElement(path);

        //populate required method information to model
        SourceFieldSetter.populateFieldInfo(clazzInfo, variableTree, e, 
                                              path, trees);
        return super.visitVariable(variableTree, trees);
//        return super.visitVariable(variableTree, trees);
    }
    @Override
    public Object visitMethodInvocation(MethodInvocationTree node,
            Trees trees) {
//    	System.out.println(node);
    	TreePath path = getCurrentPath();
        Element e = trees.getElement(path);

        //populate required method information to model
        SourceMethodInvocationSetter.populateMethodInvocationInfo(clazzInfo, node, e, 
                                              path, trees);
    	return super.visitMethodInvocation(node, trees);
    }

    /**
     * Returns the Java class model which holds the details of java source
     * @return clazzInfo Java class model 
     */
    public SourceClass getClassInfo() {
        return clazzInfo;
    }
}

