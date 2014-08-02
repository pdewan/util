package util.misc;

import java.lang.reflect.Constructor;

public class NewInstanceRunnable implements Runnable {
	/*
	 * String className; Class classFromName; public void setClassName(String
	 * newVal) { className = newVal; } public void resetClass() { classFromName
	 * = null; }
	 */
	@Override
	/*
	 * public void run() { // TODO Auto-generated method stub try {
	 * classFromName = Class.forName(className);
	 * 
	 * } catch (Exception e) { classFromName = null; }
	 * 
	 * }
	 */
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				// classFromName = Class.forName(className);
				Class cl = Common.instanceClassesBuffer.get();
				// Constructor[] cons = cl.getConstructors();
				Object obj = Common.newInstanceWithDefaultParameters(cl);
				// System.out.println("Succesfully instantiated: " + cl);
				Common.instanceBuffer.put(obj);
			} catch (Exception e) {
				System.out.println(e);
				try {
					Common.instanceBuffer.put(null);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

}
