package util.remote;

import util.annotations.ComponentWidth;


public class ARelayerController extends ADistributedProcessController implements RelayerController {
	boolean echoBack;
	String clients = "";
	
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
	public String getClients() {
		return clients;
	}
	public void setClients(String clients) {
		this.clients = clients;
		propertyChangeSupport.firePropertyChange("Clients", null, clients);
	}

}
