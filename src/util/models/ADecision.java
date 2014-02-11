/*
 * Created on Jul 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.models;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ADecision extends AStringEnum {
	public static String YES = "Yes";
	public static String NO = "No";
	public static String MAYBE = "Maybe";
	String[] choices = { YES, NO, MAYBE };

	public ADecision() {
		super();
		setChoices(choices);
	}

}
