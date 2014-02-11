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
public interface StringEnum {
	public void setChoices(String[] theInitialChoices);

	public int choicesSize();

	public String choiceAt(int i);

	public String getValue();

	public void setValue(String newVal);

	public String toString();
}