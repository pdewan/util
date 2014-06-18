package util.trace.query;

import util.trace.Traceable;
import util.trace.TraceableInfo;

public class IndexedTraceable extends TraceableInfo {
	Integer firstIndex;
	Integer secondIndex;
	public IndexedTraceable(String aMessage,  
			Integer aFirstIndex, 
			Integer aSecondIndex, 
			Object aFinder) {
		super(aMessage, aFinder);
		firstIndex = aFirstIndex;
		secondIndex = aSecondIndex;
	}
	public IndexedTraceable(String aMessage, 
			Integer aFirstIndex, 
			Integer aSecondIndex) {
		super(aMessage);
		firstIndex = aFirstIndex;
		secondIndex = aSecondIndex;

	}
	
	public static final String INDEX1 = "Index1";
	public static final String INDEX2 = "Index2";

	
	public static final String toString(Integer anIndex1, Integer anIndex2) {
		return INDEX1 + Traceable.FLAT_LEFT_MARKER + 
				anIndex1 +
				TraceableInfo.FLAT_RIGHT_MARKER +
				" " +
				INDEX2 + Traceable.FLAT_LEFT_MARKER + 
				anIndex2 +
				TraceableInfo.FLAT_RIGHT_MARKER;
				
	}
	public static Integer getIndex1(String aMessage) {
		return Integer.parseInt(Traceable.getArg(aMessage, INDEX1));
	}
	public static Integer getIndex2(String aMessage) {
		return Integer.parseInt(Traceable.getArg(aMessage, INDEX2));
	}
	
	public static IndexedTraceable toTraceable(String aMessage) {
		try {
		return new IndexedTraceable (aMessage, 
				getIndex1(aMessage), getIndex2(aMessage));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static IndexedTraceable newCase (
			Integer anIndex1,  
			Integer anIndex2,
			Object aFinder) {
		String aMessage = toString(anIndex1, anIndex2);
		IndexedTraceable retVal = new IndexedTraceable(aMessage, anIndex1, anIndex2, aFinder);
		retVal.announce();
		return retVal;
	}
	

}
