package util.remote;


public class RelayerClientControllerFactory {
	static RelayerClientController singleton;
	public static RelayerClientController getOrCreateSingleton() {
		if (singleton == null) {
			singleton = new ARelayerClientController();
			ClientControllerFactory.setSingleton(singleton);
		}
		return singleton;
			
	}
	public static void setSingleton(RelayerClientController newVal) {
		singleton = newVal;
	}

}
