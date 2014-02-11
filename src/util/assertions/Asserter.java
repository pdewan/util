package util.assertions;

import java.util.Iterator;

public interface Asserter<ElementType> {
	public void myAssert(boolean boolExp, String message)
			throws AnAssertionFailedException;

	public void assertUniversal(Iterator<ElementType> enumParam,
			ElementChecker elementChecker, String message);

	public void assertExistential(Iterator<ElementType> enumParam,
			ElementChecker elementChecker, String message);
}