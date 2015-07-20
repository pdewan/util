package util.remote;


public class AClientController extends ADistributedProcessController implements ClientController {
	String serverId;
	@Override
	public String getServerId() {
		return serverId;
	}
	@Override
	public void setServerId(String newVal) {
		this.serverId = newVal;
		propertyChangeSupport.firePropertyChange("ServerId", null, newVal );
	}
	

}
