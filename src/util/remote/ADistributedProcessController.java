package util.remote;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.misc.ClearanceManagerFactory;

public class ADistributedProcessController implements DistributedProcessController {
	protected ClearanceManager clearanceManager = ClearanceManagerFactory.getOrCreateClearanceManager();
	protected SendReceiveSettings sendReceiveSettings = SendReceiveSettingsFactory.getOrCreateSingleton();
//	protected ClearanceManager clearanceManager = new AClearanceManager();
//	protected SendReceiveSettings sendReceiveSettings = new ASendReceiveSettings();
//	protected boolean markCommands = true;
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	@Override
	public ClearanceManager getClearanceManager() {
		return clearanceManager;
	}
	@Override
	public void setClearanceManager(ClearanceManager clearanceManager) {
		this.clearanceManager = clearanceManager;
	}
	@Override
	public SendReceiveSettings getSendReceiveSettings() {
		return sendReceiveSettings;
	}
	@Override
	public void setSendReceiveSettings(SendReceiveSettings sendReceiveSettings) {
		this.sendReceiveSettings = sendReceiveSettings;
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	
	

}
