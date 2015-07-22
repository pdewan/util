package util.remote;

public class ARelayerClientController extends AClientController implements RelayerClientController{
	String relayerId;
	@Override
	public String getRelayerId() {
		return relayerId;
	}
	@Override
	public void setRelayerId(String newVal) {
		this.relayerId = newVal;
		propertyChangeSupport.firePropertyChange("RelayerId", null, newVal );
	}
}
