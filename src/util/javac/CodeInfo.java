package util.javac;

import demo.codeanalyzer.common.model.Location;

public interface CodeInfo  extends SourceCodeLines{
	public int getStartPosition() ;
	public int getEndPosition();
	public String[] getSourceCodeLines() ;
	public void setStartPosition(int startPosition) ;
	public void setEndPosition(int endPosition) ;
	public void setSourceCodeLines(String[] sourceCode) ;
	public int getStartLine() ;
	public void setStartLine(int startLine) ;
	public int getEndLine() ;
	public void setEndLine(int endLine) ;
	public boolean isAtLineNumber(int aLineNumber);

//	void setLocationInfo(Location locationInfo);
//	Location getLocationInfo();
//	public int getStartPosition() ;
//	public int getEndPosition() ;
//	public String getSurceCode() ;
//	public void setStartPosition(int startPosition) ;
//	public void setEndPosition(int endPosition) ;
//	public void setSurceCode(String surceCode) ;

}
