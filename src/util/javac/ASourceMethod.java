package util.javac;

import demo.codeanalyzer.common.model.MethodInfo;

public class ASourceMethod extends MethodInfo implements SourceMethod{
	CodeInfo codeInfo = new ACodeInfo();


	public  CodeInfo getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(CodeInfo newVal) {
		codeInfo = newVal;
	}
	public String toString() {
		String retVal = "";
		for (String string:codeInfo.getSourceCodeLines()){
			retVal += string + "\n";
			
		}
		return retVal;
	}
}
