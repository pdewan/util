package util.remote;

import util.misc.ClearanceManager;
import util.models.PropertyListenerRegisterer;

public interface DistributedProcessController extends PropertyListenerRegisterer{

	public abstract ClearanceManager getClearanceManager();

	public abstract void setClearanceManager(ClearanceManager clearanceManager);

	public abstract SendReceiveSettings getSendReceiveSettings();

	public abstract void setSendReceiveSettings(
			SendReceiveSettings sendReceiveSettings);



}