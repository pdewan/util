package util.remote;

import util.annotations.ComponentWidth;


public class ARelayerController extends ADistributedProcessController implements RelayerController {
	boolean echoBack;
	String relayClients = "";
	
	@Override
	public boolean isEchoBack() {
		return echoBack;
	}
	@Override
	public void setEchoBack(boolean echoBack) {
		this.echoBack = echoBack;
		propertyChangeSupport.firePropertyChange("EchoBack", null,echoBack);

	}
	@ComponentWidth(400)
	public String getRelayClients() {
		return relayClients;
	}
	public void setRelayClients(String clients) {
		this.relayClients = clients;
		propertyChangeSupport.firePropertyChange("RelayClients", null, clients);
	}

}
