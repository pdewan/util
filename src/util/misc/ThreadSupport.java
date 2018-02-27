package util.misc;

import util.trace.misc.ThreadDelayed;

public class ThreadSupport {
	
	public static void sleep(long interval) {
		if (interval == 0)
			return;
		try {
			ThreadDelayed.newCase(ThreadSupport.class, interval);
			Thread.sleep(interval);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
