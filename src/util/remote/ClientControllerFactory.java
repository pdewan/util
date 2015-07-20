package util.remote;


public class ClientControllerFactory {
	static ClientController singleton;
	public static ClientController getOrCreateSingleton() {
		if (singleton == null) {
			singleton = new AClientController();
			DistributedProcessControllerFactory.setSingleton(singleton);
		}
		return singleton;
			
	}
	public static void setSingleton(ClientController newVal) {
		singleton = newVal;
	}

}
