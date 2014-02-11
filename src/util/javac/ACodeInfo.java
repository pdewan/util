package util.javac;

public  class ACodeInfo implements CodeInfo {
	int startPosition, endPosition;
	int startLine, endLine;
	String[] surceCode;
	public int getStartPosition() {
		return startPosition;
	}
	public int getEndPosition() {
		return endPosition;
	}
	public String[] getSourceCodeLines() {
		return surceCode;
	}
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
	public void setSourceCodeLines(String[] sourceCode) {
		this.surceCode = sourceCode;
	}
	public int getStartLine() {
		return startLine;
	}
	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}
	public int getEndLine() {
		return endLine;
	}
	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}
	public boolean isAtLineNumber(int aLineNumber) {
		return startLine <= aLineNumber && endLine >= aLineNumber;
	}

}
