package util.misc;

public class ClassForNameRunnable implements Runnable {

	String className;
	Class classFromName;

	public void setClassName(String newVal) {
		className = newVal;
	}

	public void resetClass() {
		classFromName = null;
	}

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
				String className = (String) Common.classNamesBuffer.get();
				Class classFromName = Class.forName(className);
				Common.classesBuffer.put(classFromName);
			} catch (Exception e) {
				classFromName = null;
			}
		}

	}

	public Class getClassFromName() {
		return classFromName;
	}

}
