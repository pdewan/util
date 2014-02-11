package util.singlestep;

import java.beans.PropertyChangeListener;

import util.annotations.Column;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class ASingleStepperAndListBrowser extends AListBrowser implements SingleStepperAndListBrowser{
//	List history = new ArrayList();
//	int currentIndex;
	boolean waiting;
	

	@Visible(false)
	public synchronized void waitForUser() {
		try {
			waiting = true;
			propertyChangeSupport.firePropertyChange("currentObject", null, getCurrentObject());
			wait();
			waiting = false;
			propertyChangeSupport.firePropertyChange("currentObject", null, getCurrentObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	@Row(1)
	@Column(2)
	public synchronized void proceed() {
		try {
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean preProceed() {
		return waiting;
	}	
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	

}
