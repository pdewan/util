package util.remote;


public interface RelayerController extends DistributedProcessController{

	boolean isEchoBack();
	void setEchoBack(boolean echoBack);
	boolean isWaitForRelay();
	void setWaitForRelay(boolean newVal);
	public String getRelayClients() ;
	public void setRelayClients(String clients) ;
	

}
