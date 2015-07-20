package util.remote;


public class ARelayerController extends ADistributedProcessController implements RelayerController {
	boolean echoBack;
	@Override
	public boolean isEchoBack() {
		return echoBack;
	}
	@Override
	public void setEchoBack(boolean echoBack) {
		this.echoBack = echoBack;
	}
	

}
