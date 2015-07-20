package util.remote;


public interface ClientController extends DistributedProcessController{

	String getServerId();

	void setServerId(String newVal);

	
}
