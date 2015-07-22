package util.remote;


public interface RelayerController extends DistributedProcessController{

	boolean isEchoBack();

	void setEchoBack(boolean echoBack);
	public String getRelayClients() ;
	public void setRelayClients(String clients) ;

}
