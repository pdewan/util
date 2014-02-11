package util.javac;

import demo.codeanalyzer.common.model.FieldInfo;

public class ASourceField extends FieldInfo implements SourceField  {
	CodeInfo codeInfo = new ACodeInfo();


	public  CodeInfo getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(CodeInfo newVal) {
		codeInfo = newVal;
	}


}
