/*
 * Created on Jul 11, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.models;

import java.util.Calendar;
import java.util.Date;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ATimeWithIncrementDecrement {
	Date date;
	ATimeUnit timeUnit;
	Calendar cal = Calendar.getInstance();

	public ATimeWithIncrementDecrement(Date theDate) {
		date = theDate;
		timeUnit = new ATimeUnit();
		timeUnit.setValue(ATimeUnit.QUARTER_HOUR);
	}

	public Date getTime() {
		return date;
	}

	public void setTime(Date newVal) {
		date = newVal;
	}

	public void increment(Date theDate) {
		System.out.println("Increment called");
		add(theDate, timeUnit.calendarUnit(), timeUnit.multiplier());
	}

	public void decrement(Date theDate) {
		System.out.println("Decrement called");

	}

	public String toString() {
		return date.toString();
	}

	public ATimeUnit getStep() {
		return timeUnit;
	}

	public void setStep(ATimeUnit newVal) {
		timeUnit = newVal;
	}

	public static void add(Date date, int unit, int increment) {
		System.out.println("Original date:" + date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(unit, increment);
		Date newDate = cal.getTime();
		System.out.println("newDate " + newDate);
		System.out.println("date " + date);
		date.setTime(newDate.getTime());
		System.out.println("date after copying" + date);

	}
	/*
	 * int test; public int getTest() { return test; } public void setTest(int
	 * newVal) { test = newVal; } public void increment(int newVal) {
	 * System.out.println("Int Increment called"); test = newVal + 10;
	 * 
	 * } public void decrement(int newVal) {
	 * System.out.println("Int Increment called"); test = newVal - 10;
	 * 
	 * }
	 */

}
