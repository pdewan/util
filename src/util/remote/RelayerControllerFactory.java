package util.remote;


public class RelayerControllerFactory {
	static RelayerController singleton;
	public static RelayerController getOrCreateSingleton() {
		if (singleton == null) {
			singleton = new ARelayerController();
			DistributedProcessControllerFactory.setSingleton(singleton);
		}
		return singleton;
			
	}

}
