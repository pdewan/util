/*
 * Created on Jul 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.models;

import java.util.Calendar;
import java.util.Hashtable;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ATimeUnit extends AStringEnum {
	public static String SECOND = "Second";
	public static String MINUTE = "Minute";
	public static String QUARTER_HOUR = "15 Minutes";
	public static String HALF_HOUR = "30 Minutes";
	public static String HOUR = "Hour";
	public static String DAY = "Day";
	public static String WEEK = "Week";
	/*
	 * public static String DAY_OF_WEEK = "Day of Week"; public static String
	 * DAY_OF_WEEK_IN_MONTH = "Day of Week In Month";
	 */
	public static String MONTH = "Month";
	public static String YEAR = "Year";
	String[] choices = { SECOND, MINUTE, QUARTER_HOUR, HALF_HOUR, HOUR, DAY,
			WEEK, MONTH, YEAR };
	static Hashtable mapping = new Hashtable();
	static boolean mappingInitialized = false;

	public ATimeUnit() {
		super();
		setChoices(choices);
		initializeMapping();
	}

	void initializeMapping() {
		if (mappingInitialized)
			return;
		mapping.put(SECOND, new Integer(Calendar.SECOND));
		mapping.put(MINUTE, new Integer(Calendar.MINUTE));
		mapping.put(QUARTER_HOUR, new Integer(Calendar.MINUTE));
		mapping.put(HALF_HOUR, new Integer(Calendar.MINUTE));
		mapping.put(HOUR, new Integer(Calendar.HOUR));
		mapping.put(DAY, new Integer(Calendar.DAY_OF_MONTH));
		mapping.put(WEEK, new Integer(Calendar.DAY_OF_MONTH));
		mapping.put(MONTH, new Integer(Calendar.MONTH));
		mapping.put(YEAR, new Integer(Calendar.DAY_OF_YEAR));

	}

	public int multiplier() {
		if (this.getValue().equals(QUARTER_HOUR))
			return 15;
		else if (this.getValue().equals(HALF_HOUR))
			return 30;
		else if (this.getValue().equals(WEEK))
			return 7;
		else
			return 1;

	}

	public int calendarUnit() {
		return ((Integer) mapping.get(getValue())).intValue();
	}

}
