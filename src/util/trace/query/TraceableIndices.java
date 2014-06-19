package util.trace.query;

import util.trace.Traceable;
import util.trace.TraceableInfo;

public class TraceableIndices extends TraceableInfo {
	Integer testIndex;
	Integer referenceIndex;
	public TraceableIndices(String aMessage,  
			Integer aFirstIndex, 
			Integer aSecondIndex, 
			Object aFinder) {
		super(aMessage, aFinder);
		testIndex = aFirstIndex;
		referenceIndex = aSecondIndex;
	}
	public TraceableIndices(String aMessage, 
			Integer aFirstIndex, 
			Integer aSecondIndex) {
		super(aMessage);
		testIndex = aFirstIndex;
		referenceIndex = aSecondIndex;

	}
	
	public static final String TEST_INDEX = "TestIndex";
	public static final String REFERENCE_INDEX = "ReferenceIndex";

	
	public static final String toString(Integer anIndex1, Integer anIndex2) {
		return TEST_INDEX + Traceable.FLAT_LEFT_MARKER + 
				anIndex1 +
				TraceableInfo.FLAT_RIGHT_MARKER +
				" " +
				REFERENCE_INDEX + Traceable.FLAT_LEFT_MARKER + 
				anIndex2 +
				TraceableInfo.FLAT_RIGHT_MARKER;
				
	}
	public static Integer getIndex1(String aMessage) {
		return Integer.parseInt(Traceable.getArg(aMessage, TEST_INDEX));
	}
	public static Integer getIndex2(String aMessage) {
		return Integer.parseInt(Traceable.getArg(aMessage, REFERENCE_INDEX));
	}
	
	public static TraceableIndices toTraceable(String aMessage) {
		try {
		return new TraceableIndices (aMessage, 
				getIndex1(aMessage), getIndex2(aMessage));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static TraceableIndices newCase (
			Integer aTestIndex,  
			Integer aReferenceIndex,
			Object  aFinder) {
		String aMessage = toString(aTestIndex, aReferenceIndex);
		TraceableIndices retVal = new TraceableIndices(aMessage, aTestIndex, aReferenceIndex, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
