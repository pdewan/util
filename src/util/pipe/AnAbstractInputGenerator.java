package util.pipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AnAbstractInputGenerator implements InputGenerator {
	protected List<ProcessInputListener> processInputListeners = new ArrayList();
	protected Map<String, Boolean> processToTerminatedSucessfully = new HashMap();
//	protected boolean terminatedSuccessfully;

//	@Override
//	public void propertyChange(PropertyChangeEvent evt) {
//		if (evt.getPropertyName().equals(OUTPUT_LINE)) {
//			processNewOutputLine(null, (String) evt.getNewValue());
//		}		
//	}

//	@Override
//	public void addPropertyChangeListener(PropertyChangeListener aListener) {
//		propertyChangeSupport.addPropertyChangeListener(aListener);		
//	}
	
	

	public void notifyNewInputLine(String aProcessName, String anInput) {
		for (ProcessInputListener aListener:processInputListeners) {
			aListener.newInputLine(aProcessName, anInput);
		}
	}
	
	public void notifyTermination(String aProcessName) {
		for (ProcessInputListener aListener:processInputListeners) {
			aListener.inputTerminated(aProcessName);
		}
	}

//	@Override
//	public void newOutputLine(String aProcessName, String anOutputLine) {
//		
//	}

	@Override
	public void addProcessInputListener(ProcessInputListener aListener) {
		processInputListeners.add(aListener);
		
	}

	@Override
	public void removeProcessInputListener(ProcessInputListener aListener) {
		processInputListeners.remove(aListener);		
	}
	
	public static boolean getValue(Map<String, Boolean> aMap, String aKey) {
		if (aKey == null)
			aKey = "";
		Boolean retVal = aMap.get(aKey);
		if (retVal == null)
			return false;
		return retVal;
		
	}
	
	public boolean isTerminatedSuccessfully(String aProcess) {
		return getValue(processToTerminatedSucessfully, aProcess);
	}

	public void setTerminatedSuccessfully(String aProcess, boolean terminatedSuccessfully) {
		if (aProcess == null)
			aProcess = "";
		processToTerminatedSucessfully.put(aProcess, terminatedSuccessfully);
	}
	// will not be used much probably
	public void newInputLine(String aProcessName, String anInput) {
		
	}
	public void inputTerminated(String aProcessName) {
		
	}
	
	// from ADemoer
protected List<String> processNames = new ArrayList();


	
	boolean terminated;

	@Override
	public synchronized void notifyInteractionTermination() {
		terminated = true;
		this.notify();
		for (String aProcessName:processNames) {
			notifyTermination(aProcessName);
		}
		
	}
	@Override
	public synchronized void waitForInteractionTermination() {
		try {
			while (!terminated)
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void addProcessName(String aProcessName) {
		if (!processNames.contains(aProcessName)) {
    		processNames.add(aProcessName);
    		
    	}
		
	}
	@Override
	public void processNamesAdded() {
		
	}




	public boolean isTerminated() {
		return terminated;
	}



	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}




	
//   protected void newIOFromProcess(String aProcessName, Object newValue) {
//    	// inefficient as each output causes this codee to be executed
////    	addProcessName(aProcessName);
//    }
@Override
public void newOutputLine(String aProcessName, String anOutputLine) {
	// TODO Auto-generated method stub
	
}
@Override
public void executionStarted() {
	// TODO Auto-generated method stub
	
}

}
