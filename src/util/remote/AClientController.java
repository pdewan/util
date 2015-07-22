package util.remote;


public class AClientController extends ADistributedProcessController implements ClientController {
	String serverHost;
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
	public String getServerHost() {
		return serverHost;
	}
	@Override
	public void setServerHost(String newVal) {
		this.serverHost = newVal;
		propertyChangeSupport.firePropertyChange("ServerHost", null, newVal );
	}

}
